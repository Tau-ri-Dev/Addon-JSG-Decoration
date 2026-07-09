package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class JSGDecorBlockStateProvider extends BlockStateProvider {
    public JSGDecorBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JSGDecor.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Core decor blocks

        for (Map.Entry<String, RegistryObject<Block>> entry : JSGDecorBlocks.CORE_DECORATION_BLOCKS.entrySet()) {
            String name = entry.getKey();
            Block block = entry.getValue().get();
            String baseName = name.replaceAll("_(block|slab|stairs)$", "");

            boolean isSmooth = baseName.endsWith("_smooth");

            String topTexture = baseName
                    .replace("sculpted_creeper", "burnished")
                    .replace("polished_tiles", "small_tiles");

            if (baseName.contains("sculpted_wither") && !baseName.contains("wither_skeleton")) {
                topTexture = baseName.replace("sculpted_wither", "burnished");
            }

            ResourceLocation side = modLoc("block/core_blocks_variants/" + baseName);
            ResourceLocation top  = modLoc("block/core_blocks_variants/" + (baseName.contains("sculpted_guardian") || baseName.contains("pillar") ? baseName + "_top" : topTexture));

            if (isSmooth) {
                String materialName = baseName.replace("_smooth", "");
                side = modLoc("block/core_blocks_variants/" + materialName + "_slab_side");
                top  = modLoc("block/core_blocks_variants/" + materialName + "_slab_top");
            }

            boolean difTopBottomTexture = !side.equals(top);
            if (isSmooth && !(block instanceof SlabBlock) && !(block instanceof StairBlock)) {
                side = top;
                difTopBottomTexture = false;
            }

            if (block instanceof SlabBlock slab) {
                slabBlock(slab, difTopBottomTexture ? models().slab(name, side, top, top) : models().slab(name, side, side, side), difTopBottomTexture ? models().slabTop(name + "_top", side, top, top) : models().slabTop(name + "_top", side, side, side), difTopBottomTexture ? models().cubeBottomTop(name + "_double", side, top, top) : models().cubeAll(name + "_double", side));
                itemModels().withExistingParent(name, modLoc("block/" + name));
            } else if (block instanceof StairBlock stairs) {
                stairsBlock(stairs, difTopBottomTexture ? models().stairs(name, side, top, top) : models().stairs(name, side, side, side), difTopBottomTexture ? models().stairsInner(name + "_inner", side, top, top) : models().stairsInner(name + "_inner", side, side, side), difTopBottomTexture ? models().stairsOuter(name + "_outer", side, top, top) : models().stairsOuter(name + "_outer", side, side, side));
                itemModels().withExistingParent(name, modLoc("block/" + name));
            } else if (difTopBottomTexture && baseName.contains("pillar")) {
                axisBlock((RotatedPillarBlock) block, side, top);
                itemModels().withExistingParent(name, modLoc("block/" + name));
            } else if (baseName.contains("petrified")) {
                getVariantBuilder(block).partialState().setModels(ConfiguredModel.builder().modelFile(models().cubeAll(name, side)).nextModel().modelFile(models().withExistingParent(name + "_mirrored", "minecraft:block/cube_mirrored_all").texture("all", side)).nextModel().modelFile(models().cubeAll(name, side)).rotationY(180).nextModel().modelFile(models().withExistingParent(name + "_mirrored", "minecraft:block/cube_mirrored_all").texture("all", side)).rotationY(180).build());
                itemModels().withExistingParent(name, modLoc("block/" + name));
            } else {
                simpleBlockWithItem(block, difTopBottomTexture ? models().cubeBottomTop(name, side, top, top) : models().cubeAll(name, side));
            }
        }

        //todo: set right block states for Atlantis blocks

        // Atlantis blocks
        for (Map.Entry<String, RegistryObject<Block>> entry : JSGDecorBlocks.ATLANTIS_BLOCKS.entrySet()) {
            String registryName = entry.getKey();
            Block block = entry.getValue().get();

            String textureName = registryName.replace("_block", "")
                    .replace("_slab", "")
                    .replace("_stairs", "");

            if (textureName.contains("white_lamp")) {
                textureName = textureName.replace("_lamp", "");
            }

            ResourceLocation textureRL = modLoc("block/atlantis/" + textureName);

            if (registryName.endsWith("_slab")) {
                ModelFile bottomModel = models().slab(registryName, textureRL, textureRL, textureRL);
                ModelFile topModel = models().slabTop(registryName + "_top", textureRL, textureRL, textureRL);
                ModelFile doubleModel = models().cubeAll(registryName + "_double", textureRL);

                slabBlock((SlabBlock) block, bottomModel, topModel, doubleModel);
                itemModels().withExistingParent(registryName, modLoc("block/" + registryName));
            } else if (registryName.endsWith("_stairs")) {
                ModelFile stairsModel = models().stairs(registryName, textureRL, textureRL, textureRL);
                ModelFile innerModel = models().stairsInner(registryName + "_inner", textureRL, textureRL, textureRL);
                ModelFile outerModel = models().stairsOuter(registryName + "_outer", textureRL, textureRL, textureRL);

                stairsBlock((StairBlock) block, stairsModel, innerModel, outerModel);
                itemModels().withExistingParent(registryName, modLoc("block/" + registryName));
            } else {
                var cubeAllModel = models().cubeAll(registryName, textureRL);
                simpleBlockWithItem(block, cubeAllModel);
            }
        }
    }

    public static ResourceLocation getRL(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
