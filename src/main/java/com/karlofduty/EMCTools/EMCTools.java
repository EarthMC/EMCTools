package com.karlofduty.EMCTools;

import com.karlofduty.EMCTools.commands.DocsCommand;
import com.karlofduty.EMCTools.commands.MapCommand;
import com.karlofduty.EMCTools.commands.PremiumCommand;
import com.karlofduty.EMCTools.commands.SupportCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class EMCTools extends JavaPlugin {
    public static EMCTools instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("premium").setExecutor(new PremiumCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("docs").setExecutor(new DocsCommand());
        getCommand("support").setExecutor(new SupportCommand());
    }
}