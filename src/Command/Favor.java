package Command;

import Recipes.*;
import WhatToEatToday.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import java.util.Random;

public class Favor extends ListenerAdapter {

   Chinese chineseFood = new Chinese();
   American americanFood = new American();
   Spanish spanishFood = new Spanish();
   Japanese japaneseFood = new Japanese();
   French frenchFood = new French();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        EmbedBuilder favor = new EmbedBuilder();
        Map<String, String[]> map = new HashMap<>();
        String[] temp = {"initializer"};
        Random rand = new Random();
        boolean hasIngredient = false;
        int index;
        String[] country = {"chinese", "american", "spanish", "japanese", "french"};

        if(args[0].charAt(1) == 'a' || args[0].charAt(1) == 'e' || args[0].charAt(1) == 'i'
                || args[0].charAt(1) == 'o' || args[0].charAt(1) == 'u'){
            favor.setTitle("Want an " + args[0].substring(1) + " favor dish with " + args[1] + "?");
        } else {
            favor.setTitle("Want a " + args[0].substring(1) + " favor dish with " + args[1] + "?");
        }


        if(Arrays.asList(country).contains(args[0].substring(1))) {

            if (args[0].substring(1).equalsIgnoreCase("chinese")) {
              map.put("egg", chineseFood.egg);
              map.put("chicken", chineseFood.chicken);

              if(Arrays.asList(chineseFood.ingredient).contains(args[1])) {
                    temp = map.get(args[1]);
                    hasIngredient = true;
                }


             } else if (args[0].substring(1).equalsIgnoreCase("american")) {
                map.put("egg", americanFood.egg);
                map.put("chicken", americanFood.chicken);

                if(Arrays.asList(americanFood.ingredient).contains(args[1])) {
                    temp = map.get(args[1]);
                    hasIngredient = true;
                }


            } else if (args[0].substring(1).equalsIgnoreCase("spanish")) {
                map.put("egg", spanishFood.egg);
                map.put("chicken", spanishFood.chicken);

                if(Arrays.asList(spanishFood.ingredient).contains(args[1])) {
                    temp = map.get(args[1]);
                    hasIngredient = true;
                }

            } else if(args[0].substring(1).equalsIgnoreCase("japanese")){
                map.put("egg", japaneseFood.egg);
                map.put("chicken", japaneseFood.chicken);

                if(Arrays.asList(japaneseFood.ingredient).contains(args[1])){
                    temp = map.get(args[1]);
                    hasIngredient = true;
                }

            } else if(args[0].substring(1).equalsIgnoreCase("french")){
                map.put("egg", frenchFood.egg);
                map.put("chicken", frenchFood.chicken);

                if(Arrays.asList(frenchFood.ingredient).contains(args[1])){
                    temp = map.get(args[1]);
                    hasIngredient = true;
                }
            }

            index = rand.nextInt(temp.length);



            if(hasIngredient) {
                favor.setDescription("It seems that you have " + args[1] + "\nHow about making a " + temp[index]);
            }
            else{
                favor.setDescription("Sorry, we don't have " + args[1] + " in our recipes yet.\n" +
                        "Contact us and it will be coming soon!");
            }


        } else{
            favor.setDescription("Sorry, we are not available to provide " + args[0].substring(1) + " recipes yet. \n" +
                    "Contact us and it will be coming soon!");
        }



        favor.setColor(0xE6291C);
        favor.setFooter("Suggested by hchen88", event.getMember().getUser().getAvatarUrl());

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(favor.build()).queue();
        favor.clear();


    }
}
