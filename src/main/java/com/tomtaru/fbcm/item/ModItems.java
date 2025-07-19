package com.tomtaru.fbcm.item;

import com.tomtaru.fbcm.FleshblockCompanionMod;
import com.tomtaru.fbcm.custom.item.ModToolTier;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FleshblockCompanionMod.MODID);

    public static final DeferredItem<Item> GRISTLE = ITEMS.register("gristle",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DETRITUS_NUGGET = ITEMS.register("detritus_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DETRITUS_INGOT = ITEMS.register("detritus_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<ShovelItem> DETRITUS_SHOVEL = ITEMS.register("detritus_shovel",
            () -> new ShovelItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTier.DETRITUS,1.5f,-3.0f))));
    public static final DeferredItem<AxeItem> DETRITUS_AXE = ITEMS.register("detritus_axe",
            () -> new AxeItem(ModToolTier.DETRITUS, new  Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTier.DETRITUS,6,-3.2f))));
    public static final DeferredItem<SwordItem> DETRITUS_SWORD = ITEMS.register("detritus_sword",
            () -> new SwordItem(ModToolTier.DETRITUS, new  Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTier.DETRITUS,5,-2.4f))));
    public static final DeferredItem<PickaxeItem> DETRITUS_PICKAXE = ITEMS.register("detritus_pickaxe",
            () -> new PickaxeItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTier.DETRITUS,1.0f,-2.8f))));
    public static final DeferredItem<HoeItem> DETRITUS_HOE = ITEMS.register("detritus_hoe",
            () -> new HoeItem(ModToolTier.DETRITUS, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTier.DETRITUS,0f,-3.0f))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

