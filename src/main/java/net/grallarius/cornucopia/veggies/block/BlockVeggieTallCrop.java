package net.grallarius.cornucopia.veggies.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockVeggieTallCrop extends BlockVeggieCrop {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    public BlockVeggieTallTop top;

    public BlockVeggieTallCrop(String name) {
        super(name, Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT));
        top = new BlockVeggieTallTop(name, this);
    }

    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public void grow(final World worldIn, final BlockPos pos, final BlockState state) {
        int newAge = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        if (newAge > this.getMaxAge()) newAge = this.getMaxAge();

        worldIn.setBlockState(pos, this.withAge(newAge), 2);
        updateTop(worldIn, pos, state, newAge);
    }

    public void updateTop(final World worldIn, final BlockPos pos, final BlockState state, final int newAge) {
        if (newAge >= 4 && newAge <= 7 && top.isValidPosition(state, worldIn, pos.up())) {
            if (worldIn.isAirBlock(pos.up())) {
                top.placeAt(worldIn, pos.up());
            } else if (worldIn.getBlockState(pos.up()).getBlock() == top) {
                worldIn.setBlockState(pos.up(), top.withAge(newAge), 2);
            }
        }
    }

    public void onBlockHarvested(final World worldIn, final BlockPos pos, final BlockState state, final PlayerEntity player) {
        BlockState topState = worldIn.getBlockState(pos.up());
        if (topState.getBlock() == top) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, pos.up(), Block.getStateId(topState));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}