package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.common.registry.JSGDecorPlacement;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;

public class JSGDecorBiomeModifiers {
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        Holder<PlacedFeature> lemonTreePlaced = placedFeatures.getOrThrow(JSGDecorPlacement.LEMON_TREE_PLACED_FEATURE);
        HolderSet<Biome> targetBiomes = biomes.getOrThrow(JSGDecorBiomeTags.HAS_LEMON_TREE);

        context.register(JSGDecorPlacement.LEMON_TREE_BIOME_MODIFIER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(lemonTreePlaced),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}
