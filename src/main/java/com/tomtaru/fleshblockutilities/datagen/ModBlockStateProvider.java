package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.custom.block.HairGrowthBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FleshblockUtilities.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlocks.TILLED_FLESH);
        //blockWithItem(ModBlocks.FLESH_CRAFTING_TABLE);
        blockWithItem(ModBlocks.DETRITUS_BLOCK);

        // "crops"
        makeCrop(((CropBlock) ModBlocks.HAIR_GROWTH.get()), "hair_growth_stage", "hair_growth_stage");

    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function< BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((HairGrowthBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, "block/" + textureName + state.getValue(((HairGrowthBlock) block).getAgeProperty()))).renderType("cutout"));
return models;
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
