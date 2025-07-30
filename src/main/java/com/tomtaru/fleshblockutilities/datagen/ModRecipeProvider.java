package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DETRITUS_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.DETRITUS_NUGGET.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput,"fleshblockutilities:detritus_ingot_from_nugget");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DETRITUS_NUGGET.get(),9)
                .pattern("#")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput,"fleshblockutilities:detritus_nugget_from_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DETRITUS_INGOT.get(), 9)
                .pattern("#")
                .define('#', ModBlocks.DETRITUS_BLOCK.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput, "fleshblockutilities:detritus_ingot_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DETRITUS_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput,"fleshblockutilities:detritus_block_from_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_HOE.get())
                .pattern(" ##")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_AXE.get())
                .pattern(" ##")
                .pattern(" I#")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_PICKAXE.get())
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_SWORD.get())
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_SHOVEL.get())
                .pattern(" # ")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .define('I', ModItems.GRISTLE.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_SHEARS.get())
                .pattern(" #")
                .pattern("# ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETRITUS_BUCKET.get())
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.DETRITUS_INGOT.get())
                .unlockedBy("has_detritus", has(ModItems.DETRITUS_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DRYING_RACK.get())
                .pattern("---")
                .pattern("###")
                .define('#', Blocks.BONE_BLOCK)
                .define('-', ModItems.GRISTLE.get())
                .unlockedBy("has_bone_block", has(Blocks.BONE_BLOCK))
                .save(recipeOutput);

    }
}
