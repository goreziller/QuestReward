package eu.goreziller.listener;

import eu.goreziller.Questreward;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
    public CompletableFuture<String> waitForChat(Player player)
    {
        messageFuture = new CompletableFuture<>();
        playerWaitingFor = player;

        plugin.getServer().getPluginManager().registerEvents(new Listener()
        {
            @EventHandler
            public void onPlayerChat(AsyncPlayerChatEvent event)
            {
                if (event.getPlayer().equals(playerWaitingFor))
                {
                    messageFuture.complete(event.getMessage());
                    event.setCancelled(true);
                    AsyncPlayerChatEvent.getHandlerList().unregister(this);
                }
            }
        }, plugin);
        return messageFuture;
    }
}
