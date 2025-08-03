package com.tomtaru.fleshblockutilities.block;


import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.custom.block.*;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FleshblockUtilities.MODID);

    // Blocks

    public static final DeferredBlock<TilledFleshBlock> TILLED_FLESH = registerBlock("tilled_flesh",
            () -> new TilledFleshBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .randomTicks()
                    .strength(0.6F)
                    .sound(SoundType.WET_GRASS)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<FleshCraftingTable> FLESH_CRAFTING_TABLE = registerBlock("flesh_crafting_table",
            () -> new FleshCraftingTable(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.CRIMSON_STEM)
                    .strength(0.6F)
                    .sound(SoundType.SLIME_BLOCK)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<DetritusBlock> DETRITUS_BLOCK = registerBlock("detritus_block",
            () -> new DetritusBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.CRIMSON_STEM)
                    .strength(0.6F)
                    .sound(SoundType.SLIME_BLOCK)
                    .pushReaction(PushReaction.NORMAL)));

    // BlockEntities

    public static final DeferredBlock<FleshChestBlock> FLESH_CHEST = registerBlock("flesh_chest",
            () -> new FleshChestBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(1.5F)
                    .sound(SoundType.MUD)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<DryingRackBlock> DRYING_RACK = registerBlock("drying_rack",
            () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(1F)
                    .sound(SoundType.BONE_BLOCK)
                    .pushReaction(PushReaction.IGNORE)));

    // "Crops"

    public static final DeferredBlock<Block> HAIR_GROWTH = registerBlock("hair_growth",
            () -> new HairGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void  register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
