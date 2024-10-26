package step.world.instance;

import net.minestom.server.instance.InstanceContainer;
import step.world.utils.DungeonType;
import step.world.utils.WorldType;

import java.util.UUID;

/**
 * model class for dungeon instance
 */
public class DungeonInstance extends AbstractGameInstance {
    private final UUID dungeonId;
    private final DungeonType dungeonType;

    public DungeonInstance(InstanceContainer instanceContainer, DungeonType dungeonType) {
        super(instanceContainer, WorldType.DUNGEON);
        this.dungeonId = UUID.randomUUID();
        this.dungeonType = dungeonType;
    }

    public UUID getDungeonId() {
        return dungeonId;
    }

    public DungeonType getDungeonType() {
        return dungeonType;
    }

    public void unloadDungeon() {
        // TODO: release logic for unload dungeonInstance
    }

    @Override
    public String toString() {
        return "DungeonInstance{Type=" + dungeonType + ", id=" + dungeonId;
    }


}
