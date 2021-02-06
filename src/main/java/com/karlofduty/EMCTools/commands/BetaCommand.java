package com.karlofduty.EMCTools.commands;

import com.karlofduty.EMCTools.EMCTools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class BetaCommand implements CommandExecutor, TabCompleter 
{
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 1)
            return Arrays.asList("-skipconfirmation");
        else
            return Collections.emptyList();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof ConsoleCommandSender)
        {
            sender.sendMessage("§cCannot use this command as console.");
            return true;
        }

        Player player = (Player) sender;
        if (EMCTools.awaitingBetaConfirmation.containsKey(player.getUniqueId()) || (args.length > 0 && args[0].toLowerCase().equals("-skipconfirmation"))) // Player is accepting the confirmation.
        {
            Bukkit.getServer().dispatchCommand(sender, "joinqueue beta");
            if (EMCTools.awaitingBetaConfirmation.containsKey(player.getUniqueId()))
                EMCTools.awaitingBetaConfirmation.remove(player.getUniqueId());
            return true;
        }

        EMCTools.awaitingBetaConfirmation.put(player.getUniqueId(), (System.currentTimeMillis() + 20 * 1000));
        sender.spigot().sendMessage(new TextComponent(ChatColor.GREEN + "Run the /beta command again to be sent to the beta server."));
        sender.spigot().sendMessage(new TextComponent(ChatColor.GREEN + "You will be sent to the beta server. Use the /towny command to re-queue for Towny."));
        return true;
    }
}
