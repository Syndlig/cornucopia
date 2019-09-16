package net.grallarius.cornucopia.veggies.item;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemVeggieRaw extends Item {
    public ItemVeggieRaw(String name) {
        super((new Item.Properties()).food(new Food.Builder().hunger(1).saturation(0.1f).build()).group(CornucopiaTabs.CROPS));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_raw", name)));
    }
}