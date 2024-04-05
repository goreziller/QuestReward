package eu.goreziller.listener;

import eu.goreziller.CurrentPlayer;
import eu.goreziller.Questreward;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
        Player p = event.getPlayer();
        UUID playerId = event.getPlayer().getUniqueId();
        if (!plugin.getPlayers().containsKey(playerId))
        {
            CurrentPlayer currentPlayer = new CurrentPlayer(playerId);
            plugin.getPlayers().put(playerId, currentPlayer);
            plugin.getLogger().info("Player " + playerId + " joined the game.");

            if(currentPlayer.getCurrentQuest() != null)
            {
                p.sendMessage(ChatColor.GOLD + "Current Quest Information");
                p.sendMessage(ChatColor.BLUE + "Questname: " + currentPlayer.getCurrentQuestName() + "Description: " + currentPlayer.getCurrentQuestDescription());
            }
            else
            {
                p.sendMessage("You have no active Quest");
            }
        }
    }
}