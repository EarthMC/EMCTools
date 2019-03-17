package com.karlofduty.EMCTools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        commandSender.sendMessage(ChatColor.AQUA + "You can join the Discord server at " + ChatColor.BLUE + "https://discord.gg/6rAnxd8§3!");
        return true;
    }
}
