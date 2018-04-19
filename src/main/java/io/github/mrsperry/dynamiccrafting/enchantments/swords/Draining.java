package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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
        playEffect(event);
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            float damage = (float) Math.floor(event.getDamage() / 3);
            damage += damage % 0.5f;

            Entity damager = event.getDamager();
            if (damager instanceof Player) {
                Player player = (Player) damager;
                float saturation = player.getSaturation();
                player.setSaturation(saturation >= 20 ? 20f : saturation + damage);
            }
        }
    }
}
