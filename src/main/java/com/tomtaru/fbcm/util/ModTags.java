package com.tomtaru.fbcm.util;

import com.tomtaru.fbcm.FleshblockCompanionMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DETRITUS_TOOL = createTag("needs_detritus_tool");
        public static final TagKey<Block> INCORRECT_FOR_DETRITUS_TOOL = createTag("incorrect_for_detritus_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FleshblockCompanionMod.MODID, name));
        }
    }
    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FleshblockCompanionMod.MODID, name));
        }
    }
}
