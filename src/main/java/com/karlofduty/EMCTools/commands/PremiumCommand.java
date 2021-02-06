package com.karlofduty.EMCTools.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.md_5.bungee.api.ChatColor.*;

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

		String message = "Click here to find out more about EarthMC premium!";
		if(sender.hasPermission("group.premium"))
			message = "Wow, thanks for supporting the server with your Premium subscription! You are amazing!";
		
		TextComponent premiumMessage = new TextComponent(LIGHT_PURPLE + message);
		premiumMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://store.earthmc.net/premiumcommand"));
		premiumMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(DARK_PURPLE + "Open EarthMC.net Premium information.")));
		
		sender.spigot().sendMessage(premiumMessage);
		return true;
	}
}
