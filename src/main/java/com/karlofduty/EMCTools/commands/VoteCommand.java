package com.karlofduty.EMCTools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;

public class VoteCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        commandSender.sendMessage(GOLD + "You can vote for us and receive gold on the Towny server while you wait in queue:");
        commandSender.sendMessage(AQUA + (UNDERLINE  + "https://minecraft-mp.com/server/133301/vote/"));
        commandSender.sendMessage(AQUA + (UNDERLINE  + "https://minecraftservers.org/vote/383495/"));
        return true;
    }
}
