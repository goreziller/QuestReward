package eu.goreziller.handler;

import eu.goreziller.objects.Quest;
import eu.goreziller.Questreward;
import eu.goreziller.objects.Reward;
import eu.goreziller.listener.ChatListener;
import me.filoghost.chestcommands.api.Icon;
import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.MenuView;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateQuestHandler
{
    private final Questreward instance;
    private final ChatListener listener;
    private Menu createQuestMenu;
    ItemStack shield = new ItemStack(Material.OAK_SIGN);
    private String questname = null;
    private String description = null;
    private boolean waitingForInput = false;
    private Reward rewards = null;
    private boolean waitingForNameInput = false;
    private BukkitTask task;

    public CreateQuestHandler(Questreward plugin, ChatListener listener)
    {
        instance = plugin;
        this.listener = listener;
    }

    public Menu createQuestMenu(Plugin plugin, Player p, String title)
    {
        createQuestMenu = Menu.create(plugin, title, 3);

        createQuestMenu.setIcon(1, 1, createNameIcon());
        createQuestMenu.setIcon(1, 4, createDescriptionIcon());
        createQuestMenu.setIcon(1, 7, createRewardIcon());
        createQuestMenu.setIcon(2, 4, createConfirmIcon());
        cancelTask();

        return createQuestMenu;
    }

    private Icon createNameIcon()
    {
        return new Icon()
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
                player.closeInventory();
                if (!waitingForNameInput)
                {
                    player.sendMessage(ChatColor.BLUE + "Please enter your Questname");
                    waitingForNameInput = true;
                    listener.waitForChat(player).thenAccept(message ->
                    {
                        questname = message;
                        receiveQuestName(player, questname);
                    });
                }
            }
        };
    }

    private Icon createDescriptionIcon()
    {
        return new Icon()
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
                player.closeInventory();
                player.sendMessage(ChatColor.BLUE + "Please enter your description");
                listener.waitForChat(player).thenAccept(message ->
                {
                    description = message;
                    receiveDescription(player, description);
                });
            }
        };
    }

    private Icon createRewardIcon()
    {
        return new Icon()
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
    }

    private Icon createConfirmIcon()
    {
        return new Icon()
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
                if (questname == null || description == null || rewards == null)
                {
                    player.sendMessage("Please select all arrguments");
                }
                else
                {
                    Quest test = new Quest(questname, description, rewards);
                    test.addToList(test);
                    player.sendMessage(test.getActiveQuest().toString());
                }
            }
        };
    }

    public void receiveQuestName(Player player, String questName)
    {
        this.questname = questName;
        waitingForInput = false;
        listener.unregisterListener();
        player.sendMessage(questName);
        task = Bukkit.getScheduler().runTask(instance, () ->
        {
            Questreward.getCreateHandler().createQuestMenu(instance, player, "Create Quest").open(player);
        });
    }

    public void receiveDescription(Player player, String description)
    {
        this.description = description;
        waitingForInput = false;
        listener.unregisterListener();
        player.sendMessage(description);
        Bukkit.getScheduler().runTask(instance, () ->
        {
            if(description != null)
            {
                Questreward.getCreateHandler().createQuestMenu(instance, player, "Create Quest").open(player);
            }
        });
    }

    public void cancelTask()
    {
        if(task != null)
        {
            task.cancel();
        }
    }
}