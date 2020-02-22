package io.github.mrsperry.dynamiccrafting.infusion_crafting;

import io.github.mrsperry.dynamiccrafting.Main;
import io.github.mrsperry.dynamiccrafting.infusion_crafting.blocks.CraftingPedestal;
import io.github.mrsperry.dynamiccrafting.infusion_crafting.blocks.MaterialPedestal;
import io.github.mrsperry.mcutils.builders.ItemBuilder;
import io.github.pepsidog.custommeta.nbt.MetaHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class InfusionCraftingManager implements Listener {
    private MetaHandler handler;

    public InfusionCraftingManager() {
        this.handler = Main.getMetaHandler();
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();

        if(item.equals(EquipmentSlot.OFF_HAND) && handler.hasKey(item, "infusionCrafting")) { event.setCancelled(true); return; }
        if(handler.hasKey(item, "infusionCrafting")) {
            String type = handler.getValue(item, "infusionCrafting");

            if(type.equalsIgnoreCase("crafting")) {
                new CraftingPedestal(event.getBlock());
            } else if(type.equalsIgnoreCase("ingredient")) {
                new MaterialPedestal(event.getBlock(), null);
            }
        }
    }

    public static void initRecipies() {
        ItemStack craftingPedestal = craftPedestalAsItemstack();
        ItemStack materialPedestal = materialPedestalAsItemstack();

        ShapelessRecipe craftingPedestalRecipe = new ShapelessRecipe(craftingPedestal);
        ShapelessRecipe materialPedestalRecipe = new ShapelessRecipe(materialPedestal);

        craftingPedestalRecipe.addIngredient(Material.ENCHANTMENT_TABLE);
        craftingPedestalRecipe.addIngredient(8, Material.BOOKSHELF);

        materialPedestalRecipe.addIngredient(Material.ANVIL);
        materialPedestalRecipe.addIngredient(8, Material.CHEST);

        Bukkit.getServer().addRecipe(craftingPedestalRecipe);
        Bukkit.getServer().addRecipe(materialPedestalRecipe);
    }

    public static ItemStack craftPedestalAsItemstack() {
        ItemStack item = new ItemBuilder(Material.ENCHANTMENT_TABLE)
                .setName(ChatColor.LIGHT_PURPLE + "Crafting Pedestal")
                .addLore(ChatColor.GRAY + "The infusion crafting core pedestal!")
                .build();

        return Main.getMetaHandler().setKey(item, "infusionCrafting", "crafting");
    }

    public static ItemStack materialPedestalAsItemstack() {
        ItemStack item = new ItemBuilder(Material.ANVIL)
                .setName(ChatColor.LIGHT_PURPLE + "Material Pedestal")
                .addLore(ChatColor.GRAY + "Infusion crafting ingredient pedestal!")
                .build();

        return Main.getMetaHandler().setKey(item, "infusionCrafting", "ingredient");
    }
}
