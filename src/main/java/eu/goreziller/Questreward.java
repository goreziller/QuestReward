package eu.goreziller;

import eu.goreziller.command.CreateCommand;
import eu.goreziller.command.ShowCommand;
import eu.goreziller.handler.CreateQuestHandler;
import eu.goreziller.handler.ShowQuestHandler;
import eu.goreziller.listener.ChatListener;
import eu.goreziller.listener.JoinListener;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Questreward extends JavaPlugin
{
    private HashMap<UUID, Player> players = new HashMap<>();
    ArrayList<Quest> questlist = new ArrayList<>();
    @Getter
    private static Questreward plugin;
    @Getter
    private static CreateQuestHandler createHandler;
    @Getter
    private static ShowQuestHandler showHandler;
    private ChatListener chatListener;
    private JoinListener joinListener;
    private File file;
    private FileConfiguration config;

    @Override
    public void onEnable()
    {
        plugin = this;

        registerClass();
        createFolder();
        onload();
        registerCommands();
        registerEvents();

        createHandler = new CreateQuestHandler(plugin, chatListener);
        showHandler = new ShowQuestHandler(plugin);

        System.out.println(ChatColor.GREEN + "QuestReward has loaded");
        System.out.println(questlist.get(0));
    }

    private void createFolder()
    {
        file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        plugin.getConfig().set("test", new Quest("quest1", "do something"));

        try
        {
            plugin.saveConfig();
            config.load(file);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void onload()
    {
        questlist.add((Quest) plugin.getConfig().get("test"));

    }

    public void registerCommands()
    {
        getCommand("createquest").setExecutor(new CreateCommand(plugin));
        getCommand("quest").setExecutor(new ShowCommand(plugin));
    }

    public void registerEvents()
    {
        chatListener = new ChatListener();
        joinListener = new JoinListener(plugin);
        getServer().getPluginManager().registerEvents(chatListener, plugin);
        getServer().getPluginManager().registerEvents(joinListener, plugin);
    }

    public void registerClass()
    {
        ConfigurationSerialization.registerClass(Quest.class, "Quest");
    }

    @Override
    public void onDisable()
    {
        System.out.println(ChatColor.RED + "QuestReward has been disabled");
    }

    public static CreateQuestHandler getCreateHandler()
    {
        return createHandler;
    }

    public static ShowQuestHandler getShowHandler()
    {
        return showHandler;
    }

    public Player getPlay(UUID playerID)
    {
        return players.get(playerID);
    }

    public HashMap<UUID, Player> getPlayers()
    {
        return players;
    }
}