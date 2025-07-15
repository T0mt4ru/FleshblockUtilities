package com.tomtaru.fbcm.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;

public class TilledFleshBlock extends Block { //FarmBlock {

    public TilledFleshBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.DIRT)
                .strength(0.6F)
                .sound(SoundType.WET_GRASS)
                .pushReaction(PushReaction.NORMAL)
                .isViewBlocking((state, level, pos) -> false)
                .ignitedByLava());
    }

}