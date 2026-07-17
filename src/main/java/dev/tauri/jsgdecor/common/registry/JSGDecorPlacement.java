package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class JSGDecorPlacement {
    public static final ResourceKey<PlacedFeature> LEMON_TREE_PLACED_FEATURE = placed("lemon_tree");

    public static final ResourceKey<BiomeModifier> LEMON_TREE_BIOME_MODIFIER = biomeModifier("lemon_tree");

    public static ResourceKey<PlacedFeature> placed(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JSGMapping.rl(JSGDecor.MOD_ID, name));
    }

    public static ResourceKey<BiomeModifier> biomeModifier(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, JSGMapping.rl(JSGDecor.MOD_ID, name));
    }
}
