package step;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import step.events.EventManager;
import step.world.WorldManager;
import step.world.utils.Tags;
import step.world.utils.WorldType;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        WorldManager worldManager = new WorldManager();

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        new EventManager(eventHandler);


        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(worldManager.getLobbyInstance().getInstanceContainer());
        });

        eventHandler.addListener(PlayerSpawnEvent.class, event -> {
            Player player = event.getPlayer();
            player.setPermissionLevel(4);
            player.setGameMode(GameMode.CREATIVE);
        });


        MojangAuth.init();
        minecraftServer.start("localhost", 25565);
    }
}