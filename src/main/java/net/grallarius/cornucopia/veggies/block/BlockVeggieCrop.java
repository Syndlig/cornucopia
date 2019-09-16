package net.grallarius.cornucopia.veggies.block;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.veggies.item.ItemVeggieSeed;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockVeggieCrop extends CropsBlock {
    private ItemVeggieSeed seed;

    BlockVeggieCrop(String name, Block.Properties builder) {
        super(builder);
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_crop", name)));
    }

    public BlockVeggieCrop(String name) {
        this(name, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT));
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 1, 2);
    }

    public void setSeed(ItemVeggieSeed seed) {
        this.seed = seed;
    }

    protected IItemProvider getSeedsItem() {
        return seed;
    }
}