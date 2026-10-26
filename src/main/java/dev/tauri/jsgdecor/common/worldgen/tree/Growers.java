package dev.tauri.jsgdecor.common.worldgen.tree;

import dev.tauri.jsgdecor.common.registry.JSGDecorFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;
import java.util.function.Supplier;

public class Growers {

    public static final Supplier<TreeGrower> LEMON_TREE_GROWER = createGrower("lemon", 0.1f, JSGDecorFeatures.LEMON_TREE);


    public static Supplier<TreeGrower> createGrower(String name, float chance, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature) {
        return () -> new TreeGrower(name, chance, Optional.empty(), Optional.empty(), Optional.of(configuredFeature), Optional.empty(), Optional.empty(), Optional.empty());
    }
}
