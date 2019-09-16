package net.grallarius.cornucopia.orchard.block;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.orchard.tree.OrchardTree;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockOrchardSapling extends SaplingBlock {
    public BlockOrchardSapling(final String name, final BlockOrchardLog log, final BlockOrchardLeaves leaves) {
        super(new OrchardTree(log, leaves), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
        this.setRegistryName(Cornucopia.getId(String.format("orchard_%s_sapling", name)));
    }
}