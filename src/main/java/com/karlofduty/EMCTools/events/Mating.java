package com.karlofduty.EMCTools.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.lang.Math;

public final class Mating implements Listener {

    HashMap<Player, Long> lastSex = new HashMap<>();
    EMCTools instance = EMCTools.instance;

    @EventHandler
    public void sneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) { // If player already mates
            if (event.getPlayer().getPotionEffect(PotionEffectType.SLOW).getAmplifier() == 9) return;
        }

        if (!Tag.BEDS.isTagged(event.getPlayer().getLocation().clone().subtract(0, 0.1, 0).getBlock().getType())) return;
        if (!event.isSneaking()) return;
        Player matePlayer = null;
        for (Entity nearbyEntity : player.getNearbyEntities(2,1,2)) {
            if (nearbyEntity.getType() != EntityType.PLAYER) continue;
            Player nearby = (Player) nearbyEntity;
            if (nearby.isSneaking()) matePlayer = nearby;
        }
        if (matePlayer == null) return;
        if (System.currentTimeMillis() - lastSex.getOrDefault(player, 0L) < 3600000) {
            player.sendMessage(color(String.format("&cWait %s minute(s) to mate again.", 60 - Math.round((System.currentTimeMillis() - lastSex.getOrDefault(player, 0L)) / 60000))));
            return;
        }
        // Start sex :smiling_imp:
        triggerSex(player);
        triggerSex(matePlayer);

        // Play sounds
        int[] loopIndex = {0};

        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(instance, (task) -> {
            if (loopIndex[0] >= 9) task.cancel();
            loopIndex[0]++;
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1, 1);
        }, 0, 20);

        // Put cooldown on mating
        lastSex.put(player, System.currentTimeMillis());
        lastSex.put(matePlayer, System.currentTimeMillis());
    }

    @EventHandler
    public void jump(PlayerJumpEvent event) {
        if (!event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) return;
        if (event.getPlayer().getPotionEffect(PotionEffectType.SLOW).getAmplifier() == 9) event.setCancelled(true);
    }

    @EventHandler
    public void milk(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.MILK_BUCKET) return;
        if (!event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) return;
        if (event.getPlayer().getPotionEffect(PotionEffectType.SLOW).getAmplifier() == 9) event.setCancelled(true);
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
  
    private void triggerSex(Player player) {
        player.sendActionBar(color("&dHave a wonderful time <3"));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 0, true, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*10, 0, true, true));
    }

}
