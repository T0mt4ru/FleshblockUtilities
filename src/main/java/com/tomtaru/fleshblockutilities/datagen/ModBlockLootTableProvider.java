package com.tomtaru.fleshblockutilities.datagen;

import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.custom.block.*;
import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // blocks that drop themselves
        dropSelf(ModBlocks.DETRITUS_BLOCK.get());
        dropSelf(ModBlocks.FLESH_CRAFTING_TABLE.get());
        dropSelf(ModBlocks.DRYING_RACK.get());
        dropSelf(ModBlocks.FLESH_CHEST.get());
        dropSelf(ModBlocks.NEPHROLITH_BLOCK.get());

        //blocks that don't drop themselves
        dropOther(ModBlocks.TILLED_FLESH.get(), BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","flesh")));

        add(ModBlocks.IRON_DEPOSIT.get(),
                block -> createOreDrop(ModBlocks.IRON_DEPOSIT.get(), Items.RAW_IRON.asItem()));

        add(ModBlocks.NEPHROLITH_CLUSTER.get(),
                block -> createOreDrop(ModBlocks.NEPHROLITH_CLUSTER.get(), ModItems.NEPHROLITH_SHARD.get()));

        add(ModBlocks.FAT_DEPOSIT.get(),
                block ->createMultipleOreDrops(ModBlocks.FAT_DEPOSIT.get(),BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "fat")),2,5));


        //blocks that drop with Silk Touch
        dropWhenSilkTouch(ModBlocks.SMALL_NEPHROLITH_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_NEPHROLITH_BUD.get());
        dropWhenSilkTouch(ModBlocks.LARGE_NEPHROLITH_BUD.get());

        add(ModBlocks.BUDDING_NEPHROLITH.get(),
                block -> noDrop());

        //"crops"
        LootItemCondition.Builder lootItemConditionBuilderHair = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HAIR_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HairGrowthBlock.AGE, 3));
        this.add(ModBlocks.HAIR_GROWTH.get(), this.createCropDrops(ModBlocks.HAIR_GROWTH.get(),
                ModItems.HAIR_STRAND.get(), ModItems.HAIR_FOLLICLES.get(), lootItemConditionBuilderHair));

        LootItemCondition.Builder lootItemConditionBuilderStomach = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STOMACH_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StomachGrowthBlock.AGE, 7));
        this.add(ModBlocks.STOMACH_GROWTH.get(), this.createCropDrops(ModBlocks.STOMACH_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "stomach")), ModItems.STOMACH_GERMS.get(), lootItemConditionBuilderStomach));

        LootItemCondition.Builder lootItemConditionBuilderBrain = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BRAIN_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BrainGrowthBlock.AGE, 7));
        this.add(ModBlocks.BRAIN_GROWTH.get(), this.createCropDrops(ModBlocks.BRAIN_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "liver")), ModItems.BRAIN_GERMS.get(), lootItemConditionBuilderBrain));

        LootItemCondition.Builder lootItemConditionBuilderLiver = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LIVER_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LiverGrowthBlock.AGE, 7));
        this.add(ModBlocks.LIVER_GROWTH.get(), this.createCropDrops(ModBlocks.LIVER_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "liver")), ModItems.LIVER_GERMS.get(), lootItemConditionBuilderLiver));

        LootItemCondition.Builder lootItemConditionBuilderHeart = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HEART_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HeartGrowthBlock.AGE, 7));
        this.add(ModBlocks.HEART_GROWTH.get(), this.createCropDrops(ModBlocks.HEART_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "heart")), ModItems.HEART_GERMS.get(), lootItemConditionBuilderHeart));

        LootItemCondition.Builder lootItemConditionBuilderKidney = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KIDNEY_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KidneyGrowthBlock.AGE, 7));
        this.add(ModBlocks.KIDNEY_GROWTH.get(), this.createCropDrops(ModBlocks.KIDNEY_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "kidney")), ModItems.KIDNEY_GERMS.get(), lootItemConditionBuilderKidney));

        LootItemCondition.Builder lootItemConditionBuilderLung = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.LUNG_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LungGrowthBlock.AGE, 7));
        this.add(ModBlocks.LUNG_GROWTH.get(), this.createCropDrops(ModBlocks.LUNG_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "lung")), ModItems.LUNG_GERMS.get(), lootItemConditionBuilderLung));

        LootItemCondition.Builder lootItemConditionBuilderIntestinal = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.INTESTINAL_GROWTH.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(IntestinalGrowthBlock.AGE, 7));
        this.add(ModBlocks.INTESTINAL_GROWTH.get(), this.createCropDrops(ModBlocks.INTESTINAL_GROWTH.get(),
                BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("macabre", "intestines")), ModItems.INTESTINAL_GERMS.get(), lootItemConditionBuilderIntestinal));

        // Corpus node

        this.add(ModBlocks.CORPUS_NODE.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "heart")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "brain")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "kidneys")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "lungs")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "intestines")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "liver")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("macabre", "stomach")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))
                                        .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(
                                                        ResourceLocation.fromNamespaceAndPath("fleshblockutilities", "booger")))
                                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                                .setWeight(30))));

    }


    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float midDrops, float maxDrops) {
    HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
    return this.createSilkTouchDispatchTable(pBlock,
            this.applyExplosionCondition(pBlock, LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(midDrops, maxDrops)))
                    .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
