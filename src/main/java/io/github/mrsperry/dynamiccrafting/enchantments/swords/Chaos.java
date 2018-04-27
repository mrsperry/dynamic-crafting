package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import com.google.common.collect.Lists;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

public class Chaos extends CustomEnchantment {
    private static Random random = Main.getRandom();

    public Chaos() {
        super("CHAOS", "Chaos", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (Utils.checkEntities(event.getEntity(), event.getDamager(), "CHAOS")) {
            playEffect(event);
        }
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        LivingEntity target = (LivingEntity) event.getEntity();
        int seed = random.nextInt(100);
        Bukkit.broadcastMessage("Seed: " + seed);
        if (seed >= 40 && seed < 60) {
            if (random.nextInt(20) == 0) {
                if (random.nextBoolean()) {
                    target.setFireTicks(15);
                } else {
                    target.getWorld().strikeLightning(target.getLocation());
                }
            } else {
                Bukkit.broadcastMessage("Mob target");
                applyRandomEffect(target);
            }
        } else if (seed >= 60 && seed < 70) {
            Bukkit.broadcastMessage("Player target");
            applyRandomEffect((LivingEntity) event.getDamager());
        } else if (seed >= 70) {
            switch (random.nextInt(7)) {
                case 0:
                    Bukkit.broadcastMessage("Distortion effect");
                    Distortion.playEffect(event);
                    break;
                case 1:
                    Bukkit.broadcastMessage("Drain effect");
                    Draining.playEffect(event);
                    break;
                case 2:
                    Bukkit.broadcastMessage("Freeze effect");
                    Freezing.playEffect(event);
                    break;
                case 3:
                    Bukkit.broadcastMessage("Poly effect");
                    Polymorph.playEffect(event);
                    break;
                case 4:
                    Bukkit.broadcastMessage("Static effect");
                    StaticDischarge.playEffect(event);
                    break;
                case 5:
                    Bukkit.broadcastMessage("Vampiric effect");
                    Vampiric.playEffect(event);
                    break;
                case 6:
                    Bukkit.broadcastMessage("Venom effect");
                    Venomous.playEffect(event);
                    break;
            }
        }
    }

    private static void applyRandomEffect(LivingEntity entity) {
        ArrayList<PotionEffectType> types = Lists.newArrayList(
                PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION, PotionEffectType.DAMAGE_RESISTANCE,
                PotionEffectType.FAST_DIGGING, PotionEffectType.FIRE_RESISTANCE, PotionEffectType.HUNGER,
                PotionEffectType.INCREASE_DAMAGE, PotionEffectType.INVISIBILITY, PotionEffectType.JUMP,
                PotionEffectType.LEVITATION, PotionEffectType.NIGHT_VISION, PotionEffectType.POISON,
                PotionEffectType.REGENERATION, PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING,
                PotionEffectType.SPEED, PotionEffectType.WEAKNESS
        );

        PotionEffectType type = types.get(random.nextInt(types.size()));
        int duration = (random.nextInt(35) + 10) * 20;
        int amplifier = random.nextInt(3);

        Bukkit.broadcastMessage("Added effect: " + type.toString() + " for " + duration + " at " + amplifier);
        entity.addPotionEffect(new PotionEffect(type, duration, amplifier, false));
    }
}
