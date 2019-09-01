package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockVeggieCrop extends CropsBlock {

    private ItemVeggieRaw raw;
    private ItemVeggieSeed seed;

    public BlockVeggieCrop(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_crop", name)));
    }

    public void setDrops(final ItemVeggieRaw raw, final ItemVeggieSeed seed) {
        this.seed = seed;
        this.raw = raw;
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof FarmlandBlock;
    }

    @OnlyIn(Dist.CLIENT)
    protected IItemProvider getSeedsItem() {
        return seed;
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(raw);
    }
}