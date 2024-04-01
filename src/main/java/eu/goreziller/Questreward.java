package eu.goreziller;

import eu.goreziller.command.CreateCommand;
import eu.goreziller.handler.QuestRewardHandler;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Questreward extends JavaPlugin
{
    static {
        ConfigurationSerialization.registerClass(Quest.class, "Quest");
    }

    @Getter
    private static Questreward _instance;
    @Getter
    private static QuestRewardHandler _spawnHandler;

    public static QuestRewardHandler getSpawnHandler()
    {
        return _spawnHandler;
    }

    private File file;
    private FileConfiguration config;
    public static Questreward getInstance()
    {
        return _instance;
    }

    @Override
    public void onEnable()
    {
        System.out.println(ChatColor.GREEN + "QuestReward has loaded");

        _instance = this;
        _spawnHandler = new QuestRewardHandler(_instance);

        createFolder();
        registerCommands();
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
        _instance.getConfig().set("test", new Quest("quest1", "do something"));
        //config.set("test", new Quest("quest1", "do something"));
        try
        {
            _instance.saveConfig();
            config.load(file);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void registerCommands()
    {
        getCommand("createquest").setExecutor(new CreateCommand(_instance));
    }

    @Override
    public void onDisable()
    {
        System.out.println(ChatColor.RED + "QuestReward has been disabled");
    }
}
