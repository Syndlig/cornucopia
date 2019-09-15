package net.grallarius.cornucopia.orchard.tree;

import net.grallarius.cornucopia.orchard.block.BlockOrchardLeaves;
import net.grallarius.cornucopia.orchard.block.BlockOrchardLog;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class OrchardTree extends Tree {
   private final BlockOrchardLog log;
   private final BlockOrchardLeaves leaves;

   public OrchardTree(BlockOrchardLog log, BlockOrchardLeaves leaves) {
      this.log = log;
      this.leaves = leaves;
   }

   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
      return new OrchardTreeFeature(NoFeatureConfig::deserialize, log, leaves, true, false);
   }
}