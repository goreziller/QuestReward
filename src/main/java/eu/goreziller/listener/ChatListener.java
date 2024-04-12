package eu.goreziller.listener;

import eu.goreziller.Questreward;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ChatListener implements Listener
{
    private Questreward plugin;
    private Player playerWaitingFor;
    private CompletableFuture<String> messageFuture = new CompletableFuture<>();

    public ChatListener(Questreward plugin)
    {
        this.plugin = plugin;
    }

    public CompletableFuture<String> waitForChat(Player player) {
        messageFuture = new CompletableFuture<>();
        playerWaitingFor = player;

        plugin.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerChat(AsyncPlayerChatEvent event) {
                if (event.getPlayer().equals(playerWaitingFor)) {
                    event.setCancelled(true);
                    messageFuture.complete(event.getMessage());
                    playerWaitingFor = null;
                }
            }
        }, plugin);
        return messageFuture;
    }


    public void unregisterListener()
    {
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }
}
