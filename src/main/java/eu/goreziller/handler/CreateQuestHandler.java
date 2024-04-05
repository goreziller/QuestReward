package eu.goreziller.handler;

import eu.goreziller.objects.Quest;
import eu.goreziller.Questreward;
import eu.goreziller.objects.Reward;
import eu.goreziller.listener.ChatListener;
import me.filoghost.chestcommands.api.Icon;
import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.MenuView;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;

public class CreateQuestHandler
{
    private final Questreward _instance;
    private final ChatListener listener;
    public CreateQuestHandler(Questreward instance, ChatListener listener)
    {
        _instance = instance;
        this.listener = listener;
    }
    ItemStack shield = new ItemStack(Material.OAK_SIGN);
    private String questname = null;
    private String description = null;
    private Reward rewards = null;
    public Menu createQuestMenu(Plugin plugin, Player p, String title)
    {
        Menu createQuestMenu = Menu.create(plugin, title, 3);

        Icon name = new Icon()
        {
            @Override
            public @Nullable ItemStack render(@NotNull Player player)
            {
                ItemMeta questNameMeta = shield.getItemMeta();
                ArrayList<String> lorelist = new ArrayList<>();
                questNameMeta.setDisplayName(ChatColor.RED + "Questname");
                lorelist.add("Click for enter Questname");
                questNameMeta.setLore(lorelist);
                shield.setItemMeta(questNameMeta);
                return shield;
            }

            @Override
            public void onClick(@NotNull MenuView menuView, @NotNull Player player)
            {
                AnvilGUI builder = new AnvilGUI.Builder()
                        .onClose(stateSnapshot ->
                        {
                            stateSnapshot.getPlayer().sendMessage(stateSnapshot.getText());
                            questname = stateSnapshot.getText();

                            createQuestMenu.open(p);
                        })
                        .onClick((slot, stateSnapshot) ->
                        {
                            return Collections.emptyList();
                        })
                        .text("")
                        .title("Enter your Questname")
                        .plugin(_instance)
                        .open(p);
            }
        };

        createQuestMenu.setIcon(1 , 1, name);

        Icon descriptionIcon = new Icon()
        {
            @Override
            public @Nullable ItemStack render(@NotNull Player player)
            {
                ItemMeta descriptionMeta = shield.getItemMeta();
                ArrayList<String> lorelist = new ArrayList<>();
                descriptionMeta.setDisplayName(ChatColor.RED + "Description");
                lorelist.add("Click for enter description");
                descriptionMeta.setLore(lorelist);
                shield.setItemMeta(descriptionMeta);
                return shield;
            }

            @Override
            public void onClick(@NotNull MenuView menuView, @NotNull Player player)
            {
                p.closeInventory();
                player.sendMessage(ChatColor.BLUE + "Please enter your description");
                if(p.getName().equalsIgnoreCase("waitforchat") && listener.getMessage() == null)
                {
                    listener.waitForChat();
                }
                else
                {
                    description = listener.getMessage();
                    p.sendMessage(description);
                }

            }
        };

        createQuestMenu.setIcon(1, 4 , descriptionIcon);

        Icon rewardIcon = new Icon()
        {
            @Override
            public @Nullable ItemStack render(@NotNull Player player)
            {
                ItemMeta rewardMeta = shield.getItemMeta();
                ArrayList<String> lorelist = new ArrayList<>();
                rewardMeta.setDisplayName(ChatColor.RED + "Rewards");
                lorelist.add("Click for configure rewards");
                rewardMeta.setLore(lorelist);
                shield.setItemMeta(rewardMeta);
                return shield;
            }

            @Override
            public void onClick(@NotNull MenuView menuView, @NotNull Player player)
            {

            }
        };

        createQuestMenu.setIcon(1,7,rewardIcon);

        Icon confirm = new Icon()
        {
            @Override
            public @Nullable ItemStack render(@NotNull Player player)
            {
                ItemStack confirmButton = new ItemStack(Material.LIME_WOOL);
                ItemMeta confirmMeta = confirmButton.getItemMeta();
                ArrayList<String> lorelist = new ArrayList<>();
                confirmMeta.setDisplayName(ChatColor.RED + "Create Quest");
                lorelist.add("Click for create the Quest");
                confirmMeta.setLore(lorelist);
                confirmButton.setItemMeta(confirmMeta);
                return confirmButton;
            }

            @Override
            public void onClick(@NotNull MenuView menuView, @NotNull Player player)
            {
                if(questname == null || description == null || rewards == null)
                {
                    p.sendMessage("Please select all arrguments");
                }
                else
                {
                   Quest test = new Quest(questname, description, rewards);
                   test.addToList(test);
                   p.sendMessage(test.getActiveQuest().toString());
                }
            }
        };

        createQuestMenu.setIcon(2,4,confirm);

        return createQuestMenu;

    }
}
