package dev.tauri.jsgdecor.datagen.worldgen;

import dev.tauri.jsg.core.common.registry.CoreBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorFeatures;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;

public class JSGDecorConfiguredFeatures {
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        var lemonDecorator = new AttachedToLeavesDecorator(
                0.1F,
                1,
                1,
                BlockStateProvider.simple(CoreBlocks.LEMON_BLOCK.get().defaultBlockState()),
                1,
                List.of(Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
        );

        context.register(JSGDecorFeatures.LEMON_TREE, new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(JSGDecorBlocks.WOOD_BLOCKS.get("lemon_log").get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y)),

                        new FancyTrunkPlacer(7, 1, 0),

                        BlockStateProvider.simple(JSGDecorBlocks.WOOD_BLOCKS.get("lemon_leaves").get().defaultBlockState()
                                .setValue(BlockStateProperties.DISTANCE, 7)
                                .setValue(BlockStateProperties.PERSISTENT, false)
                                .setValue(BlockStateProperties.WATERLOGGED, false)),

                        new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),

                        new TwoLayersFeatureSize(1, 0, 2)
                )
                        .decorators(List.of(lemonDecorator))
                        .ignoreVines()
                        .build()
        ));
    }
}