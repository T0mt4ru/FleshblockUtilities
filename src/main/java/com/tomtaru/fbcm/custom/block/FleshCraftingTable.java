package com.tomtaru.fbcm.custom.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class FleshCraftingTable extends CraftingTableBlock {
    public static final MapCodec<FleshCraftingTable> CODEC = simpleCodec(FleshCraftingTable::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    @Override
    public MapCodec<? extends CraftingTableBlock> codec() {
        return CODEC;
    }

    public FleshCraftingTable(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected @NotNull MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        // We are now creating an anonymous inner class that extends CraftingMenu directly.
        // This leverages the vanilla crafting table menu logic.
        return new SimpleMenuProvider(
                (syncId, playerInventory, player) -> new CraftingMenu(syncId, playerInventory, ContainerLevelAccess.create(level, pos)) {
                    @Override
                    public boolean stillValid(Player pPlayer) {
                        return true;
                    }
                }, CONTAINER_TITLE
        );
    }
}