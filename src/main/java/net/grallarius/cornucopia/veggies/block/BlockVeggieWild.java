package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;

public class BlockVeggieWild extends BushBlock implements IPlantable {
    public BlockVeggieWild(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_wild", name)));
    }

    @Nonnull
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }
}