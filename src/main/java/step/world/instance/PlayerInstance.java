package step.world.instance;

import net.minestom.server.instance.InstanceContainer;
import step.world.utils.WorldType;

import java.util.UUID;

/**
 * model class for player island instance
 */
public class PlayerInstance extends AbstractGameInstance {
    private final UUID ownerId;
    private long lastActiveTime;

    public PlayerInstance(InstanceContainer instanceContainer, UUID ownerId) {
        super(instanceContainer, WorldType.ISLAND);
        this.ownerId = ownerId;
        this.lastActiveTime = System.currentTimeMillis();
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void updateActivity() {
        lastActiveTime = System.currentTimeMillis();
    }

    public boolean isInactive() {
        // TODO: add good system for definite activity
        return System.currentTimeMillis() - lastActiveTime > 30 * 60 * 1000;
    }

}
