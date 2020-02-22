package io.github.mrsperry.dynamiccrafting;

import javafx.util.Pair;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
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

    public static Pair<Player, Arrow> getShooterIfPlayer(Entity entity) {
        if(entity instanceof  Arrow) {
            Arrow arrow = (Arrow) entity;
            if(arrow.getShooter() instanceof  Player) {
                Player player = (Player) arrow.getShooter();
                return new Pair(player, arrow);
            }
        }
        return null;
    }
}
