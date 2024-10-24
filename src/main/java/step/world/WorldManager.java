package step.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import step.world.dungeon.DungeonInstance;
import step.world.dungeon.DungeonType;
import step.world.island.PlayerIsland;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorldManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorldManager.class);
    private static volatile WorldManager instance;

    private InstanceContainer mainLobby;
    private final Map<UUID, PlayerIsland> playerIslands;
    private final Map<UUID, DungeonInstance> activeDungeons;

    public WorldManager() {
        playerIslands = new ConcurrentHashMap<>();
        activeDungeons = new ConcurrentHashMap<>();
        loadMainLobby();
    }

    public static WorldManager getInstance() {
        if (instance == null) {
            synchronized (WorldManager.class) {
                instance = new WorldManager();
            }
        }
        return instance;
    }

    private void loadMainLobby() {
        Path path = Paths.get("src","main", "resources", "presets", "mainLobby");
        AnvilLoader anvilLoader = new AnvilLoader(path);
        mainLobby = MinecraftServer.getInstanceManager().createInstanceContainer(anvilLoader);
        LOGGER.debug("Loading main lobby");
    }

    public InstanceContainer getMainLobby() {
        return mainLobby;
    }

    public PlayerIsland getPlayerIsland(UUID playerId) {
        PlayerIsland island = playerIslands.computeIfAbsent(playerId, PlayerIsland::new);
        LOGGER.debug("Loaded island for player: {}", playerId);
        return island;
    }

    public DungeonInstance createDungeonInstance(DungeonType type) {
        DungeonInstance dungeon = new DungeonInstance(type);
        activeDungeons.put(dungeon.getDungeonId(), dungeon);
        LOGGER.debug("Created new dungeon: {}", dungeon);
        return dungeon;
    }

    public void removeDungeonInstance(UUID dungeonId) {
        DungeonInstance dungeon = activeDungeons.remove(dungeonId);
        if (dungeon != null) {
            dungeon.unloadDungeon();
            LOGGER.debug("Removed dungeon: {}", dungeon);
        }
    }
}
