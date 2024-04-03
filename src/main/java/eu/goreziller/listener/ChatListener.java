package eu.goreziller.listener;

import eu.goreziller.Questreward;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener
{
    String message;
    private boolean waitForChat = false;

    public void waitForChat()
    {
        waitForChat = true;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        if (waitForChat)
        {
            message = event.getMessage();
            waitForChat = false;
        }
    }

    public String getMessage()
    {
        return message;
    }
}
