package com.tomtaru.fleshblockutilities.compat;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.recipe.DryingRackRecipe;
import com.tomtaru.fleshblockutilities.recipe.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIFleshblockutilitiesPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DryingRackRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<DryingRackRecipe> dryingRackRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.DRYING_RACK_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(DryingRackRecipeCategory.DRYING_RACK_RECIPE_RECIPE_TYPE, dryingRackRecipes);
    }
}
