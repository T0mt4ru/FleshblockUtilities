package com.tomtaru.fleshblockutilities.item;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import com.tomtaru.fleshblockutilities.custom.item.ModToolTier;
import de.cech12.bucketlib.api.item.UniversalBucketItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FleshblockUtilities.MODID);

    public static final DeferredItem<Item> GRISTLE = ITEMS.register("gristle",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HAIR_STRAND = ITEMS.register("hair_strand",
            () -> new Item(new Item.Properties()));

//Detritus components
    public static final DeferredItem<Item> DETRITUS_NUGGET = ITEMS.register("detritus_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DETRITUS_INGOT = ITEMS.register("detritus_ingot",
            () -> new Item(new Item.Properties()));
//Detritus tools
    public static final DeferredItem<ShovelItem> DETRITUS_SHOVEL = ITEMS.register("detritus_shovel",
            () -> new ShovelItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTier.DETRITUS,1.5f,-3.0f))));

    public static final DeferredItem<AxeItem> DETRITUS_AXE = ITEMS.register("detritus_axe",
            () -> new AxeItem(ModToolTier.DETRITUS, new  Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTier.DETRITUS,7,-3.2f))));

    public static final DeferredItem<SwordItem> DETRITUS_SWORD = ITEMS.register("detritus_sword",
            () -> new SwordItem(ModToolTier.DETRITUS, new  Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTier.DETRITUS,3,-2.4f))));

    public static final DeferredItem<PickaxeItem> DETRITUS_PICKAXE = ITEMS.register("detritus_pickaxe",
            () -> new PickaxeItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTier.DETRITUS,1.0f,-2.8f))));

    public static final DeferredItem<HoeItem> DETRITUS_HOE = ITEMS.register("detritus_hoe",
            () -> new HoeItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTier.DETRITUS,-1f,-2.0f))));

    public static final DeferredItem<ShearsItem> DETRITUS_SHEARS = ITEMS.register("detritus_shears",
            () -> new ShearsItem(new Item.Properties().durability(100).component(DataComponents.TOOL, ShearsItem.createToolProperties())));

    public static final DeferredItem<Item> DETRITUS_BUCKET = ITEMS.register("detritus_bucket",
            () -> new UniversalBucketItem(new UniversalBucketItem.Properties()));

    // "Seed" items
    public static final DeferredItem<Item> HAIR_FOLLICLES = ITEMS.register("hair_follicles",
            () -> new ItemNameBlockItem(ModBlocks.HAIR_GROWTH.get(), new  Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

