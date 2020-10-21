package com.karlofduty.EMCTools.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.md_5.bungee.api.ChatColor.DARK_PURPLE;
import static net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;

public class StoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        sender.sendMessage(new TextComponent(
                new ComponentBuilder("Click here to access the EarthMC store")
                        .color(LIGHT_PURPLE)
                        .event(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://store.earthmc.net/"))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(DARK_PURPLE + "Open EarthMC Store")))
                        .create()
        ));

        return true;
    }
}
