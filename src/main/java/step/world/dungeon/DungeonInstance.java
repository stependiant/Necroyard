package step.world.dungeon;

import net.minestom.server.instance.InstanceContainer;
import step.world.generator.DungeonGenerator;

import java.util.UUID;

public class DungeonInstance {
    private final UUID dungeonId;
    private InstanceContainer dungeonInstance;
    private final DungeonType dungeonType;

    public DungeonInstance(DungeonType type) {
        this.dungeonId = UUID.randomUUID();
        this.dungeonType = type;
        generateDungeon();
    }

    private void generateDungeon() {
        dungeonInstance = DungeonGenerator.generateDungeon(dungeonType);
    }

    public UUID getDungeonId() {
        return dungeonId;
    }

    public InstanceContainer getDungeonInstance() {
        return dungeonInstance;
    }

    public void unloadDungeon() {
        // TODO: settings for unload chunks after complete his (CompletableFuture)
    }

    @Override
    public String toString() {
        return "DungeonInstance{Type=" + dungeonType + ", id=" + dungeonId;
    }
}
