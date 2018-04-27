package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Trance extends CustomEnchantment {
    private static HashMap<Player, Boolean> decrement = new HashMap<Player, Boolean>();

    public Trance(final JavaPlugin plugin) {
        super("TRANCE", "Trance", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);

        new BukkitRunnable() {
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Player player : world.getPlayers()) {
                        if (decrement.keySet().contains(player)) {
                            ItemStack item = player.getInventory().getItemInMainHand();
                            Map<CustomEnchantment, Integer> enchantments = EnchantmentManager.getCustomEnchantments(item);
                            for (CustomEnchantment enchantment : enchantments.keySet()) {
                                if (enchantment.getEnchantmentName().equals("TRANCE")) {
                                    int level = enchantments.get(enchantment);
                                    if (level > 1) {
                                        item = EnchantmentManager.addUnsafeEnchantment(item, "TRANCE", --level);

                                        decrement.put(player, true);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 20, 0);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (Utils.checkEntities(event.getEntity(), event.getDamager(), "TRANCE")) {
            playEffect(event);
        }
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Player player = (Player) damager;

        int level = 1;
        Map<CustomEnchantment, Integer> enchantments = EnchantmentManager.getCustomEnchantments(player.getInventory().getItemInMainHand());
        for (CustomEnchantment enchantment : enchantments.keySet()) {
            if (enchantment.getEnchantmentName().equals("TRANCE")) {
                level = enchantments.get(enchantment);
            }
        }
    }
}
