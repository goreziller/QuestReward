package eu.goreziller;

public class Quest
{
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
}
