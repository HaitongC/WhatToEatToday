package Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Menu extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        EmbedBuilder menu = new EmbedBuilder();

        if(args[0].substring(1).equalsIgnoreCase("menu")){
            menu.setTitle("WhatToEatToday Menu");
            menu.setDescription("This menu contains our current recipes, we are expected more to come.\n");
            menu.appendDescription("American:\nchicken\negg\n");
            menu.appendDescription("Chinese:\nchicken\negg\n");
            menu.appendDescription("French:\nchicken\negg\n");
            menu.appendDescription("Japanese:\nchicken\negg\n");
            menu.appendDescription("Spanish:\nchicken\negg");

            menu.setColor(0xEB8E1C);
            menu.setFooter("Created by hchen88", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(menu.build()).queue();
            menu.clear();
        }
    }
}
