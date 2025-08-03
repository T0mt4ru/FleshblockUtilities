package com.tomtaru.fleshblockutilities.custom.block;

import com.mojang.serialization.MapCodec;
import com.tomtaru.fleshblockutilities.entity.DryingRackEntity;
import com.tomtaru.fleshblockutilities.entity.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class DryingRackBlock extends BaseEntityBlock {

    public DryingRackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final int DRYING_SLOT = 0;

    public static final MapCodec<DryingRackBlock> CODEC = simpleCodec(DryingRackBlock::new);


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DryingRackEntity(blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof DryingRackEntity dryingRackEntity) {
                dryingRackEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {

        // Client-side: Return SUCCESS for immediate visual/audio feedback (e.g., hand swing, sound)
        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        // Server-side: Perform the actual inventory logic
        if(level.getBlockEntity(pos) instanceof DryingRackEntity dryingRackEntity) {
            ItemStack dryingSlotStack = dryingRackEntity.inventory.getStackInSlot(DRYING_SLOT);
            ItemStack playerHeldItem = player.getItemInHand(hand);

            // --- Interaction Logic (Order Matters!) ---

            // Option A: Item in rack, right-click with anything -> extract and drop (pop off)
            if (!dryingSlotStack.isEmpty()) {
                ItemStack extracted = dryingRackEntity.inventory.extractItem(DRYING_SLOT, 1, false);
                if (!extracted.isEmpty()) {
                    Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, extracted);
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                    dryingRackEntity.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);

                    return ItemInteractionResult.SUCCESS;
                }
            }
            // Option B: No item in rack, player holding an item -> try to insert
            else if (!playerHeldItem.isEmpty()) {
                ItemStack remainder = dryingRackEntity.inventory.insertItem(DRYING_SLOT, playerHeldItem.copy(), false);
                if (remainder.getCount() != playerHeldItem.getCount()) {
                    playerHeldItem.shrink(playerHeldItem.getCount() - remainder.getCount());
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                    dryingRackEntity.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntity.DRYING_RACK_BE.get(),
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }

}

