package eu.goreziller.handler;

import eu.goreziller.Questreward;
import me.filoghost.chestcommands.api.Menu;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class EditQuestHandler
{
    private final Questreward instance;

    public EditQuestHandler(Questreward plugin)
    {
        instance = plugin;
    }

    public Menu createEditMenu(Plugin plugin, Player p, String title)
    {
        Menu editMenu = Menu.create(plugin, title, 3);

        return editMenu;
    }
}
