package net.grallarius.cornucopia.veggies;

import net.grallarius.cornucopia.veggies.block.BlockVeggieCrop;
import net.grallarius.cornucopia.veggies.block.BlockVeggieTallCrop;
import net.grallarius.cornucopia.veggies.block.BlockVeggieWild;
import net.grallarius.cornucopia.veggies.item.ItemVeggieRaw;
import net.grallarius.cornucopia.veggies.item.ItemVeggieSeed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Veggie {
    static final LinkedHashMap<String, Veggie> vegMap = new LinkedHashMap<>();
    public static final Veggie
            artichoke = new Veggie("artichoke"),
            asparagus = new Veggie("asparagus"),
            barley = new Veggie("barley"),
            bean = new Veggie("bean"),
            beet = new Veggie("beet"),
            bell_pepper = new Veggie("bell_pepper"),
            blackberry = new Veggie("blackberry"),
            blueberry = new Veggie("blueberry"),
            broccoli = new Veggie("broccoli"),
            cabbage = new Veggie("cabbage"),
            celery = new Veggie("celery"),
            corn = new Veggie("corn", Type.TALL),
            cucumber = new Veggie("cucumber"),
            eggplant = new Veggie("eggplant"),
            garlic = new Veggie("garlic"),
            grape = new Veggie("grape"),
            herb = new Veggie("herb"),
            hops = new Veggie("hops"),
            lentil = new Veggie("lentil"),
            lettuce = new Veggie("lettuce"),
            onion = new Veggie("onion"),
            pea = new Veggie("pea"),
            peanut = new Veggie("peanut"),
            pineapple = new Veggie("pineapple"),
            raspberry = new Veggie("raspberry"),
            soy = new Veggie("soy"),
            spice = new Veggie("spice"),
            strawberry = new Veggie("strawberry"),
            tea = new Veggie("tea"),
            tomato = new Veggie("tomato"),
            turnip = new Veggie("turnip"),
            zucchini = new Veggie("zucchini");
    public final BlockVeggieCrop crop;
    public final BlockVeggieWild wild;
    public final ItemVeggieRaw raw;
    public final ItemVeggieSeed seed;

    private Veggie(final String name, final BlockVeggieCrop crop, final BlockVeggieWild wild, final ItemVeggieRaw raw, final ItemVeggieSeed seed) {
        this.crop = crop;
        this.crop.setSeed(seed);
        this.raw = raw;
        this.seed = seed;
        this.wild = wild;

        vegMap.put(name, this);
    }

    private Veggie(final String name, final BlockVeggieCrop crop, final BlockVeggieWild wild, final ItemVeggieRaw raw) {
        this(name, crop, wild, raw, new ItemVeggieSeed(name, crop));
    }

    private Veggie(final String name, final Type type) {
        this(name, (type == Type.SHORT) ? new BlockVeggieCrop(name) : new BlockVeggieTallCrop(name),
                new BlockVeggieWild(name), new ItemVeggieRaw(name));
    }

    private Veggie(final String name) {
        this(name, Type.SHORT);
    }

    private enum Type {SHORT, TALL}

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        vegMap.values().forEach(veggie -> {
            registry.registerAll(veggie.crop, veggie.wild);
            if (veggie.crop instanceof BlockVeggieTallCrop)
                registry.register(((BlockVeggieTallCrop) veggie.crop).top);
        });
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        vegMap.values().forEach(veggie -> registry.registerAll(veggie.raw, veggie.seed));
    }

    static LinkedHashMap<String, Veggie> getVegMap() {
        return vegMap;
    }

    public static BlockVeggieCrop[] getCropArray() {
        return Arrays.stream(vegMap.values().toArray(new Veggie[0])).map(veggie -> veggie.crop).toArray(BlockVeggieCrop[]::new);
    }
}