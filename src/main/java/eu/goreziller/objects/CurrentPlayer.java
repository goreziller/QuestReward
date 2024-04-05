package eu.goreziller.objects;

import java.util.UUID;

public class CurrentPlayer
{
    private UUID playerId;
    private Quest currentQuest;

    public CurrentPlayer(UUID playerId)
    {
        this.playerId = playerId;
    }

    public UUID getPlayerId()
    {
        return playerId;
    }

    public Quest getCurrentQuest()
    {
        return currentQuest;
    }

    public void setCurrentQuest(Quest currentQuest)
    {
        this.currentQuest = currentQuest;
    }

    public String getCurrentQuestName()
    {
        return currentQuest.getName();
    }

    public String getCurrentQuestDescription()
    {
        return currentQuest.getDescription();
    }
}