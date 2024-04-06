package eu.goreziller.objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SerializableAs("Quest")
public class Quest implements ConfigurationSerializable
{
    ArrayList<Quest> activeQuest = new ArrayList<>();
    private String name;
    private String description;
    private Reward rewards;
    private int remainingTime;

    public Quest(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Quest(String name, String description, Reward rewards)
    {
        this.name = name;
        this.description = description;
        this.rewards = rewards;
    }

    public Quest(String name, String description, Reward rewards, int remainingTime)
    {
        this.name = name;
        this.description = description;
        this.rewards = rewards;
        this.remainingTime = remainingTime;
    }

    public void addToList(Quest quest)
    {
        activeQuest.add(quest);
    }

    public ArrayList<Quest> getActiveQuest()
    {
        return activeQuest;
    }

    public void setActiveQuest(ArrayList<Quest> activeQuest)
    {
        this.activeQuest = activeQuest;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> quest = new HashMap<>();
        quest.put("questname", name);
        quest.put("description", description);
        return quest;
    }

    public static Quest deserialize(Map<String, Object> serialized)
    {
        String name = (String) serialized.get("questname");
        String description = (String) serialized.get("description");
        return new Quest(name, description);
    }

    @Override
    public String toString()
    {
        return name + " " + description;
    }
}
