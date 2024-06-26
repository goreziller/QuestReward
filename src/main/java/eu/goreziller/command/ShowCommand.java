package eu.goreziller.command;

import eu.goreziller.enums.QuestRewardEnum;
import eu.goreziller.objects.CurrentPlayer;
import eu.goreziller.objects.Quest;
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
    private CurrentPlayer currentPlayer;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("&aOnly players may use this command.");
            return false;
        }
        else
        {
            Player p = (Player) sender;
            playerID = p.getUniqueId();
            currentPlayer = plugin.getPlay(playerID);

            if(p.hasPermission("questreward.show"))
            {
                if(args.length == 0)
                {
                    if(currentPlayer.getCurrentQuest() != null)
                    {
                        p.sendMessage(QuestRewardEnum.Prefix.getColoredName() + ChatColor.GOLD + "Current Quest Information");
                        p.sendMessage(QuestRewardEnum.Prefix.getColoredName() + ChatColor.BLUE + "Questname: " + currentPlayer.getCurrentQuest().getName() + "Description: " + currentPlayer.getCurrentQuest().getDescription());
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
