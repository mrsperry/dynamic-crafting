package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class Distortion extends CustomEnchantment {
    public Distortion() {
        super("DISTORTION", "Distortion", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        playEffect(event);
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity target = event.getEntity();
        if (Utils.checkEntities(target, event.getDamager(), "DISTORTION")) {
            Random random = Main.getRandom();
            if (random.nextInt(5) == 0) {
                Location location = target.getLocation();

                int seed = random.nextInt(100);
                if (seed < 60) {
                    event.setDamage(random.nextInt(5) + 1 + event.getDamage());
                } else if (seed >= 60 && seed < 85) {
                    event.setCancelled(true);

                    Location destination = Utils.getValidLocation(location, 10);
                    Utils.createPortalEffect(location);
                    Utils.createPortalEffect(destination);

                    target.teleport(destination);
                } else {
                    event.setCancelled(true);

                    Utils.createPortalEffect(location);

                    target.remove();
                }
            }
        }
    }
}