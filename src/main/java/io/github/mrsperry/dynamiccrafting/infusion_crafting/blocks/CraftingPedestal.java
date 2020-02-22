package io.github.mrsperry.dynamiccrafting.infusion_crafting.blocks;

import org.bukkit.block.Block;

import java.util.List;

public class CraftingPedestal extends AbstractPedestal {
    private List<MaterialPedestal> children;
    private final int MAX_CHILDREN = 8;

    public CraftingPedestal(Block self) { super(self); }

    public void addChild(Block block) {
        if(this.children.size() < this.MAX_CHILDREN) {
            this.children.add(new MaterialPedestal(block, this));
        } else {
            //tell them they an idiot
        }
    }

    public boolean isChild(final Block block) {
        return this.children.stream().anyMatch(child -> child.isBlock(block));
    }

    public MaterialPedestal getChild(Block block) {
        return this.children.stream().filter(child -> child.isBlock(block)).findFirst().orElse(null);
    }
}
