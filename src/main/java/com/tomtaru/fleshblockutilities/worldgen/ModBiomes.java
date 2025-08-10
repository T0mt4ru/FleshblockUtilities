package com.tomtaru.fleshblockutilities.worldgen;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModBiomes {
    public static final ResourceKey<Biome> INSIDE_THULMARU = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(FleshblockUtilities.MODID, "inside_thulmaru"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(INSIDE_THULMARU, insideThulmaru(context));
    }


    public static Biome insideThulmaru(BootstrapContext<Biome> context) {

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
                //.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.IRON_DEPOSIT_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(-10073793)
                        .waterFogColor(-12373460)
                        .skyColor(0)
                        .grassColorOverride(-9546163)
                        .foliageColorOverride(-12108745)
                        .fogColor(-12767191)
                        .build())
                .build();
    }
}