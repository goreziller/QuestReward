package eu.goreziller.command;

import eu.goreziller.Questreward;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class EditCommand implements CommandExecutor
{
    private final Questreward plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("&aOnly players may use this command");
            return false;
        }
        else
        {
            Player p = (Player) sender;
            if(p.hasPermission("questreward.edit"))
            {
                if(args.length == 0)
                {
                    plugin.getEditHandler().createEditMenu(plugin, p, ChatColor.GOLD + "Edit Quest").open(p);
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
