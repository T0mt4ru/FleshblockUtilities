package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.item.ModItems;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
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
        tag(ItemTags.SWORDS)
                .add(ModItems.DETRITUS_SWORD.get())
                .add(ModItems.NEPHROLITH_SWORD.get());

        tag(ItemTags.HOES)
                .add(ModItems.DETRITUS_HOE.get())
                .add(ModItems.NEPHROLITH_HOE.get());

        tag(ItemTags.AXES)
                .add(ModItems.DETRITUS_AXE.get())
                .add(ModItems.NEPHROLITH_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.DETRITUS_SHOVEL.get())
                .add((ModItems.NEPHROLITH_SHOVEL.get()));

        tag(ItemTags.PICKAXES)
                .add(ModItems.DETRITUS_PICKAXE.get())
                .add(ModItems.NEPHROLITH_PICKAXE.get());

        // Modded tags
        tag(ModTags.Items.SHEARS).add(ModItems.DETRITUS_SHEARS.get());

        tag(ModTags.Items.STRINGS).add(ModItems.HAIR_STRAND.get());

        tag(ModTags.Items.INGOTS)
                .add(ModItems.DETRITUS_INGOT.get())
                .add(ModItems.CORPUSITE_INGOT.get())
                .add(ModItems.KERATITE_INGOT.get());

        tag(Tags.Items.BUDDING_BLOCKS)
                .add(ModBlocks.BUDDING_NEPHROLITH.asItem());

        tag(Tags.Items.BUDS)
                .add(ModBlocks.SMALL_NEPHROLITH_BUD.asItem())
                .add(ModBlocks.MEDIUM_NEPHROLITH_BUD.asItem())
                .add(ModBlocks.LARGE_NEPHROLITH_BUD.asItem());

        tag(Tags.Items.CLUSTERS)
                .add(ModBlocks.NEPHROLITH_CLUSTER.asItem());

        tag(Tags.Items.ORES_IRON)
                .add(ModBlocks.IRON_DEPOSIT.asItem());

        tag(Tags.Items.SLIME_BALLS)
                .add(ModItems.BOOGER.get());

        tag(Tags.Items.RODS)
                .add(ModItems.GRISTLE.get());


    }
}
