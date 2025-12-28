package dev.tauri.jsgdecor.common.worldgen.tree;

import dev.tauri.jsg.JSG;
import dev.tauri.jsg.worldgen.poolinject.TemplatePoolInjector;
import net.minecraft.resources.ResourceLocation;

public class StructuresInjector {
    public static void register() {
        new TemplatePoolInjector.Builder()
                .addPool(new ResourceLocation(JSG.MOD_ID, "abydos/main_pyramid/braziers"))
                .setClearPool()
                .addAddition("jsg_decor:abydos/brazier_off",1)
                .addAddition("jsg_decor:abydos/brazier_on",2)
                .submit();
        new TemplatePoolInjector.Builder()
                .addPool(new ResourceLocation(JSG.MOD_ID, "abydos/city/braziers"))
                .setClearPool()
                .addAddition("minecraft:empty_pool_element",2)
                .addAddition("jsg_decor:abydos/brazier_off",1)
                .addAddition("jsg_decor:abydos/brazier_on",1)
                .submit();
    }
}
