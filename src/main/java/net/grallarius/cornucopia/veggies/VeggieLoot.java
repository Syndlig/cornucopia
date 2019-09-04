package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.minecraft.block.CropsBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//TODO Forge currently doesn't work properly for loot tables. Leaving it in but unimplemented until this is resolved.
public class VeggieLoot {

    @SubscribeEvent
    void setVeggieLootPools(final LootTableLoadEvent event) {
        if (event.getName().getNamespace().equals(Cornucopia.MOD_ID)) {
            Veggie veggie = Veggie.getVegMap().get(event.getName().getPath().split("_")[1]);
            if (veggie != null) {
                LootTable table = event.getLootTableManager().getLootTableFromLocation(event.getName());
                table.addPool(buildLootPool(veggie));
                table.addPool(buildBonusPool(veggie));
            }
        }
    }



    private LootPool buildLootPool(Veggie veggie) {
        return LootPool.builder()
                .addEntry(ItemLootEntry.builder(veggie.raw)
                        .acceptCondition(buildCondition(veggie.crop))
                        .func_216080_a(ItemLootEntry.builder(veggie.seed)))
                .build();
    }

    private LootPool buildBonusPool(Veggie veggie) {
        return LootPool.builder()
                .acceptCondition(buildCondition(veggie.crop))
                .addEntry(ItemLootEntry.builder(veggie.seed)
                        .acceptFunction(ApplyBonus.func_215870_a(Enchantments.FORTUNE, 0.25F, 2)))
                .build();
    }

    private ILootCondition.IBuilder buildCondition(BlockVeggieCrop crop) {
        return BlockStateProperty.builder(crop).with(CropsBlock.AGE, 7);
    }
}