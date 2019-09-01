package net.grallarius.cornucopia;

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