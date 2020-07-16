package com.karlofduty.EMCTools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PremiumCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§CCannot use this command as console.");
			return false;
		}

		if(sender.hasPermission("group.premium"))
		{
			sender.sendMessage("§DWow, thanks for supporting the server with your Premium subscription! You are amazing!");
		}
		else
		{
			sender.sendMessage("§DIf you want to support the server you can buy Premium by clicking here: §3https://store.earthmc.net/premium§D. Premium gives you cosmetic perks and queue priority among other things.");
		}
		return true;
	}
}
