package io.github.mrsperry.dynamiccrafting.enchantments.swords;

import io.github.pepsidawg.enchantmentapi.CustomEnchantment;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;
import org.bukkit.enchantments.EnchantmentTarget;

public class Vampiric extends CustomEnchantment {
    public Vampiric() {
        super("VAMPIRIC", "Vampiric", EnchantmentManager.getNextID());

        this.addEnchantmentTarget(EnchantmentTarget.WEAPON);
    }
}
