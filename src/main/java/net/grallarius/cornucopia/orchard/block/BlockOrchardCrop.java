package net.grallarius.cornucopia.orchard.block;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockOrchardCrop extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(2.0D, 11.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 11.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 9.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 9.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.makeCuboidShape(1.0D, 6.0D, 1.0D, 15.0D, 16.0D, 15.0D),
            Block.makeCuboidShape(1.0D, 6.0D, 1.0D, 15.0D, 16.0D, 15.0D),
            Block.makeCuboidShape(1.0D, 6.0D, 1.0D, 15.0D, 16.0D, 15.0D),
            Block.makeCuboidShape(1.0D, 2.0D, 1.0D, 15.0D, 16.0D, 15.0D)};
    private final BlockOrchardSapling sapling;
    private final BlockOrchardLeaves leaves;
    private final int maxNeighbors;

    public BlockOrchardCrop(final String name, BlockOrchardSapling sapling, BlockOrchardLeaves leaves) {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
        this.sapling = sapling;
        this.leaves = leaves;
        this.maxNeighbors = 2;
        this.setRegistryName(Cornucopia.getId(String.format("orchard_%s_crop", name)));
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return true;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.isSkyLightMax(pos)) &&
                (worldIn.getBlockState(pos).getBlock() == Blocks.AIR || worldIn.getBlockState(pos).getBlock() == this) &&
                worldIn.getBlockState(pos.up()).getBlock() == leaves;
    }

    boolean hasMaxNeighbors(IWorldReader worldIn, BlockPos pos) {
        return numberOfNeighbors(worldIn, pos) >= maxNeighbors;
    }

    private int numberOfNeighbors(IWorldReader worldIn, BlockPos pos) {
        int neighbors = 0;
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos newPos = pos.add(x, 0, z);
                if (newPos != pos && worldIn.getBlockState(newPos).getBlock() instanceof BlockOrchardCrop)
                    neighbors++;
            }
        }
        return neighbors;
    }

    public int getMaxAge() {
        return 7;
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return 1;
    }

    public IItemProvider getSeedsItem() {
        return sapling;
    }

    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}