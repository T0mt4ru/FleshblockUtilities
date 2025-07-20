package com.tomtaru.fbcm;

import com.mojang.logging.LogUtils;
import com.tomtaru.fbcm.block.ModBlocks;
import com.tomtaru.fbcm.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// My classes



// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FleshblockCompanionMod.MODID)
public class FleshblockCompanionMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "fbcm";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FleshblockCompanionMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (FleshblockCompanionMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.DETRITUS_AXE);
            event.accept(ModItems.DETRITUS_HOE);
            event.accept(ModItems.DETRITUS_PICKAXE);
            event.accept(ModItems.DETRITUS_SHOVEL);
            event.accept(ModItems.DETRITUS_SWORD);

        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GRISTLE);
            event.accept(ModItems.DETRITUS_INGOT);
            event.accept(ModItems.DETRITUS_NUGGET);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TILLED_FLESH);
            event.accept(ModBlocks.FLESH_CRAFTING_TABLE);
            event.accept(ModBlocks.DETRITUS_BLOCK);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    @SubscribeEvent
    public void  onHoeUse(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() == ItemAbilities.HOE_TILL) {
            Level level = (Level) event.getLevel();
            BlockPos pos = event.getPos();
            BlockState blockState = event.getState();

            if (blockState.is(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("biomesoplenty","flesh")))) {
            if (event.getContext().getClickedFace() != Direction.DOWN) {
                event.setFinalState(ModBlocks.TILLED_FLESH.get().defaultBlockState());
                event.setCanceled(false);
            }
        }
    }
}
}
