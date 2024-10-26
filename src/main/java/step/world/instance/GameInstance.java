package step.world.instance;

import net.minestom.server.instance.InstanceContainer;
import step.world.utils.WorldType;

/**
 * Interface for all game instances
 */
public interface GameInstance {
    InstanceContainer getInstanceContainer();

    WorldType getWorldType();

}
