package net.grallarius.cornucopia;

import net.grallarius.cornucopia.veggies.Veggie;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CornucopiaTabs {
    public static final ItemGroup VEGGIES = new ItemGroup(getName("veggies")) {
        public ItemStack createIcon() {
            return new ItemStack(Veggie.asparagus.raw);
        }
    };

    private static String getName(String name) {
        return Cornucopia.MOD_ID + "_" + name;
    }
}