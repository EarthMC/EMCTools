package com.karlofduty.EMCTools;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import com.karlofduty.EMCTools.commands.BetaCommand;
import com.karlofduty.EMCTools.commands.DocsCommand;
import com.karlofduty.EMCTools.commands.JoinQueueCommand;
import com.karlofduty.EMCTools.commands.MapCommand;
import com.karlofduty.EMCTools.commands.PremiumCommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EMCTools extends JavaPlugin {
    public static EMCTools instance;
    public static Map<UUID, Long> awaitingBetaConfirmation = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "queue:join");
        getCommand("joinqueue").setExecutor(new JoinQueueCommand());
        getCommand("beta").setExecutor(new BetaCommand());
        getCommand("premium").setExecutor(new PremiumCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("docs").setExecutor(new DocsCommand());

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->
        {
            for (Entry<UUID, Long> currentPlayer : awaitingBetaConfirmation.entrySet()) {
                if (System.currentTimeMillis() > currentPlayer.getValue()) //Confirmation is expired
                {
                    awaitingBetaConfirmation.remove(currentPlayer.getKey());
                    Bukkit.getPlayer(currentPlayer.getKey()).sendMessage(Component.text("Beta command confirmation timed out.").color(NamedTextColor.RED));
                }
            }
        }, 100, 100);
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}