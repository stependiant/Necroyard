package step.world.instance;

import net.minestom.server.instance.InstanceContainer;
import step.world.utils.WorldType;


public abstract class AbstractGameInstance implements GameInstance {
    protected final InstanceContainer instanceContainer;
    protected final WorldType worldType;

    public AbstractGameInstance(InstanceContainer instanceContainer, WorldType worldType) {
        this.instanceContainer = instanceContainer;
        this.worldType = worldType;
    }

    @Override
    public InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    @Override
    public WorldType getWorldType() {
        return worldType;
    }
}
