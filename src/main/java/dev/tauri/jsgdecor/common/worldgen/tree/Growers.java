package dev.tauri.jsgdecor.common.worldgen.tree;

import dev.tauri.jsgdecor.common.registry.ConfiguredFeaturesRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class Growers {
    public static class LemonTreeGrower extends AbstractTreeGrower {
        @Override
        @ParametersAreNonnullByDefault
        protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
            return ConfiguredFeaturesRegistry.LEMON_TREE;
        }
    }
}
