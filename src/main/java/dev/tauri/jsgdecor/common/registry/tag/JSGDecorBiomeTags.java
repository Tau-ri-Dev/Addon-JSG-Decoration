package dev.tauri.jsgdecor.common.registry.tag;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class JSGDecorBiomeTags {
    public static TagKey<Biome> HAS_LEMON_TREE = tag("has_lemon_tree");

    private static TagKey<Biome> tag(String name) {
        return TagKey.create(Registries.BIOME, JSGMapping.rl(JSGDecor.MOD_ID, name));
    }
}
