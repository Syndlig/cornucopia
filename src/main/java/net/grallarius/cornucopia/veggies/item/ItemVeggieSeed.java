package net.grallarius.cornucopia.veggies.item;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.grallarius.cornucopia.veggies.block.BlockVeggieCrop;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class ItemVeggieSeed extends BlockNamedItem {
    public ItemVeggieSeed(String name, BlockVeggieCrop crop) {
        super(crop, new Item.Properties().group(CornucopiaTabs.CROPS));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_seed", name)));
    }
}