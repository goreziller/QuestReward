package eu.goreziller.enums;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum QuestRewardEnum
{
    Prefix("[QuestReward] ", ChatColor.DARK_PURPLE, "§b§o");

    private QuestRewardEnum(String name, ChatColor color, String colorNative)
    {
        this.name = name;
        this.color = color;
        this.colorNative = colorNative;
    }

    @Getter
    private String name;
    @Getter
    private ChatColor color;
    @Getter
    private String colorNative;

    public String getColoredName()
    {
        return color + name;
    }

    public String getColoredNameNative()
    {
        return colorNative + name;
    }
}
