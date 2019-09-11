package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.IPlantable;

public class BlockVeggieWild extends BushBlock implements IPlantable {
    public BlockVeggieWild(String name) {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_wild", name)));
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }
}