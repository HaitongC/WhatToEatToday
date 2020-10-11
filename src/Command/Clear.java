package Command;

import WhatToEatToday.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;

public class Clear extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(main.prefix + "clear")){
            event.getChannel().sendMessage("in clear");
            if(args.length < 2){
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(0xff3923);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("usage: " + main.prefix + "clear [# of messages]");
                event.getChannel().sendMessage(usage.build()).queue();
            } else{
                try{
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();

                    //success
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0x22ff2a);
                    success.setTitle("successfully deleted " + args[1] + ".");success.setDescription("between 1-100 messags can be deleted at one time");
                    event.getChannel().sendMessage(success.build()).queue();
                } catch(IllegalArgumentException e){
                    if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")){
                        //too many messages
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("There are too may messages selected");
                        error.setDescription("between 1-100 messags can be deleted at one time");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else{
                        //messages are too old
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("selected messages are older than 2 weeks");
                        error.setDescription("messages older than 2 weeks can not be deleted");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }
            }
        }
    }
}
