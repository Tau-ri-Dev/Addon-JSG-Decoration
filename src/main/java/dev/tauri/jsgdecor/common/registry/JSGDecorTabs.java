package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgcore.common.registry.helper.TabBuilder;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;
import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;

@EventBusSubscriber(modid = MOD_ID)
public class JSGDecorTabs {
    public static final RegistryObjectWrapper<CreativeModeTab> JSGD_BUILDING_TAB = JSGDecor.REGISTRY_HELPER.tab().register("building_blocks", TabBuilder.create(JSGMapping.rl(MOD_ID, "building_blocks"))
            .withIcon(() -> COMMON_BLOCKS.get("blue_atlantis_wall_block"))
            .withIcon(() -> COMMON_BLOCKS.get("gray_atlantis_wall_block"))
            .withIcon(() -> STONE_BASED_DECORATION_BLOCKS.get("naquadah_alloy_big_tiles_block"))
            .withIcon(() -> STONE_BASED_DECORATION_BLOCKS.get("trinium_carved_pillar_block"))
            .build());

    public static void init() {
    }

    /**
     * Handling vanilla recipes hook
     */
    @SubscribeEvent
    public static void buildTabsContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (var type : JSGDecorEntities.JSGD_BOAT.item().values()) event.accept(type.get());
            for (var type : JSGDecorEntities.JSGD_CHEST_BOAT.item().values()) event.accept(type.get());
        }

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            for (var brazier : JSGDecorBlocks.BRAZIERS.values()) event.accept(brazier.get());
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            for (var block : WOOD_BLOCKS.values()) {
                Block blockType = block.get();

                if (blockType instanceof FenceGateBlock || blockType instanceof DoorBlock ||
                        blockType instanceof TrapDoorBlock || blockType instanceof PressurePlateBlock ||
                        blockType instanceof ButtonBlock) {

                    event.accept(block.get());
                }
            }
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            for (var block : WOOD_BLOCKS.values()) {
                Block blockType = block.get();
                if (blockType instanceof RotatedPillarBlock || blockType instanceof LeavesBlock ||
                        blockType instanceof SaplingBlock) {
                    event.accept(block.get());
                }
            }
        }

        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            for (var block : GLASS_BLOCKS.values()) {
                event.accept(block.get());
            }
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {

            for (var block : OVERLAY_BLOCKS.values()) {
                event.accept(block.get());
            }

            for (var block : COMMON_BLOCKS.values()) {
                event.accept(block.get());
            }

            for (var block : WOOD_BLOCKS.values()) {
                Block blockType = block.get();
                if (!(blockType instanceof LeavesBlock) && !(blockType instanceof SaplingBlock) &&
                        !(blockType instanceof ButtonBlock) && !(blockType instanceof PressurePlateBlock)) {
                    event.accept(block.get());
                }
            }
        }
    }
}
