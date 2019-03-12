package com.karlofduty.EMCTools.commands;

import com.karlofduty.EMCTools.EMCTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// Command to safely restart the server
public class SafeRestartCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender.hasPermission("emc.saferestart"))
        {
            EMCTools.executeCommand("broadcast Server is restarting, this usually takes about 1 minute.");
            EMCTools.executeCommand("whitelist on");
            EMCTools.executeCommand("tne save");
            EMCTools.instance.getServer().getScheduler().scheduleSyncDelayedTask(EMCTools.instance, () -> EMCTools.executeCommand("stop"), 1);
        }
        else
        {
            sender.sendMessage("§CYou do not have permission to do that.");
        }
        return true;
    }
}
