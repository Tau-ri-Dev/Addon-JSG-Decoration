package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.datagen.worldgen.JSGDecorConfiguredFeatures;
import dev.tauri.jsgdecor.datagen.worldgen.JSGDecorPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class JSGDecorWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, JSGDecorConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, JSGDecorPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, JSGDecorBiomeModifiers::bootstrap);

    public JSGDecorWorldGenProvider(PackOutput output, CompletableFuture<net.minecraft.core.HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(JSGDecor.MOD_ID));

    }
}
