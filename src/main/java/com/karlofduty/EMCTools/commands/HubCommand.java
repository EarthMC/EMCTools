package com.karlofduty.EMCTools.commands;

import com.karlofduty.EMCTools.EMCTools;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class HubCommand implements CommandExecutor {
    private final EMCTools plugin;
    private final boolean isHub = System.getProperty("name", "").toLowerCase(Locale.ROOT).contains("hub");
    private final Set<UUID> awaitingConfirmation = new HashSet<>();

    public HubCommand(EMCTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("This command cannot be used by console.", NamedTextColor.RED));
            return true;
        }

        if (isHub) {
            sender.sendMessage(Component.text("You are already connected to the hub.", NamedTextColor.RED));
            return true;
        }

        if (awaitingConfirmation.contains(player.getUniqueId()) || (args.length > 0 && "confirm".equalsIgnoreCase(args[0]))) {
            sender.sendMessage(Component.text("You are being queued for hub...", NamedTextColor.GREEN));
            awaitingConfirmation.remove(player.getUniqueId());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "cp ppc " + player.getName() + " joinqueue hub confirm");
            return true;
        }

        sender.sendMessage(Component.text("Are you sure you want to join the hub? Run /hub again to confirm.", NamedTextColor.GREEN));

        awaitingConfirmation.add(player.getUniqueId());
        Bukkit.getScheduler().runTaskLater(plugin, () -> awaitingConfirmation.remove(player.getUniqueId()), 600);

        return true;
    }
}
