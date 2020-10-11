package event;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceived extends ListenerAdapter {
    public void onGuildMessageReveived(GuildMessageReceivedEvent event){
        event.getMessage().addReaction("🤣").queue();
    }
}
