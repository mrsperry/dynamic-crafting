package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import org.bukkit.enchantments.EnchantmentTarget;

public class StaticDischarge extends CustomEnchantment {
    public StaticDischarge() {
        super("STATIC_DISCHARGE", "Static Discharge", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }
}
