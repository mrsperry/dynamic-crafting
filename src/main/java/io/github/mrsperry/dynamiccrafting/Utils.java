package io.github.mrsperry.dynamiccrafting;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class Utils {
    public static Location getValidLocation(Location center, int radius) {
        return null;
    }

    public static void createPortalEffect(Location location) {
        World world = location.getWorld();
        world.playEffect(location, Effect.PORTAL_TRAVEL, 1, 1);
        world.spawnParticle(Particle.PORTAL, location.add(0.5f, 0.5f, 0.5f), 5, 1, 0.5f, 1);
    }
}
