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
        basicItem(ModItems.DETRITUS_LUMP.get());
        basicItem(ModItems.HAIR_FOLLICLES.get());
        basicItem(ModItems.HAIR_STRAND.get());
        basicItem(ModItems.CORPUSITE_INGOT.get());
        basicItem(ModItems.KERATITE_INGOT.get());
        basicItem(ModItems.NEPHROLITH_SHARD.get());
        basicItem(ModItems.NEPHROLITH_SHEET.get());
        basicItem(ModItems.NEPHROLITH_DUST.get());
        basicItem(ModItems.BOOGER.get());
        basicItem(ModItems.STEM_CELL.get());
        basicItem(ModItems.STOMACH_GERMS.get());

        handheldItem(ModItems.DETRITUS_AXE.get());
        handheldItem(ModItems.DETRITUS_PICKAXE.get());
        handheldItem(ModItems.DETRITUS_SWORD.get());
        handheldItem(ModItems.DETRITUS_HOE.get());
        handheldItem(ModItems.DETRITUS_SHOVEL.get());

        handheldItem(ModItems.NEPHROLITH_AXE.get());
        handheldItem(ModItems.NEPHROLITH_PICKAXE.get());
        handheldItem(ModItems.NEPHROLITH_SWORD.get());
        handheldItem(ModItems.NEPHROLITH_HOE.get());
        handheldItem(ModItems.NEPHROLITH_SHOVEL.get());
    }
}
