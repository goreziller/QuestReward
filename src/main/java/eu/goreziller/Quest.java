package eu.goreziller;

import java.util.ArrayList;

public class Quest
{
    ArrayList<Quest> activeQuest = new ArrayList<>();
    private String name;
    private String description;
    private Reward rewards;
    private int remainingTime;

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
}
