package net.grallarius.cornucopia.orchard;

import net.grallarius.cornucopia.CornucopiaTabs;
import net.grallarius.cornucopia.orchard.block.BlockOrchardCrop;
import net.grallarius.cornucopia.orchard.block.BlockOrchardLeaves;
import net.grallarius.cornucopia.orchard.block.BlockOrchardLog;
import net.grallarius.cornucopia.orchard.block.BlockOrchardSapling;
import net.grallarius.cornucopia.orchard.item.ItemOrchardRaw;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Orchard {
    private static final LinkedHashMap<String, Orchard> fruitMap = new LinkedHashMap<>();
    public static final Orchard
            almond = new Orchard("almond"),
            avocado = new Orchard("avocado"),
            banana = new Orchard("banana"),
            cherry = new Orchard("cherry"),
            coconut = new Orchard("coconut"),
            coffee = new Orchard("coffee"),
            date = new Orchard("date"),
            fig = new Orchard("fig"),
            grapefruit = new Orchard("grapefruit"),
            kiwi = new Orchard("kiwi"),
            lemon = new Orchard("lemon"),
            lime = new Orchard("lime"),
            olive = new Orchard("olive"),
            orange = new Orchard("orange"),
            peach = new Orchard("peach"),
            pear = new Orchard("pear"),
            plum = new Orchard("plum"),
            pomegranate = new Orchard("pomegranate");
    public final BlockOrchardCrop crop;
    public final BlockOrchardLog log;
    public final BlockOrchardLeaves leaves;
    public final BlockOrchardSapling sapling;
    public final ItemOrchardRaw raw;

    private Orchard(final String name, final BlockOrchardLog log, final BlockOrchardLeaves leaves, final BlockOrchardSapling sapling, final ItemOrchardRaw raw) {
        this.crop = new BlockOrchardCrop(name, sapling, leaves);
        this.log = log;
        this.leaves = leaves;
        leaves.setCrop(crop);
        this.sapling = sapling;
        this.raw = raw;

        fruitMap.put(name, this);
    }

    private Orchard(final String name, final BlockOrchardLog log, BlockOrchardLeaves leaves) {
        this(name, log, leaves, new BlockOrchardSapling(name, log, leaves), new ItemOrchardRaw(name));
    }

    private Orchard(final String name) {
        this(name, new BlockOrchardLog(name), new BlockOrchardLeaves(name));
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        fruitMap.values().forEach(orchard -> {
            registry.registerAll(orchard.crop, orchard.leaves, orchard.log, orchard.sapling);
        });
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        fruitMap.values().forEach(orchard -> registry.register(orchard.raw));
    }

    public static void registerBlockItems(IForgeRegistry<Item> registry) {
        fruitMap.values().forEach(orchard -> {
            registerBlockItem(registry, orchard.sapling);
            registerBlockItem(registry, orchard.log);
            registerBlockItem(registry, orchard.leaves);
        });
    }

    private static void registerBlockItem(IForgeRegistry<Item> registry, Block block) {
        BlockItem blockItem = new BlockItem(block, new Item.Properties().group(CornucopiaTabs.ORCHARD));
        blockItem.setRegistryName(block.getRegistryName());
        blockItem.addToBlockToItemMap(Item.BLOCK_TO_ITEM, blockItem);
        registry.register(blockItem);
    }

    public static BlockOrchardLeaves[] getLeafArray() {
        return Arrays.stream(fruitMap.values().toArray(new Orchard[0])).map(orchard -> orchard.leaves).toArray(BlockOrchardLeaves[]::new);
    }
}