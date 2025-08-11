package com.tomtaru.fleshblockutilities.custom.block;

import com.mojang.serialization.MapCodec;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingNephrolithBlock extends BuddingAmethystBlock {
    public static final MapCodec<BuddingNephrolithBlock> CODEC = simpleCodec(BuddingNephrolithBlock::new);
    public static final int GROWTH_CHANCE = 3;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingAmethystBlock> codec() {
        return (MapCodec<BuddingAmethystBlock>) (MapCodec<?>) CODEC;
    }

    public BuddingNephrolithBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(GROWTH_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = ModBlocks.SMALL_NEPHROLITH_BUD.get();
            } else if (blockstate.is(ModBlocks.SMALL_NEPHROLITH_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_NEPHROLITH_BUD.get();
            } else if (blockstate.is(ModBlocks.MEDIUM_NEPHROLITH_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_NEPHROLITH_BUD.get();
            } else if (blockstate.is(ModBlocks.LARGE_NEPHROLITH_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocks.NEPHROLITH_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }
}
