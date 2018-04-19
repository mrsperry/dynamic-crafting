package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Freezing extends CustomEnchantment {
    public Freezing() {
        super("FREEZING", "Freezing", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
        this.addConflictingEnchantment("FIRE_ASPECT");
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        playEffect(event);
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity target = event.getEntity();
        if (Utils.checkEntities(target, event.getDamager(), "FREEZING")) {
            ((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1));
        }
    }
}
