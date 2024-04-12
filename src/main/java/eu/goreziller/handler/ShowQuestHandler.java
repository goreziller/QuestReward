package eu.goreziller.handler;

import eu.goreziller.Questreward;
import eu.goreziller.objects.CurrentPlayer;
import eu.goreziller.objects.Quest;
import me.filoghost.chestcommands.api.Icon;
import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.MenuView;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

public class ShowQuestHandler
{
    private final Questreward instance;
    private int i;
    ItemStack shield = new ItemStack(Material.OAK_SIGN);

    public ShowQuestHandler(Questreward plugin)
    {
        instance = plugin;
    }

    public Menu createShowMenu(Plugin plugin, Player p, String title)
    {
        Menu showMenu = Menu.create(plugin, title, 3);

        for(i = 1; instance.getQuestlist().get(0) != null; i++)
        {
            Icon quest = new Icon()
            {
                @Override
                public @Nullable ItemStack render(@NotNull Player player)
                {
                    ItemMeta questMeta = shield.getItemMeta();
                    ArrayList<String> lorelist = new ArrayList<>();

                    Quest loadedQuest = (Quest) instance.getQuestConfig().get("quest" + i);
                    questMeta.setDisplayName(ChatColor.RED + loadedQuest.getName());

                    lorelist.add("Click for enter Questname");
                    questMeta.setLore(lorelist);
                    shield.setItemMeta(questMeta);
                    return shield;
                }

                @Override
                public void onClick(@NotNull MenuView menuView, @NotNull Player player)
                {

                }
            };
            showMenu.setIcon(1,1,quest);
        }

        return showMenu;
    }
}

