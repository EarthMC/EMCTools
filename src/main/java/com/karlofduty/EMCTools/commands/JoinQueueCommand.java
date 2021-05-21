package com.karlofduty.EMCTools.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.karlofduty.EMCTools.EMCTools;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

// Command to join the queue to another server
public class JoinQueueCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof ConsoleCommandSender)
        {
            sender.sendMessage(Component.text("Cannot use this command as console.").color(NamedTextColor.RED));
            return true;
        }

        if (args.length < 1)
        {
            sender.sendMessage(Component.text("Not enough arguments! Usage: /joinqueue [server name]").color(NamedTextColor.RED));
            return true;
        }

        if (sender.hasPermission("emc.joinqueue.*") || sender.hasPermission("emc.joinqueue." + args[0]))
        {
            Player player = (Player) sender;
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF(player.getUniqueId().toString());
            out.writeUTF(args[0]);
            player.sendPluginMessage(EMCTools.instance, "queue:join", out.toByteArray());
        }
        else
        {
            if ("beta".equalsIgnoreCase(args[0]))
            {
                sender.sendMessage(Component.text("The beta server is only available to players with Premium.").color(NamedTextColor.LIGHT_PURPLE).append(Component.newline())
                    .append(Component.text("Most of the beta features will be available for the public after the beta has ended.").color(NamedTextColor.LIGHT_PURPLE)).append(Component.newline())
                    .append(Component.text("If you don't want to wait you can purchase it by clicking here: https://store.earthmc.net/premium").color(NamedTextColor.LIGHT_PURPLE)));
            }
            else
            {
                sender.sendMessage(Component.text("You do not have permission to do that.").color(NamedTextColor.RED));
            }
        }
        return true;
    }
}

