package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraftforge.common.IPlantable;

public class BlockVeggieWild extends BushBlock implements IPlantable {
    BlockVeggieWild(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_wild", name)));
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }
}