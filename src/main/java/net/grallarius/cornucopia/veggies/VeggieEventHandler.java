package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.veggies.block.BlockVeggieTallCrop;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cornucopia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeggieEventHandler {
    @SubscribeEvent
    public static void stopFarmlandTrample(FarmlandTrampleEvent event) {
        if (event.isCancelable()) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void growTallTop(BlockEvent.CropGrowEvent event) {
        if (event.getState().getBlock() instanceof BlockVeggieTallCrop) {
            BlockVeggieTallCrop crop = (BlockVeggieTallCrop) event.getState().getBlock();
            crop.updateTop(event.getWorld().getWorld(), event.getPos(), event.getState(), crop.getAge(event.getState()));
        }
    }
}