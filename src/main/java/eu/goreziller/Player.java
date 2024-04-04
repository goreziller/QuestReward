package eu.goreziller;

import java.util.UUID;

public class Player
{
    private UUID playerId;
    private Quest currentQuest;

    public Player(UUID playerId)
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
        if (currentQuest != null)
        {
            return currentQuest.getName();
        }
        else
        {
            return "No current quest";
        }
    }
}