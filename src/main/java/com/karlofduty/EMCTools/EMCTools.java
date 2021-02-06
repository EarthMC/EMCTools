package com.karlofduty.EMCTools;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import com.karlofduty.EMCTools.commands.BetaCommand;
import com.karlofduty.EMCTools.commands.JoinQueueCommand;
import com.karlofduty.EMCTools.commands.PremiumCommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class EMCTools extends JavaPlugin
{
    public static EMCTools instance;
    public static Map<UUID, Long> awaitingBetaConfirmation = new HashMap<>();

    @Override
    public void onEnable()
    {
        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "queue:join");
        this.getCommand("joinqueue").setExecutor(new JoinQueueCommand());
        this.getCommand("beta").setExecutor(new BetaCommand());
        this.getCommand("premium").setExecutor(new PremiumCommand());

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->
        {
            for (Entry <UUID,Long> currentPlayer : awaitingBetaConfirmation.entrySet()) {
                if (System.currentTimeMillis() > currentPlayer.getValue()) //Confirmation is expired
                {
                    awaitingBetaConfirmation.remove(currentPlayer.getKey());
                    Bukkit.getPlayer(currentPlayer.getKey()).spigot().sendMessage(new TextComponent(ChatColor.RED + "Beta command confirmation timed out."));
                }
            }
        }, 100, 100);
    }

    public static boolean executeCommand(String command)
    {
        return instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), command);
    }

    public static void log(String message)
    {
        instance.getServer().getLogger().info(message);
    }
}