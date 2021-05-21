package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent component = Component.text("Click here to open the EarthMC map in your browser.").color(NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl("https://earthmc.net/map/"));
        sender.sendMessage(component);
        return true;
    }
}
