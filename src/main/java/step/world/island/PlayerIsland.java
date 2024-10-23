package step.world.island;

import net.minestom.server.instance.InstanceContainer;
import step.world.generator.PlayerIslandGenerator;

import java.util.UUID;

public class PlayerIsland {
    private final InstanceContainer islandInstance;
    private long lastActiveTime;

    public PlayerIsland(UUID ownerId) {
        this.lastActiveTime = System.currentTimeMillis();
        this.islandInstance = PlayerIslandGenerator.loadIsland(ownerId);
    }

    public InstanceContainer getIslandInstance() {
        return islandInstance;
    }

    public void updateActivity() {
        lastActiveTime = System.currentTimeMillis();
    }

    private boolean PlayerOnIsland() {
        // TODO: switch if player join and leave from island
        return true;
    }

    public boolean isInactive() {
        return System.currentTimeMillis() - lastActiveTime > 30*60*1000;
        // TODO: add more good system analytic that player is inactive
    }

}
