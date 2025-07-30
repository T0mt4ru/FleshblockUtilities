package com.tomtaru.fleshblockutilities.item;

import com.tomtaru.fleshblockutilities.FleshblockUtilities;
import com.tomtaru.fleshblockutilities.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FleshblockUtilities.MODID);

    public static final Supplier<CreativeModeTab> FLESHBLOCK_UTILITIES = CREATIVE_MODE_TAB.register("fleshblock_utilities_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DETRITUS_INGOT.get()))
                    .title(Component.translatable("creativetab.fleshblockutilities.fleshblock_utilities"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModItems.DETRITUS_AXE);
                        output.accept(ModItems.DETRITUS_BUCKET);
                        output.accept(ModItems.DETRITUS_HOE);
                        output.accept(ModItems.DETRITUS_INGOT);
                        output.accept(ModItems.DETRITUS_NUGGET);
                        output.accept(ModItems.DETRITUS_PICKAXE);
                        output.accept(ModItems.DETRITUS_SHEARS);
                        output.accept(ModItems.DETRITUS_SHOVEL);
                        output.accept(ModItems.DETRITUS_SWORD);

                        output.accept(ModBlocks.DETRITUS_BLOCK);
                        output.accept(ModBlocks.FLESH_CRAFTING_TABLE);
                        output.accept(ModBlocks.TILLED_FLESH);

                        output.accept(ModBlocks.DRYING_RACK);

                        output.accept(ModItems.HAIR_FOLLICLES);

                    }).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
