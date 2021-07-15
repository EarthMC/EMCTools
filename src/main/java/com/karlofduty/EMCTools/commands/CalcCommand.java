package com.karlofduty.EMCTools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import static net.md_5.bungee.api.ChatColor.*;

public class CalcCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§CCannot use this command as console.");
			return true;
		}
		
		Player player = (Player) sender;
		Block block = player.getTargetBlock(4);
		if (!PlayerCacheUtil.getCachePermission(player, block.getLocation(), block.getType(), TownyPermission.ActionType.SWITCH))
		{
			sender.sendMessage("§CCannot use this command without permission.");
			return true;
		}
		if (block instanceof BlockInventoryHolder)
		{
			float sum = 0;
			Chest chestblock = (Chest) block;
			Inventory inv = chestblock.getBlockInventory();
			sum += inv.all(Material.GOLDEN_INGOT).size();
			sum += inv.all(Material.GOLD_BLOCK).size() * 9;
			sum += inv.all(Material.BEACON).size() * 60;
			sum += inv.all(Material.GOLDEN_APPLE).size() * 8;
			sum += inv.all(Material.DIAMOND_BLOCK).size() * 0.15;
			sum += inv.all(Material.NETHERITE_INGOT).size() * 10;
			sum += inv.all(Material.NETHERITE_BLOCK).size() * 90;
			sum += inv.all(Material.TRIDENT).size() * 1000;
			sum += inv.all(Material.TOTEM_OF_UNDYING).size() * 300;
			sum += inv.all(Material.SPAWNER).size() * 30;
			sum += inv.all(Material.NETHER_STAR).size() * 120;
			sum += inv.all(Material.WITHER_SKELETON_SKULL).size() * 40;
			sum += inv.all(Material.ENCHANTED_GOLDEN_APPLE).size() * 576;
			sum += inv.all(Material.TURTLE_HELMET).size() * 8;
			sum += inv.all(Material.BELL).size() * 54;
			sum += inv.all(Material.DRAGON_SKULL).size() * 800;
			sum += inv.all(Material.NETHERITE_HELMET).size() * 10;
			sum += inv.all(Material.NETHERITE_CHESTPLATE).size() * 10;
			sum += inv.all(Material.NETHERITE_LEGGINGS).size() * 10;
			sum += inv.all(Material.NETHERITE_BOOTS).size() * 10;
			for (ItemStack item : inv.getContents())
			{
				if (item.hasEnchant(Enchantment.MENDING)) sum += 35;
				if (item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 5) sum += 310;
				if (item.getEnchantmentLevel(Enchantment.THORNS) == 5) sum += 1152;
				if (item.getItemMeta() instanceof EnchantmentStorageMeta) 
				{
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
					if (meta.getStoredEnchants().containsKey(Enchantment.MENDING)) sum += 35;
				}
			}
			player.sendMessage("§2This chest contains a sum of §6§LG" + sum + "§7.");
			return true;
		
		} else {
			sender.sendMessage("§CTarget any container to process.");
			return true;
		}
	
		return true;
	}
}
