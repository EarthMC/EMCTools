package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DocsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Component.text("Click here for a link to the online docs.").color(NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl("https://earthmc.net/docs/")).hoverEvent(HoverEvent.showText(Component.text("Click me to open!").color(NamedTextColor.GREEN))));
        return true;
    }
}
