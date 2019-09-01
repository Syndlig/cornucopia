package net.grallarius.cornucopia;

import net.grallarius.cornucopia.veggies.Veggie;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Proxy {

    Proxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Proxy::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Veggie::registerBlocks);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Veggie::registerItems);

        MinecraftForge.EVENT_BUS.addListener(Proxy::serverStarting);
    }

    private static void setup(final FMLCommonSetupEvent event) {
        Veggie.buildVeggies();
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