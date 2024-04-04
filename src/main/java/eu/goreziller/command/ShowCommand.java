package eu.goreziller.command;

import eu.goreziller.Quest;
import eu.goreziller.Questreward;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class ShowCommand implements CommandExecutor
{
    private final Questreward plugin;
    private UUID playerID;
    private eu.goreziller.Player player;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("&aNur Spieler dürfen diesen Befehl nutzen.");
            return false;
        }
        else
        {
            Player p = (Player) sender;
            playerID =  p.getUniqueId();
            player = plugin.getPlay(playerID);
            Quest currentQuest = (Quest) plugin.getConfig().get("test");
            player.setCurrentQuest(currentQuest);
            if(p.hasPermission("questreward.show"))
            {
                if(args.length == 0)
                {
                    if(player.getCurrentQuest() != null)
                    {
                        p.sendMessage(ChatColor.GOLD + "Current Quest Information");
                        p.sendMessage(ChatColor.BLUE + "Questname: " + currentQuest.getName() + "Description: " + currentQuest.getDescription());
                    }
                    else
                    {
                        plugin.getShowHandler().createShowMenu(plugin, p, ChatColor.GOLD + "QuestReward").open(p);
                    }
                }
            }
            else
            {
                sender.sendMessage("You dont have permission to use this command");
                return false;
            }
        }

        return false;
    }
}
