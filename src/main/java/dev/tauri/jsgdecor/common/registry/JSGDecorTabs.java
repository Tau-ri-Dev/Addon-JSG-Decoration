package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsg.core.common.registry.helper.TabBuilder;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JSGDecorTabs {
    public static final RegistryObject<CreativeModeTab> JSGD_TAB = JSGDecor.REGISTRY_HELPER.tab().register("decor", TabBuilder.create(JSGMapping.rl(MOD_ID, "decor")).withIcon(() -> JSGDecorBlocks.LEMON_LOG).build());
    public static final RegistryObject<CreativeModeTab> JSGD_ATLANTIS_TAB = JSGDecor.REGISTRY_HELPER.tab().register("atlantis_decor", TabBuilder.create(JSGMapping.rl(MOD_ID, "atlantis_decor")).withIcon(() -> JSGDecorBlocks.STANDARD_WALL_BLOCK).build());

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
    }
}
