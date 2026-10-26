package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.common.registry.JSGDecorPlacement;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorBiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

public class JSGDecorBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        var lemonTreePlaced = placedFeatures.getOrThrow(JSGDecorPlacement.LEMON_TREE_PLACED_FEATURE);
        var targetBiomes = biomes.getOrThrow(JSGDecorBiomeTags.HAS_LEMON_TREE);

        context.register(JSGDecorPlacement.LEMON_TREE_BIOME_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(lemonTreePlaced),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}
