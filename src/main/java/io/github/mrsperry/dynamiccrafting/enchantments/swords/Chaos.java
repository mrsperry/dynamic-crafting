package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import com.google.common.collect.Lists;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.Sound;
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
        if (seed >= 60 && seed < 80) {
            if (random.nextInt(5) == 0) {
                if (random.nextBoolean()) {
                    target.setFireTicks(100);
                } else {
                    target.getWorld().strikeLightning(target.getLocation());
                }
            } else {
                applyRandomEffect(target);
                target.getWorld().playSound(target.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 6.0f, 1);
            }
        } else if (seed >= 80 && seed < 90) {
            LivingEntity damager = (LivingEntity) event.getDamager();
            applyRandomEffect(damager);
            damager.getWorld().playSound(damager.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 6.0f, 1);
        } else if (seed >= 90) {
            switch (random.nextInt(7)) {
                case 0:
                    Distortion.playEffect(event);
                    break;
                case 1:
                    Draining.playEffect(event);
                    break;
                case 2:
                    Freezing.playEffect(event);
                    break;
                case 3:
                    Polymorph.playEffect(event);
                    break;
                case 4:
                    StaticDischarge.playEffect(event);
                    break;
                case 5:
                    Vampiric.playEffect(event);
                    break;
                case 6:
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
        int amplifier = random.nextInt(2);
        entity.addPotionEffect(new PotionEffect(type, duration, amplifier, false));
    }
}
