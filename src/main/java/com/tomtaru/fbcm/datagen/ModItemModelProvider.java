package com.tomtaru.fbcm.datagen;

import com.tomtaru.fbcm.FleshblockCompanionMod;
import com.tomtaru.fbcm.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, FleshblockCompanionMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GRISTLE.get());
        basicItem(ModItems.DETRITUS_NUGGET.get());
        basicItem(ModItems.DETRITUS_INGOT.get());
    }
}
