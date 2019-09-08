package net.grallarius.cornucopia.veggies.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockVeggieTallCrop extends BlockVeggieCrop {
    private static int tallAge;
    public static BlockVeggieTallTop top;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    private BlockVeggieTallCrop(String name, int tallAge, Properties builder) {
        super(name, builder);
        BlockVeggieTallCrop.tallAge = tallAge;
        top = new BlockVeggieTallTop(name, this, builder);
    }

    public BlockVeggieTallCrop(String name, Properties builder) {
        this(name, 4, builder);
    }

    static int getTallAge() {
        return tallAge;
    }

    public void grow(final World worldIn, @Nonnull final BlockPos pos, @Nonnull final BlockState state) {
        int newAge = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        if (newAge > this.getMaxAge()) newAge = this.getMaxAge();

        if (newAge < tallAge) {
            worldIn.setBlockState(pos, this.withAge(newAge), 2);
        } else if (top.isValidPosition(worldIn, pos.up(), newAge)) {
            worldIn.setBlockState(pos, this.withAge(newAge), 2);
            if (worldIn.isAirBlock(pos.up())) {
                top.placeAt(worldIn, pos.up());
            } else if (worldIn.getBlockState(pos.up()).getBlock() == top) {
                worldIn.setBlockState(pos.up(), top.withAge(newAge), 2);
            }
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, @Nonnull PlayerEntity player) {
        BlockState topState = worldIn.getBlockState(pos.up());
        if (topState.getBlock() == top) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, pos.up(), Block.getStateId(topState));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}