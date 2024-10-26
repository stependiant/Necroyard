package step;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import step.world.WorldManager;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        WorldManager worldManager = new WorldManager();

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();


        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(worldManager.getMainLobby().getInstanceContainer());
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