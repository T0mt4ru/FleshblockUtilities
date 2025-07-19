package com.tomtaru.fbcm.custom.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class TilledFleshBlock extends FarmBlock {
    public TilledFleshBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<TilledFleshBlock> CODEC = simpleCodec(TilledFleshBlock::new);

    @Override
    protected void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int i = state.getValue(MOISTURE);
        if (!isNearBlood(level, pos)) {
            if (i > 0) {
//                System.out.println(" Attempting to decrease moisture from " +i);
                level.setBlock(pos, state.setValue(MOISTURE, i - 1), 2);
//                System.out.println( "Moisture decreased to: " + (i-1));
            } else if (!shouldMaintainFarmland(level, pos)) {
                turnToFlesh(null, state, level, pos);
//                System.out.println(" Turned to flesh");
            }
        } else if (i < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
//            System.out.println(" Moisture increased to :7");
        }
    }
    private static boolean isNearBlood(LevelReader level, BlockPos pos) {
//        System.out.println(" Starting isNearBlood check for TilledFleshBlock at " + pos);
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
//            System.out.println(" isNearBlood checking: " + blockpos + " -> " + level.getBlockState(blockpos));
            if (level.getBlockState(blockpos) == (BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","blood")).defaultBlockState())
            || level.getBlockState(blockpos) == BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "flowing_blood")).defaultBlockState()) {
//                System.out.println(" Found Blood at " + blockpos + "!!!");
                return true;
            }
        }
//        System.out.println(" No blood found in the vicinity of TilledFleshBlock at " + pos);
        return false;
    }

    private static boolean shouldMaintainFarmland(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide
                && net.neoforged.neoforge.common.CommonHooks.onFarmlandTrample(level, pos, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","flesh")).defaultBlockState(), fallDistance, entity)) { // Forge: Move logic to Entity#canTrample
            turnToFlesh(entity, state, level, pos);
        }
    }

    public static void turnToFlesh(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        BlockState blockstate = pushEntitiesUp(state, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","flesh")).defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
    }


}