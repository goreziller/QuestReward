package eu.goreziller.listener;

import eu.goreziller.Player;
import eu.goreziller.Questreward;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener
{
    private Questreward plugin;

    public JoinListener(Questreward plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        // When a player joins, create a new Player object and store it
        UUID playerId = event.getPlayer().getUniqueId();
        if (!plugin.getPlayers().containsKey(playerId))
        {
            Player player = new Player(playerId);
            plugin.getPlayers().put(playerId, player);
            plugin.getLogger().info("Player " + playerId + " joined the game.");
        }
    }
}