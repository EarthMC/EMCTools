package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapCommand implements CommandExecutor, TabCompleter {

    private final TextComponent mapMessage = Component.text("Click here to open the EarthMC map in your browser.").color(NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl("https://earthmc.net/map/")).hoverEvent(HoverEvent.showText(Component.text("Click me to open!").color(NamedTextColor.GREEN)));

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1)
            return Stream.of("me", "link").filter(string -> string.startsWith(args[0].toLowerCase())).collect(Collectors.toList());

        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage(mapMessage);
            return true;
        }

        if (args[0].equalsIgnoreCase("me") && sender instanceof Player && ((Player) sender).getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
            Location location = ((Player) sender).getLocation();
            sender.sendMessage(Component.text("Click here to open the EarthMC map, centered on your current position.").color(NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl("https://earthmc.net/map/?worldname=earth&mapname=flat&zoom=4&x=" + location.getBlockX() + "&y=64&z=" + location.getBlockZ())).hoverEvent(HoverEvent.showText(Component.text("Click me to open!").color(NamedTextColor.GREEN))));
        } else
            sender.sendMessage(mapMessage);

        return true;
    }
}
