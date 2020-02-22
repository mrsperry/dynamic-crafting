package io.github.mrsperry.dynamiccrafting.infusion_crafting.blocks;

import org.bukkit.block.Block;

public class MaterialPedestal extends AbstractPedestal {
    CraftingPedestal parent;

    public MaterialPedestal(Block self, CraftingPedestal parent) {
        super(self);
        this.parent = parent;
    }

    public CraftingPedestal getParent() {
        return this.parent;
    }

    public String getMaterialName() {
        return this.getItem().getType().name();
    }
}
