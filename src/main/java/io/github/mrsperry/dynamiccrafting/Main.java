package io.github.mrsperry.dynamiccrafting;

import io.github.mrsperry.dynamiccrafting.enchantments.bows.Exploding;
import io.github.mrsperry.dynamiccrafting.enchantments.bows.Illumination;
import io.github.mrsperry.dynamiccrafting.enchantments.bows.Returning;
import io.github.mrsperry.dynamiccrafting.enchantments.swords.*;
import io.github.mrsperry.dynamiccrafting.infusion_crafting.InfusionCraftingManager;
import io.github.pepsidawg.enchantmentapi.EnchantmentManager;

import io.github.pepsidog.custommeta.CustomMeta;
import io.github.pepsidog.custommeta.nbt.MetaHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin implements CommandExecutor {
    private static Random random;
    private static MetaHandler metaHandler;

    @Override
    public void onEnable() {
        random = new Random();
        try {
            metaHandler = CustomMeta.getHandler(this);
        } catch (Exception e) {
            getLogger().warning("Could not find suitable meta handler version!");
            metaHandler = null;
        }

        this.saveDefaultConfig();

        Recipes.initialize(this);
        for (Recipe recipe : Recipes.getShapedRecipes()) {
            this.getServer().addRecipe(recipe);
        }
        for (Recipe recipe : Recipes.getShapelessRecipes()) {
            this.getServer().addRecipe(recipe);
        }
        InfusionCraftingManager.initRecipies();
        getServer().getPluginManager().registerEvents(new InfusionCraftingManager(), this);

        this.getCommand("customenchant").setExecutor(this);

        try {
//            EnchantmentManager.registerEnchantment(new Bloodbound());
//            EnchantmentManager.registerEnchantment(new Chaos());
//            EnchantmentManager.registerEnchantment(new Distortion());
//            EnchantmentManager.registerEnchantment(new Draining());
//            EnchantmentManager.registerEnchantment(new Freezing());
//            EnchantmentManager.registerEnchantment(new Polymorph());
//            EnchantmentManager.registerEnchantment(new StaticDischarge());
//            EnchantmentManager.registerEnchantment(new Trance());
//            EnchantmentManager.registerEnchantment(new Vampiric());
//            EnchantmentManager.registerEnchantment(new Venomous());
//            EnchantmentManager.registerEnchantment(new Wyrmbane());
//            EnchantmentManager.registerEnchantment(new Exploding(), new Returning(), new Illumination());
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

    public static MetaHandler getMetaHandler() {
        return metaHandler;
    }
}
