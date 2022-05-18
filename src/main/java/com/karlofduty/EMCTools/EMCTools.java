package com.karlofduty.EMCTools;

import com.karlofduty.EMCTools.commands.ComponentCommand;
import com.karlofduty.EMCTools.commands.MapCommand;
import com.karlofduty.EMCTools.commands.PremiumCommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EMCTools extends JavaPlugin {
    public static EMCTools instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("premium").setExecutor(new PremiumCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("docs").setExecutor(new ComponentCommand(clickableMessage("online docs", "https://earthmc.net/docs/")));
        getCommand("support").setExecutor(new ComponentCommand(clickableMessage("support Discord", "https://discord.gg/EZBYmpp")));
        getCommand("wiki").setExecutor(new ComponentCommand(clickableMessage("online wiki", "https://wiki.earthmc.net/")));
    }

    private Component clickableMessage(String description, String url) {
        return Component.text("Click here for a link to the " + description + ".", NamedTextColor.GREEN).clickEvent(ClickEvent.openUrl(url)).hoverEvent(HoverEvent.showText(Component.text("Click me to open!", NamedTextColor.GREEN)));
    }
}