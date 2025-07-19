package com.tomtaru.fbcm.custom.item;

import com.tomtaru.fbcm.item.ModItems;
import com.tomtaru.fbcm.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTier {
    public static final Tier DETRITUS = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DETRITUS_TOOL,
    60, 4f, 3f, 28, () -> Ingredient.of(ModItems.DETRITUS_INGOT));
}
