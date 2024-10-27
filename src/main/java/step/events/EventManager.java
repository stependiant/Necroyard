package step.events;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import step.world.utils.Tags;
import step.world.utils.WorldType;

public class EventManager {
    private GlobalEventHandler globalEventHandler;

    public EventManager(GlobalEventHandler globalEventHandler) {
        this.globalEventHandler = globalEventHandler;

        EventNode<Event> lobbyEventNode = EventNode.all("Lobby");

        lobbyEventNode.addListener(ItemDropEvent.class, event -> {
            WorldType worldType = event.getInstance().getTag(Tags.WORLD_TYPE_TAG);
            if (worldType == WorldType.LOBBY) {
                event.setCancelled(true);
            }
        });

        globalEventHandler.addChild(lobbyEventNode);

    }





}
