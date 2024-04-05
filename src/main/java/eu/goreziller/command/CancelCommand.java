package eu.goreziller.command;

import eu.goreziller.objects.CurrentPlayer;
import eu.goreziller.Questreward;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class CancelCommand implements CommandExecutor
{
    private final Questreward plugin;
    private UUID playerID;
    private CurrentPlayer currentPlayer;
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
            currentPlayer = plugin.getPlay(playerID);

            if(p.hasPermission("questreward.cancel"))
            {
                if(args.length == 0)
                {
                    currentPlayer.setCurrentQuest(null);
                    p.sendMessage(ChatColor.RED + "You have canceled your current quest");
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
