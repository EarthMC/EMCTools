package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Command class for generic commands that only return a component
 */
public class ComponentCommand implements CommandExecutor {
    private final Component component;

    public ComponentCommand(@NotNull Component component) {
        this.component = component;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(component);
        return true;
    }
}
