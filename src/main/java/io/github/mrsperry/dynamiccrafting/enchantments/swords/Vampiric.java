package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.Sound;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Vampiric extends CustomEnchantment {
    public Vampiric() {
        super("VAMPIRIC", "Vampiric", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (Utils.checkEntities(event.getEntity(), event.getDamager(), "VAMPIRIC")) {
            playEffect(event);
        }
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        float damage = (float) Math.floor(event.getDamage() / 10);
        damage += damage % 0.5f;

        Player player = (Player) event.getDamager();
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 2, 2);
        double health = player.getHealth();
        player.setHealth(health >= 20 ? 20f : health + damage);
    }
}
