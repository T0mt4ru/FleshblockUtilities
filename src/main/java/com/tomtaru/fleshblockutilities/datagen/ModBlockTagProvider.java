package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FleshblockUtilities.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

       //Vanilla Tags
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.DETRITUS_BLOCK.get())
                .add(ModBlocks.FLESH_CRAFTING_TABLE.get())
                .add(ModBlocks.TILLED_FLESH.get())
                .add(ModBlocks.FLESH_CHEST.get())
                .add(ModBlocks.IRON_DEPOSIT.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DRYING_RACK.get())
                .add(ModBlocks.SMALL_NEPHROLITH_BUD.get())
                .add(ModBlocks.MEDIUM_NEPHROLITH_BUD.get())
                .add(ModBlocks.LARGE_NEPHROLITH_BUD.get())
                .add(ModBlocks.NEPHROLITH_CLUSTER.get())
                .add(ModBlocks.NEPHROLITH_BLOCK.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.DETRITUS_BLOCK.get());

        //Modded Tags
        tag(ModTags.Blocks.NEEDS_DETRITUS_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.IRON_DEPOSIT.get())
                .add(ModBlocks.NEPHROLITH_CLUSTER.get());

        tag(ModTags.Blocks.INCORRECT_FOR_DETRITUS_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(ModTags.Blocks.NEEDS_DETRITUS_TOOL);

        tag(ModTags.Blocks.NEEDS_NEPHROLITH_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_NEPHROLITH_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_NEPHROLITH_TOOL);

        tag(ModTags.Blocks.FLESH_DEPOSIT_REPLACEABLES)
                .add(TagEntry.element(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "flesh")));

        tag(Tags.Blocks.BUDDING_BLOCKS)
                .add(ModBlocks.BUDDING_NEPHROLITH.get());

        tag(Tags.Blocks.BUDS)
                .add(ModBlocks.SMALL_NEPHROLITH_BUD.get())
                .add(ModBlocks.MEDIUM_NEPHROLITH_BUD.get())
                .add(ModBlocks.LARGE_NEPHROLITH_BUD.get());

        tag(Tags.Blocks.CLUSTERS)
                .add(ModBlocks.NEPHROLITH_CLUSTER.get());

        tag(Tags.Blocks.ORES_IRON)
                .add(ModBlocks.IRON_DEPOSIT.get());


    }
}