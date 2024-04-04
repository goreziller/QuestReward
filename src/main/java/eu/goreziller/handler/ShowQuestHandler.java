package eu.goreziller.handler;

import eu.goreziller.Questreward;
import me.filoghost.chestcommands.api.Menu;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ShowQuestHandler
{
    private final Questreward instance;

    public ShowQuestHandler(Questreward plugin)
    {
        instance = plugin;
    }

    public Menu createShowMenu(Plugin plugin, Player p, String title)
    {
        Menu createQuestMenu = Menu.create(plugin, title, 3);

        return createQuestMenu;
    }
}

