package io.github.mrsperry.dynamiccrafting;

import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import javafx.util.Pair;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class Utils {
    public static Location getValidLocation(Location center, int radius) {
        Random random = Main.getRandom();
        World world = center.getWorld();

        int tries = 100;
        do {
            int blockX = center.getBlockX(), blockY = center.getBlockY(), blockZ = center.getBlockZ();
            int x = blockX + (random.nextInt((radius * 2) + 1) - radius);
            int y = blockY + (random.nextInt((radius * 2) + 1) - radius);
            int z = blockZ + (random.nextInt((radius * 2) + 1) - radius);

            Block below = world.getBlockAt(x, y - 1, z);
            Block block = world.getBlockAt(x, y, z);
            Block above = world.getBlockAt(x, y + 1, z);
            Block above2 = world.getBlockAt(x, y + 2, z);
            if (below.getType().isSolid() && checkMaterials(block, above, above2)) {
                return block.getLocation();
            }

            tries--;
        } while (tries > 0);

        return null;
    }

    private static boolean checkMaterials(Block... blocks) {
        for (Block block : blocks) {
            Material type = block.getType();
            if (type != Material.AIR && type != Material.SNOW) {
                return false;
            }
        }
        return true;
    }

    public static void createPortalEffect(Location location) {
        World world = location.getWorld();
        world.playSound(location, Sound.BLOCK_PORTAL_TRAVEL, 1, 1);
        world.spawnParticle(Particle.PORTAL, location.add(0.5f, 0.5f, 0.5f), 50, 1, 0.5f, 1);
    }

    public static void createVoidEffect(Location location) {
        World world = location.getWorld();
        world.playSound(location, Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
        world.spawnParticle(Particle.SUSPENDED_DEPTH, location.add(0.5f, 0.5f, 0.5f), 120, 0.5f, 1f, 0.5f);
    }

    public static boolean checkEntities(Entity target, Entity player, String name) {
        if (target instanceof LivingEntity) {
            if (player instanceof Player) {
                return EnchantmentManager.hasCustomEnchantment(((Player) player).getInventory().getItemInMainHand(), name);
            }
        }
        return false;
    }

    public static boolean willKillEntity(LivingEntity entity, double damage) {
        return damage >= entity.getHealth();
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
