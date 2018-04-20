package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import com.google.common.collect.Lists;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.Utils;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Random;

public class Polymorph extends CustomEnchantment {
    private static ArrayList<EntityType> friendlies = Lists.newArrayList(
            EntityType.BAT, EntityType.CHICKEN, EntityType.COW, EntityType.DONKEY, EntityType.HORSE,
            EntityType.LLAMA, EntityType.MULE, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PARROT,
            EntityType.PIG, EntityType.POLAR_BEAR, EntityType.RABBIT, EntityType.SHEEP, EntityType.SQUID,
            EntityType.WOLF
    );
    private static ArrayList<EntityType> enemies = Lists.newArrayList(
            EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.ENDERMAN, EntityType.ENDERMITE,
            EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK, EntityType.MAGMA_CUBE,
            EntityType.PIG_ZOMBIE, EntityType.SKELETON, EntityType.SILVERFISH, EntityType.SLIME, EntityType.SPIDER,
            EntityType.STRAY, EntityType.SHULKER, EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH,
            EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER
    );

    public Polymorph() {
        super("POLYMORPH", "Polymorphism", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        playEffect(event);
    }

    public static void playEffect(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (Utils.checkEntities(entity, event.getDamager(), "POLYMORPH")) {
            Random random = Main.getRandom();

            LivingEntity target = (LivingEntity) entity;
            double damage = event.getDamage();
            if (!Utils.willKillEntity(target, event.getDamage())) {
                if (random.nextInt(15) == 0) {
                    event.setCancelled(true);

                    EntityType type = event.getEntityType();
                    EntityType newType = null;
                    if (friendlies.contains(type)) {
                        newType = getNewType(type, friendlies);
                    } else if (enemies.contains(type)) {
                        newType = getNewType(type, enemies);
                    }

                    if (newType != null) {
                        Utils.createPortalEffect(target.getLocation());

                        LivingEntity newEntity = (LivingEntity) target.getWorld().spawnEntity(target.getLocation(), newType);
                        double health = target.getHealth();
                        newEntity.setHealth(health > newEntity.getMaxHealth() ? newEntity.getMaxHealth() : health);
                        newEntity.damage(damage);
                        target.remove();
                    }
                }
            }
        }
    }

    private static EntityType getNewType(EntityType type, ArrayList<EntityType> list) {
        EntityType newType = type;
        do {
            newType = list.get(Main.getRandom().nextInt(list.size()));
        } while (type == newType);
        return newType;
    }
}
