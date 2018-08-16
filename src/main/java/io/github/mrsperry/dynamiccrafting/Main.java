package io.github.mrsperry.dynamiccrafting;

import io.github.mrsperry.dynamiccrafting.enchantments.swords.*;

import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin implements CommandExecutor {
    private static Random random;

    @Override
    public void onEnable() {
        random = new Random();

        this.saveDefaultConfig();

        Recipes.initialize(this);
        for (Recipe recipe : Recipes.getShapedRecipes()) {
            this.getServer().addRecipe(recipe);
        }
        for (Recipe recipe : Recipes.getShapelessRecipes()) {
            this.getServer().addRecipe(recipe);
        }

        this.getCommand("customenchant").setExecutor(this);

        try {
            EnchantmentManager.registerEnchantment(new Bloodbound());
            EnchantmentManager.registerEnchantment(new Chaos());
            EnchantmentManager.registerEnchantment(new Distortion());
            EnchantmentManager.registerEnchantment(new Draining());
            EnchantmentManager.registerEnchantment(new Freezing());
            EnchantmentManager.registerEnchantment(new Polymorph());
            EnchantmentManager.registerEnchantment(new StaticDischarge());
            EnchantmentManager.registerEnchantment(new Trance(this));
            EnchantmentManager.registerEnchantment(new Vampiric());
            EnchantmentManager.registerEnchantment(new Venomous());
            EnchantmentManager.registerEnchantment(new Wyrmbane());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmdLine, String[] args) {
        if (command.getName().equalsIgnoreCase("customenchant")) {
            try {
                ((Player) sender).getInventory().setItemInMainHand(EnchantmentManager.addUnsafeEnchantment(
                        ((Player) sender).getInventory().getItemInMainHand(),
                        EnchantmentManager.getEnchantByName(args[0]),
                        1)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {

    }

    public static Random getRandom() {
        return random;
    }
}
