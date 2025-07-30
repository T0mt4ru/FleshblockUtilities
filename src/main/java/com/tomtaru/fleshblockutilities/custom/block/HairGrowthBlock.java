package com.tomtaru.fleshblockutilities.custom.block;

import com.tomtaru.fleshblockutilities.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class HairGrowthBlock extends BeetrootBlock {
//    public static final int MAX_AGE = 3;
//    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
//    private static final VoxelShape[] SHAPE_BY_AGE =
//            new VoxelShape[] {
//            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
//            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
//            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
//            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
//    };

    public HairGrowthBlock(Properties properties) {
        super(properties);
    }

//    @Override
//    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        return SHAPE_BY_AGE[state.getValue(AGE)];
//    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.HAIR_FOLLICLES.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

//    @Override
//    public int getMaxAge() {
//        return MAX_AGE;
//    }
}
