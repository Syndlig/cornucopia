package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

public class BlockVeggieWild extends BushBlock implements IPlantable {

    private ItemVeggieSeed seed;

    public BlockVeggieWild(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_wild", name)));
    }

    public void setDrops(final ItemVeggieSeed seed) {
        this.seed = seed;
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(seed);
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }
}