package eu.goreziller.handler;

import eu.goreziller.Questreward;
import eu.goreziller.Reward;
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
import java.util.Arrays;
import java.util.Collections;

public class QuestRewardHandler
{
    private final Questreward _instance;
    public QuestRewardHandler(Questreward instance)
    {
        _instance = instance;
    }
    ItemStack shield = new ItemStack(Material.OAK_SIGN);
    private String questname = null;
    private String des = null;
    private Reward rewards = null;
    public Menu createMenu(Plugin plugin, Player p, String title)
    {
        Menu createQuestMenu = Menu.create(plugin, title, 3);

        Icon questname = new Icon()
        {
            @Override
            public @org.jetbrains.annotations.Nullable ItemStack render(@org.jetbrains.annotations.NotNull Player player)
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
            public void onClick(@org.jetbrains.annotations.NotNull MenuView menuView, @org.jetbrains.annotations.NotNull Player player)
            {
                new AnvilGUI.Builder()
                        .onClose(stateSnapshot ->
                        {
                            stateSnapshot.getPlayer().sendMessage(stateSnapshot.getText());
                            createQuestMenu.open(p);
                        })
                        .onClick((slot, stateSnapshot) ->
                        {
                            if(slot != AnvilGUI.Slot.OUTPUT)
                            {
                                return Collections.emptyList();
                            }
                            else
                            {
                                createQuestMenu.open(p);
                                return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText("Try again"));
                            }
                        })
                        .text("")
                        .title("Enter your Questname")
                        .plugin(_instance)
                        .open(p);
            }
        };

        createQuestMenu.setIcon(1 , 1, questname);

        Icon description = new Icon()
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

            }
        };

        createQuestMenu.setIcon(1, 4 , description);

        Icon rewards = new Icon()
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

        createQuestMenu.setIcon(1,7,rewards);

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

            }
        };

        createQuestMenu.setIcon(2,4,confirm);

        return createQuestMenu;

    }
}
