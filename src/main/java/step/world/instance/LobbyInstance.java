package step.world.instance;

import net.minestom.server.instance.InstanceContainer;
import step.world.utils.WorldType;

/**
 * model class for main lobby instance
 */
public class LobbyInstance extends AbstractGameInstance {

    public LobbyInstance(InstanceContainer instanceContainer) {
        super(instanceContainer, WorldType.LOBBY);
    }
}
