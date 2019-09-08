package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.veggies.block.BlockVeggieTallTop;
import net.minecraft.block.BlockState;
import net.minecraft.item.BoneMealItem;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cornucopia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeggieEventHandler {
    @SubscribeEvent
    public static void useBonemealOnLowerCrop(BonemealEvent event) {
        if (event.getBlock().getBlock() instanceof BlockVeggieTallTop) {
            BoneMealItem.applyBonemeal(event.getPlayer().getHeldItemMainhand(), event.getWorld(), event.getPos().down(), event.getPlayer());
            BlockState downState = event.getWorld().getBlockState(event.getPos().down());
            event.getWorld().notifyBlockUpdate(event.getPos().down(), downState, downState, 2);
        }
    }

    @SubscribeEvent
    public static void stopFarmlandTrample(FarmlandTrampleEvent event) {
        if (event.isCancelable()) event.setCanceled(true);
    }
}