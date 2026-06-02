package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgdecor.JSGDecor;
import net.minecraftforge.eventbus.api.IEventBus;

public class JSGDecorRegistriesInit {

    public static void init() {
        JSGDecorTabs.init();
        JSGDecorEntities.init();
        JSGDecorBlocks.init();
        JSGDecorBlockEntities.init();
    }

    public static void register(IEventBus bus) {
        JSGDecor.REGISTRY_HELPER.register(bus);
    }

}
