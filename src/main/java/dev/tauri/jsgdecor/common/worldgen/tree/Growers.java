package dev.tauri.jsgdecor.common.worldgen.tree;

import dev.tauri.jsgdecor.common.registry.ConfiguredFeaturesRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Growers {

    public static final Supplier<AbstractTreeGrower> LEMON_TREE_GROWER = createGrower((random, hasFlowers) -> ConfiguredFeaturesRegistry.LEMON_TREE);


    public static Supplier<AbstractTreeGrower> createGrower(BiFunction<RandomSource, Boolean, ResourceKey<ConfiguredFeature<?, ?>>> configuredFeature) {
        return () -> new AbstractTreeGrower() {
            @Override
            @ParametersAreNonnullByDefault
            protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
                return configuredFeature.apply(pRandom, pHasFlowers);
            }
        };
    }
}
