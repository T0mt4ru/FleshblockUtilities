package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.custom.block.HairGrowthBlock;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        // blocks that drop themselves
        dropSelf(ModBlocks.DETRITUS_BLOCK.get());
        dropSelf(ModBlocks.FLESH_CRAFTING_TABLE.get());
        dropSelf(ModBlocks.DRYING_RACK.get());
        dropSelf(ModBlocks.FLESH_CHEST.get());

        //blocks that don't drop themselves
        dropOther(ModBlocks.TILLED_FLESH.get(), BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","flesh")));

        //"crops"
        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HAIR_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HairGrowthBlock.AGE, 3));
        this.add(ModBlocks.HAIR_GROWTH.get(), this.createCropDrops(ModBlocks.HAIR_GROWTH.get(),
                ModItems.HAIR_STRAND.get(), ModItems.HAIR_FOLLICLES.get(), lootItemConditionBuilder));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
