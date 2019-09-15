package net.grallarius.cornucopia;

import net.grallarius.cornucopia.orchard.Orchard;
import net.grallarius.cornucopia.veggies.Veggie;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Cornucopia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registry {
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        Veggie.registerBlocks(registry);
        Orchard.registerBlocks(registry);
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Veggie.registerItems(registry);
        Orchard.registerItems(registry);
        Orchard.registerBlockItems(registry);
    }
}