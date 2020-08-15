package com.karlofduty.EMCTools.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

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

		if(sender.hasPermission("group.premium"))
		{
			sender.sendMessage(new TextComponent(
				new ComponentBuilder("Wow, thanks for supporting the server with your Premium subscription! You are amazing!")
					.color(LIGHT_PURPLE)
					.event(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://store.earthmc.net/premiumcommand"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(DARK_PURPLE + "Open EarthMC.net Premium information.")))
					.create()
			));
		}
		else
		{
			sender.sendMessage(new TextComponent(
				new ComponentBuilder("Click here to find out more about EarthMC premium!")
					.color(LIGHT_PURPLE)
					.event(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://store.earthmc.net/premiumcommand"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(DARK_PURPLE + "Open EarthMC.net Premium information.")))
					.create()
			));
		}
		return true;
	}
}
