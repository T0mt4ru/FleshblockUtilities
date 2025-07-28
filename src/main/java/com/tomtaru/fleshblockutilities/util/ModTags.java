package com.tomtaru.fleshblockutilities.util;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
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
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> SHEARS = importTag("biomesoplenty", "shears");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, name));
        }
        private static TagKey<Item> importTag(String modName, String tagName) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath((modName), tagName));
        }
    }
}
