package net.grallarius.cornucopia.orchard.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockOrchardLog extends LogBlock {
    public BlockOrchardLog(final String name) {
        super(MaterialColor.SAND, Block.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
        this.setRegistryName(Cornucopia.getId(String.format("orchard_%s_log", name)));
    }
}