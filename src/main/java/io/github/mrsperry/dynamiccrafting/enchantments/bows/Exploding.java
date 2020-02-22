package io.github.mrsperry.dynamiccrafting.enchantments.bows;

import io.github.mrsperry.dynamiccrafting.Utils;
import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import javafx.util.Pair;
import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Exploding extends CustomEnchantment {

    public Exploding() {
        super("EXPLODING", "Exploding", EnchantmentManager.getNextID());
        addEnchantmentTarget(EnchantmentTarget.BOW);
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        playEffect(event);
    }

    public static void playEffect(ProjectileHitEvent event) {
        Pair<Player, Arrow> shooterData = Utils.getShooterIfPlayer(event.getEntity());
        Player player = (shooterData != null) ? shooterData.getKey() : null;
        Arrow arrow = (shooterData != null) ? shooterData.getValue() : null;

        if(shooterData == null) { return; }

        if(EnchantmentManager.hasCustomEnchantment(player.getInventory().getItemInMainHand(), "EXPLODING")) {
            Location location = arrow.getLocation();
            arrow.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 1f, false, false);
            arrow.remove();
        }
    }
}
