package dev.tauri.jsgdecor.common.injectors;

import dev.tauri.jsg.core.common.worldgen.TemplatePoolInjector;
import dev.tauri.jsg.core.mapping.JSGMapping;

public class JSGDecorTemplatePoolInjectors {
    public static void register() {
        TemplatePoolInjector.Builder
                .forTargets(JSGMapping.rl("jsg:abydos/main_pyramid/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(JSGMapping.rl("jsg_decor:abydos/main_pyramid/braziers")))
                .submit();

        TemplatePoolInjector.Builder
                .forTargets(JSGMapping.rl("jsg:abydos/city/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(JSGMapping.rl("jsg_decor:abydos/city/braziers")))
                .submit();

        TemplatePoolInjector.Builder
                .forTargets(JSGMapping.rl("jsg:abydos/dungeon/braziers"))
                .clearPoolFirst()
                .add(new TemplatePoolInjector.PoolAddition(JSGMapping.rl("jsg_decor:abydos/dungeon/braziers")))
                .submit();
    }
}
