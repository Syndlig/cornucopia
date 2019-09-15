package net.grallarius.cornucopia.orchard.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockOrchardLeaves extends LeavesBlock {
    private BlockOrchardCrop crop;

    public BlockOrchardLeaves(final String name) {
        super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT));
        this.setRegistryName(Cornucopia.getId(String.format("orchard_%s_leaves", name)));
    }

    public void setCrop(BlockOrchardCrop crop) {
        this.crop = crop;
    }

    public boolean isShearable(@Nonnull ItemStack item, IWorldReader world, BlockPos pos) {
        return false;
    }

    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (isValidFruitPosition(worldIn, pos) && random.nextInt(10) == 0) {
            worldIn.setBlockState(pos.down(), this.crop.getDefaultState());
        }
        super.randomTick(state, worldIn, pos, random);
    }

    private boolean isValidFruitPosition(World worldIn, BlockPos pos) {
        return this.crop.isValidPosition(this.crop.getDefaultState(), worldIn, pos.down()) && !this.crop.hasMaxNeighbors(worldIn, pos.down());
    }
}