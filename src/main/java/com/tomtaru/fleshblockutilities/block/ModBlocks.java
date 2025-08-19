package com.tomtaru.fleshblockutilities.block;


import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.custom.block.*;
import com.tomtaru.fleshblockutilities.custom.block.BuddingNephrolithBlock;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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

    public static final DeferredBlock<Block> DETRITUS_BLOCK = registerBlock("detritus_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.CRIMSON_STEM)
                    .strength(0.6F)
                    .sound(SoundType.SLIME_BLOCK)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<Block> IRON_DEPOSIT = registerBlock("iron_deposit",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(1.6F)
                    .sound(SoundType.MUD)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<Block> CORPUS_NODE = registerBlock("corpus_node",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_RED)
                    .strength(1.6F)
                    .sound(SoundType.MUD_BRICKS)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<Block> FAT_DEPOSIT = registerBlock("fat_deposit",
            () -> new DropExperienceBlock(UniformInt.of(1, 3), BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(1.8F)
                    .sound(SoundType.MUD)
                    .pushReaction(PushReaction.NORMAL)));

    public static final DeferredBlock<AmethystClusterBlock> SMALL_NEPHROLITH_BUD = registerBlock("small_nephrolith_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD)));

    public static final DeferredBlock<AmethystClusterBlock> MEDIUM_NEPHROLITH_BUD = registerBlock("medium_nephrolith_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD)));

    public static final DeferredBlock<AmethystClusterBlock> LARGE_NEPHROLITH_BUD = registerBlock("large_nephrolith_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)));

    public static final DeferredBlock<AmethystClusterBlock> NEPHROLITH_CLUSTER = registerBlock("nephrolith_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> NEPHROLITH_BLOCK = registerBlock("nephrolith_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));


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

    public static final DeferredBlock<BuddingNephrolithBlock> BUDDING_NEPHROLITH = registerBlock("budding_nephrolith",
            () -> new BuddingNephrolithBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST)));

    // "Crops"

    public static final DeferredBlock<Block> HAIR_GROWTH = registerBlock("hair_growth",
            () -> new HairGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));

    public static final DeferredBlock<Block> STOMACH_GROWTH = registerBlock("stomach_growth",
            () -> new StomachGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> BRAIN_GROWTH = registerBlock("brain_growth",
            () -> new BrainGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> LIVER_GROWTH = registerBlock("liver_growth",
            () -> new LiverGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> HEART_GROWTH = registerBlock("heart_growth",
            () -> new HeartGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> KIDNEY_GROWTH = registerBlock("kidney_growth",
            () -> new KidneyGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> LUNG_GROWTH = registerBlock("lung_growth",
            () -> new LungGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> INTESTINAL_GROWTH = registerBlock("intestinal_growth",
            () -> new IntestinalGrowthBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

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
