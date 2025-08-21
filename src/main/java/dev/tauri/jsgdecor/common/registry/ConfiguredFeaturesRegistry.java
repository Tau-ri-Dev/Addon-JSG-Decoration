package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ConfiguredFeaturesRegistry {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_TREE = feature("lemon_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> feature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(JSGDecor.MOD_ID, name));
    }
}
