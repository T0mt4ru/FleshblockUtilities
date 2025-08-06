package com.tomtaru.fleshblockutilities.compat;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.tomtaru.fleshblockutilities.entity.FleshChestEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class FleshChestMounted extends MountedItemStorage {
    private final FleshChestEntity chest;

    public static final MapCodec<FleshChestMounted> CODEC = MapCodec.unit(FleshChestMounted::new);

    public FleshChestMounted() {
        super(null);
        this.chest = null;
    }
    public FleshChestMounted(MountedItemStorageType<?> type, FleshChestEntity chest) {
        super(type);
        this.chest = chest;
    }

    public IItemHandler getInventory() {
        return this.chest.inventory;
    }

    @Override
    public void unmount(Level level, BlockState blockState, BlockPos blockPos, @Nullable BlockEntity blockEntity) {

    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        this.chest.inventory.setStackInSlot(slot, stack);
        this.chest.setChanged();

    }

    @Override
    public int getSlots() {
        return this.chest.inventory.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.chest.inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        ItemStack result = this.chest.inventory.insertItem(slot, stack, simulate);
        if (!simulate) {
            this.chest.setChanged();
        }
        return result;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack result = this.chest.inventory.extractItem(slot, amount, simulate);
        if (!simulate) {
            this.chest.setChanged();
        }
        return result;
    }

    @Override
    public int getSlotLimit(int slot) {
        return this.chest.inventory.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return this.chest.inventory.isItemValid(slot, stack);
    }
}
