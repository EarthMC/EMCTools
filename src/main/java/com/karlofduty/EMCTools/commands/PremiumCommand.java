package com.karlofduty.EMCTools.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PremiumCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String message = "Click here to find out more about EarthMC premium!";
		if (sender.hasPermission("group.premium"))
			message = "Wow, thanks for supporting the server with your Premium subscription! You are amazing!";
		
		TextComponent premiumComponent = Component.text(message, NamedTextColor.LIGHT_PURPLE)
			.hoverEvent(HoverEvent.showText(Component.text("Open EarthMC.net Premium information.", NamedTextColor.DARK_PURPLE)))
			.clickEvent(ClickEvent.openUrl("https://store.earthmc.net/premiumcommand"));

		sender.sendMessage(premiumComponent);
		return true;
	}
}
