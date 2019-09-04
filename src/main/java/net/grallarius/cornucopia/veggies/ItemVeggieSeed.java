package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.minecraft.item.BlockNamedItem;

public class ItemVeggieSeed extends BlockNamedItem {
    ItemVeggieSeed(String name, BlockVeggieCrop crop, Properties builder) {
        super(crop, builder.group(CornucopiaTabs.VEGGIES));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_seed", name)));
    }
}