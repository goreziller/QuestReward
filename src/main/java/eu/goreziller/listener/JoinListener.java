package eu.goreziller.listener;

import eu.goreziller.enums.QuestRewardEnum;
import eu.goreziller.objects.CurrentPlayer;
import eu.goreziller.Questreward;
import eu.goreziller.objects.Quest;
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

            Quest currentQuest = (Quest) plugin.getConfig().get("quest1");
            if(plugin.getPlayerConfig().get(event.getPlayer().getName()) == null)
            {
                plugin.getPlayerConfig().set(event.getPlayer().getName(), new CurrentPlayer(playerId, new Quest(currentQuest.getName(), currentQuest.getDescription())));
                plugin.saveConfig(plugin.getPlayerConfig(), plugin.getPlayerFile());
            }

            CurrentPlayer loadedPlayer = (CurrentPlayer) plugin.getPlayerConfig().get(event.getPlayer().getName());
            if(loadedPlayer.getCurrentQuest() != null)
            {
                p.sendMessage(QuestRewardEnum.Prefix.getColoredName() + ChatColor.GOLD + "Current Quest Information");
                p.sendMessage(QuestRewardEnum.Prefix.getColoredName() + ChatColor.BLUE + "Questname: " + loadedPlayer.getCurrentQuestName() + " " +  "Description: " + loadedPlayer.getCurrentQuestDescription());
            }
            else
            {
                p.sendMessage(QuestRewardEnum.Prefix.getColoredName() + ChatColor.BLUE + "You have no active Quest");
            }
        }
    }
}