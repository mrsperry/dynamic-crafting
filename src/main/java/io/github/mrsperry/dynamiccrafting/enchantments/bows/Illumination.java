package io.github.mrsperry.dynamiccrafting.enchantments.bows;

import io.github.mrsperry.dynamiccrafting.Utils;
import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import javafx.util.Pair;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.material.Torch;

public class Illumination extends CustomEnchantment {

    public Illumination() {
        super("ILLUMINATION", "Illuminating", EnchantmentManager.getNextID());
        addEnchantmentTarget(EnchantmentTarget.BOW);

    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        playEffect(event);
    }

    public static void playEffect(ProjectileHitEvent event) {
        Pair<Player, Arrow> data = Utils.getShooterIfPlayer(event.getEntity());

        if(data != null) {
            Player player = data.getKey();
            Arrow arrow = data.getValue();

            if(EnchantmentManager.hasCustomEnchantment(player.getInventory().getItemInMainHand(), "ILLUMINATION")) {
                if(event.getHitEntity() == null) {
                    arrow.remove();
                    Block block = arrow.getWorld().getBlockAt(arrow.getLocation());
                    if(block.getWorld().getBlockAt(block.getLocation().clone().subtract(0,1,0)).getType().isSolid()) {
                        block.setType(Material.TORCH);
                    }
                } else {
                    event.getHitEntity().setFireTicks(100);
                }
            }
        }
    }
}
