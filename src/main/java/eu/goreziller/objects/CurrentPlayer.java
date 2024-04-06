package eu.goreziller.objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SerializableAs("Player")
public class CurrentPlayer implements ConfigurationSerializable
{
    private UUID playerId;
    private Quest currentQuest;

    public CurrentPlayer(UUID playerId)
    {
        this.playerId = playerId;
    }

    public CurrentPlayer(UUID playerID, Quest currentQuest)
    {
        this.playerId = playerID;
        this.currentQuest = currentQuest;
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

    @NotNull
    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> currentPlayer = new HashMap<>();
        currentPlayer.put("playerID", playerId.toString());
        currentPlayer.put("currentQuest", currentQuest);
        return currentPlayer;
    }
}