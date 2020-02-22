package io.github.mrsperry.dynamiccrafting.enchantments.bows;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.Utils;
import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class Returning extends CustomEnchantment {
    private static final double RETURN_CHANCE = 0.50;

    public Returning() {
        super("RETURNING", "Returning", EnchantmentManager.getNextID());
        addEnchantmentTarget(EnchantmentTarget.BOW);
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        playEffect(event);
    }

    public static void playEffect(ProjectileHitEvent event) {
        Pair<Player, Arrow> shooterData = Utils.getShooterIfPlayer(event.getEntity());

        if(event.getHitEntity() != null) {
            return;
        }
        if(shooterData == null) {
            return;
        }

        Player player = shooterData.getKey();
        Arrow arrow = shooterData.getValue();

        if(EnchantmentManager.hasCustomEnchantment(player.getInventory().getItemInMainHand(), "RETURNING")) {
            if(Main.getRandom().nextDouble() <= RETURN_CHANCE) {
                arrow.remove();
                Collection<ItemStack> overflow = player.getInventory().addItem(new ItemStack(Material.ARROW)).values();

                for(ItemStack item : overflow) {
                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                }
            }
        }
    }
}
