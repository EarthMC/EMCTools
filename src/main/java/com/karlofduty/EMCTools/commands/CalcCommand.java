package com.karlofduty.EMCTools.commands;

import com.palmergames.bukkit.towny.object.TownyPermission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class CalcCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§CCannot use this command as console.");
			return true;
		}

		Player player = (Player) sender;
		Block block = player.getTargetBlock(5);
		if (block == null) return true;
		if (!PlayerCacheUtil.getCachePermission(player, block.getLocation(), block.getType(), TownyPermission.ActionType.SWITCH)) {
			sender.sendMessage("§CCannot use this command without permission.");
			return true;
		}
		if (block.getState() instanceof BlockInventoryHolder) {
			float sum = 0;
			BlockInventoryHolder invhold = (BlockInventoryHolder) block.getState();
			Inventory inv = invhold.getInventory();

			for (ItemStack item : inv.getContents()) {
				if (item == null) continue;
				int amount = item.getAmount();

				switch (item.getType()) {
					case GOLD_INGOT: sum += amount; break;
					case GOLD_BLOCK: sum += amount * 9; break;
					case BEACON: sum += amount * 60; break;
					case GOLDEN_APPLE: sum += amount * 8; break;
					case DIAMOND_BLOCK: sum += amount * 0.15; break;
					case NETHERITE_INGOT: sum += amount * 10; break;
					case NETHERITE_BLOCK: sum += amount * 90; break;
					case TRIDENT: sum += 1000; break;
					case TOTEM_OF_UNDYING: sum += 300; break;
					case TURTLE_HELMET: sum += 8; break;
					case SPAWNER: sum += amount * 30; break;
					case NETHER_STAR: sum += amount * 120; break;
					case WITHER_SKELETON_SKULL: sum += amount * 40; break;
					case ENCHANTED_GOLDEN_APPLE: sum += amount * 576; break;
					case BELL: sum += amount * 54; break;
					case DRAGON_HEAD: sum += amount * 800; break;
					case NETHERITE_HELMET:
					case NETHERITE_CHESTPLATE:
					case NETHERITE_LEGGINGS:
					case NETHERITE_BOOTS:
						sum += 10; break;
				}

				if (item.containsEnchantment(Enchantment.MENDING)) sum += 35;
				if (item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 5) sum += 310;
				if (item.getEnchantmentLevel(Enchantment.THORNS) == 5) sum += 1152;
				if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
					if (meta.getStoredEnchants().containsKey(Enchantment.MENDING)) sum += 35;
				}
			}
			player.sendMessage("§2This chest contains a sum of §6§LG" + Math.round(sum) + "§2.");
			return true;
		
		} else {
			sender.sendMessage("§CTarget any container to process.");
			return true;
		}
	}
}
