package step.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import step.world.utils.DungeonType;
import step.world.generator.DungeonGenerator;
import step.world.generator.PlayerIslandGenerator;
import step.world.instance.DungeonInstance;
import step.world.instance.LobbyInstance;
import step.world.instance.PlayerInstance;
import step.world.utils.Tags;
import step.world.utils.WorldPath;
import step.world.utils.WorldType;

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Менеджер миров, управляющий различными игровыми инстансами.
 */
public class WorldManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorldManager.class);
    private static volatile WorldManager instance;

    private LobbyInstance mainLobby;
    private final Map<UUID, PlayerInstance> playerIslands;
    private final Map<UUID, DungeonInstance> activeDungeons;

    public WorldManager() {
        playerIslands = new ConcurrentHashMap<>();
        activeDungeons = new ConcurrentHashMap<>();
    }

    public static WorldManager getInstance() {
        if (instance == null) {
            synchronized (WorldManager.class) {
                if (instance == null) {
                    instance = new WorldManager();
                }
            }
        }
        return instance;
    }

    public LobbyInstance getLobbyInstance() {
        if (mainLobby == null) {
            Path path = WorldPath.MAIN_LOBBY.getPath();
            AnvilLoader anvilLoader = new AnvilLoader(path);
            LOGGER.debug("Loading main lobby from {}", path.toAbsolutePath());
            InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer(anvilLoader);
            instanceContainer.setTag(Tags.WORLD_TYPE_TAG, WorldType.LOBBY);
            mainLobby = new LobbyInstance(instanceContainer);
            // TODO: Настройка обработчиков событий для mainLobby
        }
        return mainLobby;
    }

    public PlayerInstance getPlayerInstance(UUID playerId) {
        return playerIslands.computeIfAbsent(playerId, id -> {
            InstanceContainer instanceContainer = PlayerIslandGenerator.loadIsland(id);
            instanceContainer.setTag(Tags.WORLD_TYPE_TAG, WorldType.ISLAND);
            PlayerInstance islandInstance = new PlayerInstance(instanceContainer, id);
            LOGGER.debug("Loaded island for player: {}", id);
            return islandInstance;
        });
    }

    public DungeonInstance getDungeonInstance(DungeonType type) {
        InstanceContainer instanceContainer = DungeonGenerator.generateDungeon(type);
        instanceContainer.setTag(Tags.WORLD_TYPE_TAG, WorldType.DUNGEON);
        DungeonInstance dungeonInstance = new DungeonInstance(instanceContainer, type);
        activeDungeons.put(dungeonInstance.getDungeonId(), dungeonInstance);
        LOGGER.debug("Created new dungeon: {}", dungeonInstance);
        return dungeonInstance;
    }

    public void removeDungeonInstance(UUID dungeonId) {
        DungeonInstance dungeonInstance = activeDungeons.remove(dungeonId);
        if (dungeonInstance != null) {
            dungeonInstance.unloadDungeon();
            LOGGER.debug("Removed dungeon: {}", dungeonInstance);
        }
    }
}
