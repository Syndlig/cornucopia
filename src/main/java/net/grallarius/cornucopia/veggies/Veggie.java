package net.grallarius.cornucopia.veggies;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class Veggie {
    private static final HashMap<String, Veggie> vegMap = new HashMap<>();

    public static Veggie artichoke, asparagus, barley, bean, beet, bell_pepper,
            blackberry, blueberry, broccoli, cabbage, celery, corn, cucumber,
            eggplant, garlic, grape, herb, hops, lentil, lettuce, onion, pea,
            peanut, pineapple, raspberry, soy, spice, strawberry, tea, tomato,
            turnip, zucchini;

    public final String name;
    public final BlockVeggieCrop crop;
    public final BlockVeggieWild wild;
    public final ItemVeggieRaw raw;
    public final ItemVeggieSeed seed;

    public Veggie(final String name, final BlockVeggieCrop crop, final BlockVeggieWild wild, final ItemVeggieRaw raw, final ItemVeggieSeed seed) {
        this.name = name;
        this.crop = crop;
        this.wild = wild;
        this.raw = raw;
        this.seed = seed;

        this.crop.setDrops(raw, seed);
        this.wild.setDrops(seed);
        vegMap.put(name, this);
    }

    private Veggie(final String name, final BlockVeggieCrop crop, final BlockVeggieWild wild, final ItemVeggieRaw raw) {
        this(name, crop, wild, raw, new ItemVeggieSeed(name, crop, new Item.Properties()));
    }

    private Veggie(final String name) {
        this(name,
                new BlockVeggieCrop(name, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT)),
                new BlockVeggieWild(name, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.PLANT)),
                new ItemVeggieRaw(name, (new Item.Properties()).food(new Food.Builder().hunger(1).saturation(0.1f).build())));
    }

    public static void buildVeggies() {
        artichoke = new Veggie("artichoke");
        asparagus = new Veggie("asparagus");
        barley = new Veggie("barley");
//        bean = new Veggie("bean");
        beet = new Veggie("beet");
        bell_pepper = new Veggie("bell_pepper");
        blackberry = new Veggie("blackberry");
        blueberry = new Veggie("blueberry");
//        broccoli = new Veggie("broccoli");
        cabbage = new Veggie("cabbage");
//        celery = new Veggie("celery");
//        corn = new Veggie("corn");
//        cucumber = new Veggie("cucumber");
//        eggplant = new Veggie("eggplant");
        garlic = new Veggie("garlic");
        grape = new Veggie("grape");
        herb = new Veggie("herb");
        hops = new Veggie("hops");
        lentil = new Veggie("lentil");
        lettuce = new Veggie("lettuce");
        onion = new Veggie("onion");
//        pea = new Veggie("pea");
        peanut = new Veggie("peanut");
//        pineapple = new Veggie("pineapple");
        raspberry = new Veggie("raspberry");
//        soy = new Veggie("soy");
        spice = new Veggie("spice");
        strawberry = new Veggie("strawberry");
//        tea = new Veggie("tea");
        tomato = new Veggie("tomato");
        turnip = new Veggie("turnip");
//        zucchini = new Veggie("zucchini");
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (!event.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;
        for (Map.Entry<String, Veggie> veggie : vegMap.entrySet()) {
            event.getRegistry().register(veggie.getValue().crop);
            event.getRegistry().register(veggie.getValue().wild);
        }
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
        for (Map.Entry<String, Veggie> veggie : vegMap.entrySet()) {
            event.getRegistry().register(veggie.getValue().raw);
            event.getRegistry().register(veggie.getValue().seed);
        }
    }
}