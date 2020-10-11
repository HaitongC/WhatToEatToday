package Command;

import WhatToEatToday.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(main.prefix + "info")){
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("WhatToEatToday Bot Information");
            info.setDescription("This bot helps you decide what to eat today. Simply type in your desired favor, " +
                    "it gives you some suggestions based on what you entered.\n" +
                    "This bot uses prefix ~ \n");
            //info.addField("Creator", "hchen88", false);
            info.setColor(0xffc524);
            info.setFooter("Created by hchen88", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }

}
