package com.tomtaru.fleshblockutilities.compat;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.entity.FleshChestEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

public class ModMountedStorage {
    private static DeferredRegister<MountedItemStorageType<?>> MOUNTED_STORAGE_TYPES;
    private static DeferredHolder<MountedItemStorageType<?>, ?> FLESH_CHEST_STORAGE;

    public static void register(IEventBus eventBus) {
        // Only register if Create is loaded
        if (ModList.get().isLoaded("create")) {
            MOUNTED_STORAGE_TYPES = DeferredRegister.create(CreateBuiltInRegistries.MOUNTED_ITEM_STORAGE_TYPE, FleshblockUtilities.MODID);

            FLESH_CHEST_STORAGE = MOUNTED_STORAGE_TYPES.register("flesh_chest_storage",
                    () -> new MountedItemStorageType<FleshChestMounted>(FleshChestMounted.CODEC) {
                        @Override
                        public @Nullable FleshChestMounted mount(Level level, BlockState blockState, BlockPos blockPos, @Nullable BlockEntity blockEntity) {
                            if (blockEntity instanceof FleshChestEntity chestEntity) {
                                return new FleshChestMounted(this, chestEntity);
                            }
                            return null;
                        }
                    });

            MOUNTED_STORAGE_TYPES.register(eventBus);
        }
    }
}