package net.grallarius.cornucopia;

import net.grallarius.cornucopia.veggies.Veggie;
import net.grallarius.cornucopia.veggies.VeggieEventHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class IProxy {

    IProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(IProxy::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(IProxy::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(IProxy::processIMC);
        MinecraftForge.EVENT_BUS.addListener(IProxy::serverStarting);
        MinecraftForge.EVENT_BUS.register(VeggieEventHandler.class);
//        MinecraftForge.EVENT_BUS.register(new VeggieLoot()); //TODO currently broken in forge, does not detect modded loot tables.
    }

    private static void setup(final FMLCommonSetupEvent event) {
    }

    private static void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private static void processIMC(final InterModProcessEvent event) {
    }

    private static void serverStarting(FMLServerStartingEvent event) {
    }

    static class Client extends IProxy {
        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::clientSetup);
        }

        private static void clientSetup(FMLClientSetupEvent event) {
            addBiomeTinting(Veggie.getCropArray());
        }

        private static void addBiomeTinting(Block[] blocks) {
            Minecraft.getInstance().getBlockColors().register(
                    (state, world, pos, tintIndex) -> world != null && pos != null
                            ? BiomeColors.getGrassColor(world, pos)
                            : GrassColors.get(0.5, 1),
                    blocks);
        }
    }

    static class Server extends IProxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Server::serverSetup);
        }

        private static void serverSetup(FMLDedicatedServerSetupEvent event) {
        }
    }
}