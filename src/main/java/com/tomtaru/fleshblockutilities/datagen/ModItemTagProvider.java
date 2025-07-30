package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.item.ModItems;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FleshblockUtilities.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // Vanilla tags
        tag(ItemTags.SWORDS).add(ModItems.DETRITUS_SWORD.get());
        tag(ItemTags.HOES).add(ModItems.DETRITUS_HOE.get());
        tag(ItemTags.AXES).add(ModItems.DETRITUS_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.DETRITUS_SHOVEL.get());
        tag(ItemTags.PICKAXES).add(ModItems.DETRITUS_PICKAXE.get());

        // Modded tags
        tag(ModTags.Items.SHEARS).add(ModItems.DETRITUS_SHEARS.get());
        tag(ModTags.Items.STRINGS).add(ModItems.HAIR_STRAND.get());

    }
}
