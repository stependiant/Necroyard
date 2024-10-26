package step.world.generator;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import step.world.utils.DungeonType;

public class DungeonGenerator {


    public static InstanceContainer generateDungeon(DungeonType type) {
        InstanceContainer dungeonInstance = MinecraftServer.getInstanceManager().createInstanceContainer();
        // TODO: do generate dungeon in dependencies of type
        return dungeonInstance;
    }

}
