package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Bloodbound extends CustomEnchantment {
    public Bloodbound() {
        super("BLOODBOUND", "Bloodbound", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        playEffect(event);
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (Utils.checkEntities(event.getEntity(), damager, "BLOODBOUND")) {
            event.setDamage(((Player) damager).getHealth() + event.getDamage());
        }
    }
}
