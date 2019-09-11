package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class BlockVeggieTallTop extends BushBlock implements IGrowable {
    private static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    private final BlockVeggieTallCrop crop;

    BlockVeggieTallTop(String name, BlockVeggieTallCrop bottom) {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT));
        this.crop = bottom;
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_top", name)));
    }

    BlockState withAge(int age) {
        return this.getDefaultState().with(AGE, age);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return (worldIn.isAirBlock(pos) || worldIn.getBlockState(pos).getBlock() == this)
                && worldIn.getBlockState(pos.down()).getBlock() == crop;
    }

    void placeAt(IWorld worldIn, BlockPos pos) {
        BlockState crop = worldIn.getBlockState(pos.down());
        worldIn.setBlockState(pos, this.withAge(crop.get(((BlockVeggieTallCrop) crop.getBlock()).getAgeProperty())), 2);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockState cropState = worldIn.getBlockState(pos.down());
        if (cropState.getBlock() == crop) {
            worldIn.setBlockState(pos.down(), Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, pos.down(), Block.getStateId(cropState));
            if (!worldIn.isRemote && !player.isCreative()) {
                spawnDrops(cropState, worldIn, pos.down(), null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(AGE)];
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(crop.getSeedsItem());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < 7;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        ((BlockVeggieTallCrop) worldIn.getBlockState(pos.down()).getBlock()).grow(worldIn, pos.down(), worldIn.getBlockState(pos.down()));
    }
}