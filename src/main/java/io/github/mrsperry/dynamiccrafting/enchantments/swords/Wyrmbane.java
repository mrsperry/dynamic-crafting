package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.enchantments.EnchantmentTarget;

public class Wyrmbane extends CustomEnchantment {
    public Wyrmbane() {
        super("WYRMBANE", "Wyrmbane", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }
}
