package net.grallarius.cornucopia;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(Cornucopia.MOD_ID)
public class Cornucopia {
    public static final String MOD_ID = "cornucopia";
    private static final Logger LOGGER = LogManager.getLogger();

    public Cornucopia() {
        DistExecutor.runForDist(() -> IProxy.Client::new, () -> IProxy.Server::new);
    }

    @Nonnull
    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
