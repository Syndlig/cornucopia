package net.grallarius.cornucopia.orchard.item;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemOrchardRaw extends Item {
    public ItemOrchardRaw(String name) {
        super((new Item.Properties()).food(new Food.Builder().hunger(1).saturation(0.1f).build()).group(CornucopiaTabs.ORCHARD));
        this.setRegistryName(Cornucopia.getId(String.format("orchard_%s_raw", name)));
    }
}