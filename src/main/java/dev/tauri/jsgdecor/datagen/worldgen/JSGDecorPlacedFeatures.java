package dev.tauri.jsgdecor.datagen.worldgen;

import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorFeatures;
import dev.tauri.jsgdecor.common.registry.JSGDecorPlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class JSGDecorPlacedFeatures {
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> lemonTreeConfiguredFeature = configuredFeatures.getOrThrow(JSGDecorFeatures.LEMON_TREE);

        WeightedListInt weightedCount = new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                .add(ConstantInt.of(1), 16)
                .add(ConstantInt.of(2), 5)
                .add(ConstantInt.of(4), 1)
                .build());

        context.register(JSGDecorPlacement.LEMON_TREE_PLACED_FEATURE, new PlacedFeature(
                lemonTreeConfiguredFeature,
                List.of(
                        CountPlacement.of(weightedCount),
                        InSquarePlacement.spread(),
                        SurfaceWaterDepthFilter.forMaxDepth(0),

                        HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),

                        BiomeFilter.biome(),

                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
                                JSGDecorBlocks.WOOD_BLOCKS.get("lemon_sapling").get().defaultBlockState(),
                                BlockPos.ZERO
                        )),
                        RarityFilter.onAverageOnceEvery(40)
                )
        ));
    }
}
