package io.github.mrsperry.dynamiccrafting;

import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import javafx.util.Pair;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Utils {
    public static Location getValidLocation(Location center, int radius) {
        return null;
    }

    public static void createPortalEffect(Location location) {
        World world = location.getWorld();
        world.playEffect(location, Effect.PORTAL_TRAVEL, 1, 1);
        world.spawnParticle(Particle.PORTAL, location.add(0.5f, 0.5f, 0.5f), 5, 1, 0.5f, 1);
    }

    public static boolean checkEntities(Entity target, Entity player, String name) {
        if (target instanceof LivingEntity) {
            if (player instanceof Player) {
                if (EnchantmentManager.hasCustomEnchantment(((Player) player).getInventory().getItemInMainHand(), name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Pair<Player, Arrow> getShooterIfPlayer(ProjectileHitEvent event) {
        if(event.getEntity() instanceof  Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            if(arrow.getShooter() instanceof  Player) {
                Player player = (Player) arrow.getShooter();
                return new Pair(player, arrow);
            }
        }
        return null;
    }
}
