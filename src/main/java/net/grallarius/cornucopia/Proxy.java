package net.grallarius.cornucopia;

import net.grallarius.cornucopia.veggies.Veggie;
import net.grallarius.cornucopia.veggies.VeggieEventHandler;
import net.grallarius.cornucopia.veggies.VeggieLoot;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Proxy {
    Proxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::processIMC);

        MinecraftForge.EVENT_BUS.addListener(Proxy::serverStarting);
        MinecraftForge.EVENT_BUS.register(VeggieEventHandler.class);
        MinecraftForge.EVENT_BUS.register(VeggieLoot.class);
    }

    private static void setup(final FMLCommonSetupEvent event) {
    }

    private static void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private static void processIMC(final InterModProcessEvent event) {
    }

    private static void serverStarting(FMLServerStartingEvent event) {
    }

    static class Client extends Proxy {
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

    static class Server extends Proxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Server::serverSetup);
        }

        private static void serverSetup(FMLDedicatedServerSetupEvent event) {
        }
    }
}