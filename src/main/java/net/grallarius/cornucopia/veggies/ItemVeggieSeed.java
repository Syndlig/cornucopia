package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.Cornucopia;
import net.grallarius.cornucopia.CornucopiaTabs;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;

public class ItemVeggieSeed extends BlockNamedItem {

    public ItemVeggieSeed(String name, BlockVeggieCrop crop, Properties builder) {
        super(crop, builder.group(CornucopiaTabs.VEGGIES));
        this.setRegistryName(Cornucopia.getId(String.format("veggie_%s_seed", name)));
    }

    public ItemVeggieSeed(String name, BlockVeggieCrop crop, Properties builder, int hunger, float saturation) {
        this(name, crop, builder.food(new Food.Builder().hunger(hunger).saturation(saturation).build()));
    }
}