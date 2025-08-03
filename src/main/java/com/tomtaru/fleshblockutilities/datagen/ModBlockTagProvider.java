package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                .add(ModBlocks.FLESH_CHEST.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DRYING_RACK.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.DETRITUS_BLOCK.get());

        //Modded Tags
        tag(ModTags.Blocks.NEEDS_DETRITUS_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_DETRITUS_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(ModTags.Blocks.NEEDS_DETRITUS_TOOL);
    }
}