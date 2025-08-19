package com.tomtaru.fleshblockutilities.worldgen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.util.ModTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_IRON_DEPOSIT = registerKey("add_iron_deposit");
    public static final ResourceKey<BiomeModifier> ADD_BUDDING_NEPHROLITH = registerKey("add_budding_nephrolith");
    public static final ResourceKey<BiomeModifier> ADD_CORPUS_NODE = registerKey("add_corpus_node");
    public static final ResourceKey<BiomeModifier> ADD_FAT_DEPOSIT = registerKey("add_fat_deposit");

    public static void  bootstrap(BootstrapContext<BiomeModifier> context) {

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_IRON_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.IS_THULMARU),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.IRON_DEPOSIT_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_BUDDING_NEPHROLITH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.IS_THULMARU),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BUDDING_NEPHROLITH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_CORPUS_NODE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.IS_THULMARU),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CORPUS_NODE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_FAT_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.IS_THULMARU),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FAT_DEPOSIT_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, name));
    }
}
