package com.karlofduty.EMCTools.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.karlofduty.EMCTools.EMCTools;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Command to join the queue to another server
public class JoinQueueCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("§CCannot use this command as console.");
            return false;
        }

        if(args.length < 1)
        {
            sender.sendMessage("§CInvalid arguments.");
            return false;
        }

        if(sender.hasPermission("emc.joinqueue.*") || sender.hasPermission("emc.joinqueue." + args[0]))
        {
            Player player = (Player)sender;
            joinServerQueue(player, args[0]);
        }
        else
        {
            if("beta".equals(args[0]))
            {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "The beta server is only available to players with Premium. " +
                        "Most of the beta features will be available for the public after the beta has ended. " +
                        "If you don't want to wait you can purchase it by clicking here: https://store.earthmc.net/premium");
            }
            else
            {
                sender.sendMessage("§CYou do not have permission to do that.");
            }
        }
        return true;
    }

    public void joinServerQueue(Player player, String target)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(player.getUniqueId().toString());
        out.writeUTF(target);
        player.sendPluginMessage(EMCTools.instance, "queue:join", out.toByteArray());
    }
}

