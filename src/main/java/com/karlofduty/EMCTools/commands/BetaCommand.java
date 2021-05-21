package com.karlofduty.EMCTools.commands;

import com.karlofduty.EMCTools.EMCTools;

import java.util.Collections;
import java.util.List;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class BetaCommand implements CommandExecutor, TabCompleter
{
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 1)
            return Collections.singletonList("-skipconfirmation");
        else
            return Collections.emptyList();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof ConsoleCommandSender)
        {
            sender.sendMessage(Component.text("Cannot use this command as console.").color(NamedTextColor.RED));
            return true;
        }

        Player player = (Player) sender;
        if (EMCTools.awaitingBetaConfirmation.containsKey(player.getUniqueId()) || (args.length > 0 && args[0].toLowerCase().equals("-skipconfirmation"))) // Player is accepting the confirmation.
        {
            Bukkit.getServer().dispatchCommand(sender, "joinqueue beta");
            EMCTools.awaitingBetaConfirmation.remove(player.getUniqueId());
            return true;
        }

        EMCTools.awaitingBetaConfirmation.put(player.getUniqueId(), (System.currentTimeMillis() + 20 * 1000));
        TextComponent component = Component.text("Run the /beta command again to be sent to the beta server.").color(NamedTextColor.GREEN).append(Component.newline())
            .append(Component.text("You will be sent to the beta server. Use /joinqueue towny to re-queue for Towny.").color(NamedTextColor.GREEN));

        sender.sendMessage(component);
        return true;
    }
}
