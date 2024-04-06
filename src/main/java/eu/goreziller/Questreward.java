package eu.goreziller;

import eu.goreziller.command.CancelCommand;
import eu.goreziller.command.CreateCommand;
import eu.goreziller.command.ShowCommand;
import eu.goreziller.handler.CreateQuestHandler;
import eu.goreziller.handler.EditQuestHandler;
import eu.goreziller.handler.ShowQuestHandler;
import eu.goreziller.listener.ChatListener;
import eu.goreziller.listener.JoinListener;
import eu.goreziller.objects.CurrentPlayer;
import eu.goreziller.objects.Quest;
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
    private HashMap<UUID, CurrentPlayer> players = new HashMap<>();
    ArrayList<Quest> questlist = new ArrayList<>();
    @Getter
    private static Questreward plugin;
    @Getter
    private static CreateQuestHandler createHandler;
    @Getter
    private static ShowQuestHandler showHandler;
    @Getter
    private static EditQuestHandler editHandler;
    private ChatListener chatListener;
    private JoinListener joinListener;
    private File questFile;
    private File playerFile;
    private FileConfiguration questConfig;
    private FileConfiguration playerConfig;

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
    }

    private void createFolder()
    {
        questFile = new File(getDataFolder(), "config.yml");
        playerFile = new File(getDataFolder(), "player.yml");
        if (!questFile.exists())
        {
            questFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        if(!playerFile.exists())
        {
            playerFile.getParentFile().mkdirs();
            saveResource("player.yml", false);
        }

        questConfig = new YamlConfiguration();
        playerConfig = new YamlConfiguration();

        plugin.getQuestConfig().set("test", new Quest("quest1", "do something"));

        try
        {
            saveConfig(questConfig, questFile);
            questConfig.load(questFile);
            playerConfig.load(playerFile);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void onload()
    {
        questlist.add((Quest) plugin.getQuestConfig().get("test"));
    }

    public void registerCommands()
    {
        getCommand("createquest").setExecutor(new CreateCommand(plugin));
        getCommand("quest").setExecutor(new ShowCommand(plugin));
        getCommand("cancelquest").setExecutor(new CancelCommand(plugin));
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
        ConfigurationSerialization.registerClass(CurrentPlayer.class, "Player");
    }

    public void saveConfig(FileConfiguration config, File configFile)
    {
        try
        {
            config.save(configFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

    public CurrentPlayer getPlay(UUID playerID)
    {
        return players.get(playerID);
    }

    public FileConfiguration getQuestConfig()
    {
        return questConfig;
    }

    public FileConfiguration getPlayerConfig()
    {
        return playerConfig;
    }

    public File getPlayerFile()
    {
        return playerFile;
    }

    public File getQuestFile()
    {
        return questFile;
    }

    public HashMap<UUID, CurrentPlayer> getPlayers()
    {
        return players;
    }
}