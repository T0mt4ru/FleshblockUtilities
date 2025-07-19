package com.tomtaru.fbcm.datagen;

import com.tomtaru.fbcm.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_HOE.get())
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET)).save(recipeOutput);

    }
}
