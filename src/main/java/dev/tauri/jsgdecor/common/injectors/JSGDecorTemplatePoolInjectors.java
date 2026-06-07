package dev.tauri.jsgdecor.common.injectors;

import dev.tauri.jsg.core.common.worldgen.TemplatePoolInjector;
import net.minecraft.resources.ResourceLocation;

public class JSGDecorTemplatePoolInjectors {
    public static void register() {
        TemplatePoolInjector.Builder
                .forTargets(new ResourceLocation("jsg:abydos/main_pyramid/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(new ResourceLocation("jsg_decor:abydos/main_pyramid/braziers")))
                .submit();

        TemplatePoolInjector.Builder
                .forTargets(new ResourceLocation("jsg:abydos/city/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(new ResourceLocation("jsg_decor:abydos/city/braziers")))
                .submit();

        TemplatePoolInjector.Builder
                .forTargets(new ResourceLocation("jsg:abydos/dungeon/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(new ResourceLocation("jsg_decor:abydos/dungeon/braziers")))
                .submit();
    }
}
