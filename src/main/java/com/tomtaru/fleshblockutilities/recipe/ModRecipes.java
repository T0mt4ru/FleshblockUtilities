package com.tomtaru.fleshblockutilities.recipe;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FleshblockUtilities.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FleshblockUtilities.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DryingRackRecipe>> DRYING_RACK_SERIALIZER =
            SERIALIZERS.register("drying_rack", DryingRackRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<DryingRackRecipe>> DRYING_RACK_TYPE =
            TYPES.register("drying_rack", () -> new RecipeType<DryingRackRecipe>() {
                @Override
                public String toString() {
                    return "drying_rack";
                }
            });

    public static void register(IEventBus eventBus) {
     SERIALIZERS.register(eventBus);
     TYPES.register(eventBus);
    }
}
