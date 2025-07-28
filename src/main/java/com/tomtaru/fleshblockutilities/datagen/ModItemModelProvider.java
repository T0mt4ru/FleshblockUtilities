package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, FleshblockUtilities.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GRISTLE.get());
        basicItem(ModItems.DETRITUS_NUGGET.get());
        basicItem(ModItems.DETRITUS_INGOT.get());

        handheldItem(ModItems.DETRITUS_AXE.get());
        handheldItem(ModItems.DETRITUS_PICKAXE.get());
        handheldItem(ModItems.DETRITUS_SWORD.get());
        handheldItem(ModItems.DETRITUS_HOE.get());
        handheldItem(ModItems.DETRITUS_SHOVEL.get());
    }
}
