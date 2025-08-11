package com.tomtaru.fleshblockutilities.custom.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties BOOGER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3f)
            .alwaysEdible().build();
}
