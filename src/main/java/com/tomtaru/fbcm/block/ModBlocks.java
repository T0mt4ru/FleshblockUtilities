package com.tomtaru.fbcm.block;


import com.tomtaru.fbcm.FleshblockCompanionMod;
import com.tomtaru.fbcm.custom.block.FleshCraftingTable;
import com.tomtaru.fbcm.custom.block.TilledFleshBlock;
import com.tomtaru.fbcm.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FleshblockCompanionMod.MODID);

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
                    .sound(SoundType.SLIME_BLOCK)
                    .pushReaction(PushReaction.NORMAL)));

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
