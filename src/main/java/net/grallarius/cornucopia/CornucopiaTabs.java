package net.grallarius.cornucopia;

import net.grallarius.cornucopia.orchard.Orchard;
import net.grallarius.cornucopia.veggies.Veggie;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CornucopiaTabs {
    public static final ItemGroup ORCHARD = new ItemGroup(getName("orchard")) {
        public ItemStack createIcon() {
            return new ItemStack(Orchard.peach.raw);
        }
    };

    public static final ItemGroup CROPS = new ItemGroup(getName("crops")) {
        public ItemStack createIcon() {
            return new ItemStack(Veggie.asparagus.raw);
        }
    };

    private static String getName(String name) {
        return Cornucopia.MOD_ID + "_" + name;
    }
}