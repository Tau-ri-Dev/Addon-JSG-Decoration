package dev.tauri.jsgdecor.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);


    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
