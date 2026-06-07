package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsg.core.JSGCore;
import dev.tauri.jsg.core.common.registry.tag.CoreBiomeTags;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDecorBiomeTagGenerator extends BiomeTagsProvider {
    public JSGDecorBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(JSGDecorBiomeTags.HAS_LEMON_TREE).add(
                Biomes.FOREST,
                Biomes.FLOWER_FOREST,
                Biomes.WINDSWEPT_FOREST,
                Biomes.BIRCH_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.SPARSE_JUNGLE,
                Biomes.SUNFLOWER_PLAINS
        );
    }
}
