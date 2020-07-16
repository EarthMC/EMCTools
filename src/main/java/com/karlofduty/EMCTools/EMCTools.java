package com.karlofduty.EMCTools;

import com.karlofduty.EMCTools.commands.JoinQueueCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class EMCTools extends JavaPlugin
{
    public static EMCTools instance;

    @Override
    public void onEnable()
    {
        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "queue:join");
        this.getCommand("joinqueue").setExecutor(new JoinQueueCommand());
        this.getCommand("beta").setExecutor(new BetaCommand());
    }

    private class BetaCommand implements CommandExecutor
    {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
        {
            return getServer().dispatchCommand(sender, "joinqueue beta");
        }
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