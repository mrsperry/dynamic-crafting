package io.github.mrsperry.dynamiccrafting.infusion_crafting.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractPedestal {
    private ItemStack item;
    private Block self;
    private ArmorStand display;

    public AbstractPedestal(Block self) {
        this.self = self;
        spawnArmorstand();
    }

    public void setItem(ItemStack item) {
        this.item = item;
        this.display.setHelmet(item);
    }

    private boolean hasItem() {
        return item == null;
    }

    private void removeItem() {
        //remove from armorstand
        this.self.getWorld().dropItemNaturally(this.self.getLocation().add(0.5, 0.5, 0.5), this.item);
        this.display.setHelmet(new ItemStack(Material.AIR));
        this.item = null;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Block getBlock() {
        return this.self;
    }

    public boolean isBlock(Block block) {
        return this.self.equals(block);
    }

    private void spawnArmorstand() {
        Location location = this.self.getLocation().clone().add(0.5, 0.5, 0.5);
        this.display = (ArmorStand) this.self.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.display.setGravity(false);
        this.display.setVisible(true);
        this.display.setInvulnerable(true);
        this.display.setSmall(true);
        setItem(new ItemStack(Material.STONE));
    }
}
