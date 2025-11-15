package dev.tauri.jsgdecor.common.worldgen.tree;

import dev.tauri.jsg.JSG;
import dev.tauri.jsg.worldgen.poolinject.TemplatePoolInjector;
import net.minecraft.resources.ResourceLocation;

public class StructuresInjector {
    public static void register() {
        new TemplatePoolInjector.Builder()
                .addPool(new ResourceLocation(JSG.MOD_ID, "abydos/main_pyramid/braziers"))
                .setClearPool()
                .submit();
        new TemplatePoolInjector.Builder()
                .addPool(new ResourceLocation(JSG.MOD_ID, "abydos/main_pyramid/braziers_low_ground"))
                .setClearPool()
                .submit();
        new TemplatePoolInjector.Builder()
                .addPool(new ResourceLocation(JSG.MOD_ID, "abydos/main_pyramid/braziers_low_ground_red"))
                .setClearPool()
                .submit();
    }
}
