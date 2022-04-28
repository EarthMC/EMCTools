package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class MapCommand implements CommandExecutor, TabCompleter {

    private final String serverName = System.getProperty("name", "unknown").toLowerCase();
    private final TextComponent mapMessage = Component.text("Click here to open the EarthMC map in your browser.", NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl("https://earthmc.net/map/" + serverName + "/")).hoverEvent(HoverEvent.showText(Component.text("Click me to open!", NamedTextColor.GREEN)));

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player && player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
            sender.sendMessage(mapMessage.clickEvent(ClickEvent.openUrl("https://earthmc.net/map/" + serverName + "/?worldname=earth&mapname=flat&zoom=5&x=" + player.getLocation().getBlockX() + "&z=" + player.getLocation().getBlockZ())));
        } else
            sender.sendMessage(mapMessage);

        return true;
    }
}
