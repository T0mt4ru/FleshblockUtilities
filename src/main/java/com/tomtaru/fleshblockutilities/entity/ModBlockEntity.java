package com.tomtaru.fleshblockutilities.entity;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FleshblockUtilities.MODID);

    public static final Supplier<BlockEntityType<DryingRackEntity>> DRYING_RACK_BE =
            BLOCK_ENTITIES.register("drying_rack_be", () -> BlockEntityType.Builder.of(
                    DryingRackEntity:: new, ModBlocks.DRYING_RACK.get()).build(null));

    public static final Supplier<BlockEntityType<FleshChestEntity>> FLESH_CHEST_BE =
            BLOCK_ENTITIES.register("flesh_chest_be", () -> BlockEntityType.Builder.of(
                    FleshChestEntity:: new, ModBlocks.FLESH_CHEST.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
