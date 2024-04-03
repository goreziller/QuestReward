package eu.goreziller.command;

import eu.goreziller.Questreward;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CreateCommand implements CommandExecutor
{
    private final Questreward _instance;

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
            if(p.hasPermission("questreward.create"))
            {
                if(args.length == 0)
                {
                    _instance.getCreateHandler().createMenu(_instance, p, ChatColor.GOLD + "QuestReward").open(p);
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
