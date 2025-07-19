package com.tomtaru.fbcm.datagen;

import com.tomtaru.fbcm.block.ModBlocks;
import com.tomtaru.fbcm.custom.block.TilledFleshBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropOther(ModBlocks.TILLED_FLESH, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "flesh")));

    }

    private void dropOther(DeferredBlock<TilledFleshBlock> tilledFlesh, Block item) {
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
