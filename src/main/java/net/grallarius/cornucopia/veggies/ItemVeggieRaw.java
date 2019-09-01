package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.minecraft.item.Item;

public class ItemVeggieRaw extends Item {

    public ItemVeggieRaw(String name, Properties builder) {
        super(builder.group(CornucopiaTabs.VEGGIES));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_raw", name)));
    }
}