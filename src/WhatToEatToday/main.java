package WhatToEatToday;

import Command.Favor;
import Command.Clear;
import Command.Info;
import Command.Menu;
import event.GuildMemberJoin;
import event.GuildMemberLeave;
import event.GuildMessageReactionAdd;
import event.GuildMessageReceived;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class main {

    public static JDA jda;
    public static String prefix = "~";

    public static void main(String args[]) throws Exception {

       Object obj = new JSONParser().parse(new FileReader("src/config.json"));

        JSONObject jo = (JSONObject)obj;
        String token = (String)jo.get("token");
        
        jda = new JDABuilder(AccountType.BOT).setToken(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("good recipes"));

        jda.addEventListener(new Info());
        jda.addEventListener(new Clear());
        jda.addEventListener(new Favor());
        jda.addEventListener(new Menu());
        jda.addEventListener(new GuildMemberJoin());
        jda.addEventListener(new GuildMemberLeave());
        jda.addEventListener(new GuildMessageReceived());
        jda.addEventListener(new GuildMessageReactionAdd());

    }
}
