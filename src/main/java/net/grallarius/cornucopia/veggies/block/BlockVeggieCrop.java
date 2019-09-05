package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.veggies.item.ItemVeggieSeed;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class BlockVeggieCrop extends CropsBlock {
    private ItemVeggieSeed seed;

    public BlockVeggieCrop(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_crop", name)));
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof FarmlandBlock;
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return 1;
    }

    public void setSeed(ItemVeggieSeed seed) {
        this.seed = seed;
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    protected IItemProvider getSeedsItem() {
        return seed;
    }
}