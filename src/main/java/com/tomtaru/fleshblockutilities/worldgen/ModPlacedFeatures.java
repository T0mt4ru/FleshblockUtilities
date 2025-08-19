package com.tomtaru.fleshblockutilities.worldgen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> IRON_DEPOSIT_PLACED_KEY = registerKey("iron_deposit_placed");
    public static final ResourceKey<PlacedFeature> BUDDING_NEPHROLITH_PLACED_KEY = registerKey("budding_nephrolith_placed");
    public static final ResourceKey<PlacedFeature> CORPUS_NODE_PLACED_KEY = registerKey("corpus_node_placed");
    public static final ResourceKey<PlacedFeature> FAT_DEPOSIT_PLACED_KEY = registerKey("fat_deposit_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        var configuresFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, IRON_DEPOSIT_PLACED_KEY, configuresFeatures.getOrThrow(ModConfiguredFeatures.IRON_DEPOSIT_KEY),
                ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(49))));

        register(context, BUDDING_NEPHROLITH_PLACED_KEY, configuresFeatures.getOrThrow(ModConfiguredFeatures.BUDDING_NEPHROLITH_KEY),
                ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(50), VerticalAnchor.absolute(100))));

        register(context, CORPUS_NODE_PLACED_KEY, configuresFeatures.getOrThrow(ModConfiguredFeatures.CORPUS_NODE_KEY),
                ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(24), VerticalAnchor.absolute(100))));

        register(context, FAT_DEPOSIT_PLACED_KEY, configuresFeatures.getOrThrow(ModConfiguredFeatures.FAT_BLOCK_KEY),
                ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(25))));

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
