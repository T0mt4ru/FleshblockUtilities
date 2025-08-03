package com.tomtaru.fleshblockutilities.entity;


import com.tomtaru.fleshblockutilities.recipe.DryingRackRecipe;
import com.tomtaru.fleshblockutilities.recipe.DryingRackRecipeInput;
import com.tomtaru.fleshblockutilities.recipe.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingRackEntity extends BlockEntity {
    public final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected  int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        protected void onContentChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(),3);
            }
            DryingRackEntity.this.onInventoryChanged(slot);
        }
    };

    private void onInventoryChanged(int slot) {
        setChanged();
        if (this.level != null && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(),getBlockState(), 3);
        }
    }

    private static final int DRYING_SLOT = 0;

    protected final ContainerData data;

    private int progress = 0;
    private int maxProgress = 600;

    public DryingRackEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntity.DRYING_RACK_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> DryingRackEntity.this.progress;
                    case 1 -> DryingRackEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: DryingRackEntity.this.progress = value;
                    case 1: DryingRackEntity.this.maxProgress = value;
                }

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }


    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i< inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("drying_rack.progress", progress);
        tag.putInt("drying_rack.max_progress", maxProgress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("drying_rack.progress");
        maxProgress = tag.getInt("drying_rack.max_progress");

        this.requestModelDataUpdate();
        this.onInventoryChanged(DRYING_SLOT);
    }

    public void tick(Level level1, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level1, blockPos, blockState);
            level.sendBlockUpdated(blockPos, blockState, blockState, 3);

            if (hasDryingFinished()) {
                dryItem();
                setChanged(level1, blockPos, blockState);
                level.sendBlockUpdated(blockPos, blockState, blockState, 3);
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void dryItem() {
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();
        inventory.extractItem(DRYING_SLOT, 1, false);
        inventory.setStackInSlot(DRYING_SLOT, new ItemStack(output.getItem(), 1));
        this.level.playSound(null, this.getBlockPos(), SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1, 1);
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 600;
    }

    private boolean hasDryingFinished() {
        return this.progress>= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return true;
    }

    private Optional<RecipeHolder<DryingRackRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.DRYING_RACK_TYPE.get(), new DryingRackRecipeInput(inventory.getStackInSlot(DRYING_SLOT)), level);
    }


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
