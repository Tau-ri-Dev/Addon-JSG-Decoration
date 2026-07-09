package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsg.core.common.registry.helper.TabBuilder;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JSGDecorTabs {
    public static final RegistryObject<CreativeModeTab> JSGD_BUILDING_TAB = JSGDecor.REGISTRY_HELPER.tab().register("building_blocks", TabBuilder.create(JSGMapping.rl(MOD_ID, "building_blocks"))
            .withIcon(() -> JSGDecorBlocks.ATLANTIS_BLOCKS.get("blue_atlantis_block"))
            .withIcon(() -> JSGDecorBlocks.ATLANTIS_BLOCKS.get("gray_atlantis_wall_block"))
            .withIcon(() -> JSGDecorBlocks.CORE_DECORATION_BLOCKS.get("naquadah_alloy_big_tiles_block"))
            .withIcon(() -> JSGDecorBlocks.CORE_DECORATION_BLOCKS.get("trinium_carved_pillar_block"))
            .build());

    public static void init() {
    }

    /**
     * Handling vanilla recipes hook
     */
    @SubscribeEvent
    public static void buildTabsContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (var type : JSGDecorEntities.JSGD_BOAT.item().values())
                event.accept(type);
            for (var type : JSGDecorEntities.JSGD_CHEST_BOAT.item().values())
                event.accept(type);
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(JSGDecorBlocks.LEMON_GATE.get());
            event.accept(JSGDecorBlocks.LEMON_DOOR.get());
            event.accept(JSGDecorBlocks.LEMON_TRAPDOOR.get());
            event.accept(JSGDecorBlocks.LEMON_PRESSURE_PLATE.get());
            event.accept(JSGDecorBlocks.LEMON_BUTTON.get());
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(JSGDecorBlocks.LEMON_LOG.get());
            event.accept(JSGDecorBlocks.LEMON_LOG_STRIPPED.get());
            event.accept(JSGDecorBlocks.LEMON_WOOD.get());
            event.accept(JSGDecorBlocks.LEMON_WOOD_STRIPPED.get());
            event.accept(JSGDecorBlocks.LEMON_LEAVES.get());
            event.accept(JSGDecorBlocks.LEMON_SAPLING.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(JSGDecorBlocks.LEMON_LOG.get());
            event.accept(JSGDecorBlocks.LEMON_LOG_STRIPPED.get());
            event.accept(JSGDecorBlocks.LEMON_WOOD.get());
            event.accept(JSGDecorBlocks.LEMON_WOOD_STRIPPED.get());
            event.accept(JSGDecorBlocks.LEMON_PLANKS.get());
            event.accept(JSGDecorBlocks.LEMON_STAIRS.get());
            event.accept(JSGDecorBlocks.LEMON_SLAB.get());
            event.accept(JSGDecorBlocks.LEMON_FENCE.get());
            event.accept(JSGDecorBlocks.LEMON_GATE.get());
            event.accept(JSGDecorBlocks.LEMON_DOOR.get());
            event.accept(JSGDecorBlocks.LEMON_TRAPDOOR.get());
            event.accept(JSGDecorBlocks.LEMON_PRESSURE_PLATE.get());
            event.accept(JSGDecorBlocks.LEMON_BUTTON.get());
        }
        if (event.getTabKey() == (CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            for (RegistryObject<BrazierBlock> brazier : JSGDecorBlocks.BRAZIERS.values()) {
                event.accept(brazier.get());
            }
        }
    }
}
