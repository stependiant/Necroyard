package step.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import step.world.dungeon.DungeonInstance;
import step.world.dungeon.DungeonType;
import step.world.island.PlayerIsland;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorldManager {
    private static WorldManager instance;

    private InstanceContainer mainLobby;
    private final Map<UUID, PlayerIsland> playerIslands;
    private final Map<UUID, DungeonInstance> activeDungeons;

    private WorldManager() {
        instance = new WorldManager();
        playerIslands = new ConcurrentHashMap<>();
        activeDungeons = new ConcurrentHashMap<>();
    }

    public static WorldManager getInstance() {
        return instance;
    }

    private void loadMainLobby() {
        mainLobby = MinecraftServer.getInstanceManager().createInstanceContainer();
        // TODO: load lobby from AnvilLoader with lobby map
    }

    public InstanceContainer getMainLobby() {
        return mainLobby;
    }

    public PlayerIsland getPlayerIsland(UUID playerId) {
        return playerIslands.computeIfAbsent(playerId, PlayerIsland::new);
    }

    public DungeonInstance createDungeonInstance(DungeonType type) {
        DungeonInstance dungeon = new DungeonInstance(type);
        activeDungeons.put(dungeon.getDungeonId(), dungeon);
        return dungeon;
    }

    public void removeDungeonInstance(UUID dungeonId) {
        DungeonInstance dungeon = activeDungeons.remove(dungeonId);
        if (dungeon != null) {
            dungeon.unloadDungeon();
        }
    }
}
