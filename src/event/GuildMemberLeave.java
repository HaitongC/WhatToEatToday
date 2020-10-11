package event;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class GuildMemberLeave extends ListenerAdapter {

    String[] messages = {
            "[member] left, the party's over."
    };

    public void onGuildMemberLeave(GuildMemberLeaveEvent event){
        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0xf48342);
        join.setDescription(messages[number].replace("[member]", event.getMember().getAsMention()));

        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();

        //add role
        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName("Mmber", true).get(0)).complete();

    }
}
