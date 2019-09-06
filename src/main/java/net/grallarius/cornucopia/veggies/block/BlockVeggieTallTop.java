package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.player.PlayerEntity;
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

import javax.annotation.Nonnull;

public class BlockVeggieTallTop extends BushBlock {
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

    BlockVeggieTallTop(String name, BlockVeggieTallCrop bottom, Properties properties) {
        super(properties);
        this.crop = bottom;
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_top", name)));
    }

    private IntegerProperty getAgeProperty() {
        return AGE;
    }

    BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    boolean isValidPosition(IWorldReader worldIn, BlockPos pos, int cropNewAge) {
        return (worldIn.isAirBlock(pos) || worldIn.getBlockState(pos).getBlock() == this)
                && worldIn.getBlockState(pos.down()).getBlock() == this.crop
                && cropNewAge >= BlockVeggieTallCrop.getTallAge();
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    void placeAt(IWorld worldIn, BlockPos pos) {
        BlockState crop = worldIn.getBlockState(pos.down());
        worldIn.setBlockState(pos, this.withAge(crop.get(((BlockVeggieTallCrop) crop.getBlock()).getAgeProperty())), 2);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, @Nonnull PlayerEntity player) {
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
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}