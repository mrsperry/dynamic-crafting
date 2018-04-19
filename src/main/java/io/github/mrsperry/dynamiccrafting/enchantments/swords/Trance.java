package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import org.bukkit.enchantments.EnchantmentTarget;

public class Trance extends CustomEnchantment {
    public Trance() {
        super("TRANCE", "Trance", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }
}
