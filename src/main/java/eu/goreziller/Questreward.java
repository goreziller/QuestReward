package eu.goreziller;

import eu.goreziller.command.CreateCommand;
import eu.goreziller.handler.QuestRewardHandler;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import sun.awt.ComponentFactory;

public final class Questreward extends JavaPlugin
{
    @Getter
    private static Questreward _instance;
    @Getter
    private static QuestRewardHandler _spawnHandler;

    public static QuestRewardHandler getSpawnHandler()
    {
        return _spawnHandler;
    }

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
        registerCommands();
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
