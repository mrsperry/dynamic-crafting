package io.github.mrsperry.dynamiccrafting.enchantments.bows;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Returning extends CustomEnchantment {
    static final double RETURN_CHANCE = 0.50;

    public Returning() {
        super("RETURNING", "Returning", EnchantmentManager.getNextID());
        addEnchantmentTarget(EnchantmentTarget.BOW);
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) { playEffect(event); }

    public static void playEffect(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        if(event.getHitEntity() != null) {
            return;
        }
        if(entity instanceof Arrow) {
            Arrow arrow = (Arrow) entity;
            if(arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                if(EnchantmentManager.hasCustomEnchantment(player.getInventory().getItemInMainHand(), "RETURNING")) {

                }
            }
        }
    }
}
