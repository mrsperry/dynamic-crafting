package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Draining extends CustomEnchantment {
    public Draining() {
        super("DRAINING", "Draining", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (Utils.checkEntities(event.getEntity(), event.getDamager(), "DRAINING")) {
            playEffect(event);
        }
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        float damage = (float) Math.floor(event.getDamage() / 10);
        damage += damage % 1;

        Player player = (Player) event.getDamager();
        int level = player.getFoodLevel();
        player.setFoodLevel(level >= 20 ? 20 : level + (int) damage);
    }
}
