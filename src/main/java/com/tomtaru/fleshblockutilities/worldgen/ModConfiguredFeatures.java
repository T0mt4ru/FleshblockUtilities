package com.tomtaru.fleshblockutilities.worldgen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> IRON_DEPOSIT_KEY = registerKey("iron_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUDDING_NEPHROLITH_KEY = registerKey("budding_nephrolith");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORPUS_NODE_KEY = registerKey("corpus_node");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAT_BLOCK_KEY = registerKey("fat_block");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest   fleshReplaceables = new TagMatchTest(ModTags.Blocks.FLESH_DEPOSIT_REPLACEABLES);

        register(context, IRON_DEPOSIT_KEY, Feature.ORE, new OreConfiguration(fleshReplaceables,
                ModBlocks.IRON_DEPOSIT.get().defaultBlockState(), 4));

        register(context, BUDDING_NEPHROLITH_KEY, Feature.ORE, new OreConfiguration(fleshReplaceables,
                ModBlocks.BUDDING_NEPHROLITH.get().defaultBlockState(), 3));

        register(context, CORPUS_NODE_KEY, Feature.ORE, new OreConfiguration(fleshReplaceables,
            ModBlocks.CORPUS_NODE.get().defaultBlockState(), 4));

        register(context, FAT_BLOCK_KEY, Feature.ORE, new OreConfiguration(fleshReplaceables,
            ModBlocks.FAT_DEPOSIT.get().defaultBlockState(), 5));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
