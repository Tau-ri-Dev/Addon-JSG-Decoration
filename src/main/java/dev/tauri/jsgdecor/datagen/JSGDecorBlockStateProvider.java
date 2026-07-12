package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.CoreDecorationBlocks;
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

import java.util.List;
import java.util.Map;

public class JSGDecorBlockStateProvider extends BlockStateProvider {
    public JSGDecorBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JSGDecor.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Core decor blocks
        for (CoreDecorationBlocks.Material material : CoreDecorationBlocks.Material.values()) {
            for (CoreDecorationBlocks.Variant variant : CoreDecorationBlocks.Variant.values()) {
                for (CoreDecorationBlocks.Shape shape : CoreDecorationBlocks.Shape.values()) {

                    boolean pillars = List.of(
                            CoreDecorationBlocks.Variant.PILLAR,
                            CoreDecorationBlocks.Variant.TILLED_PILLAR,
                            CoreDecorationBlocks.Variant.CARVED_PILLAR
                    ).contains(variant);

                    String name = material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();
                    if (!JSGDecorBlocks.CORE_DECORATION_BLOCKS.containsKey(name)) continue;
                    Block block = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(name).get();

                    String texVar = switch (variant) {
                        case SCULPTED_CREEPER, SCULPTED_WITHER -> "burnished";
                        case POLISHED_TILES -> "small_tiles";
                        default -> variant.getVariant();
                    };

                    ResourceLocation side = modLoc("block/core_blocks_variants/" + material.getMaterial() + "_" + variant.getVariant());
                    ResourceLocation top = modLoc("block/core_blocks_variants/" + material.getMaterial() + "_" + texVar);

                    if (variant == CoreDecorationBlocks.Variant.SCULPTED_GUARDIAN || pillars) {
                        top = modLoc("block/core_blocks_variants/" + material.getMaterial() + "_" + variant.getVariant() + "_top");
                    }
                    if (variant == CoreDecorationBlocks.Variant.SMOOTH) {
                        side = modLoc("block/core_blocks_variants/" + material.getMaterial() + "_slab_side");
                        top = modLoc("block/core_blocks_variants/" + material.getMaterial() + "_slab_top");
                        if (shape == CoreDecorationBlocks.Shape.BLOCK) side = top;
                    }

                    boolean differentTopBottomTexture = !side.equals(top);

                    if (block instanceof SlabBlock slab) {
                        slabBlock(slab, differentTopBottomTexture ? models().slab(name, side, top, top) : models().slab(name, side, side, side), differentTopBottomTexture ? models().slabTop(name + "_top", side, top, top) : models().slabTop(name + "_top", side, side, side), differentTopBottomTexture ? models().cubeBottomTop(name + "_double", side, top, top) : models().cubeAll(name + "_double", side));
                    } else if (block instanceof StairBlock stairs) {
                        stairsBlock(stairs, differentTopBottomTexture ? models().stairs(name, side, top, top) : models().stairs(name, side, side, side), differentTopBottomTexture ? models().stairsInner(name + "_inner", side, top, top) : models().stairsInner(name + "_inner", side, side, side), differentTopBottomTexture ? models().stairsOuter(name + "_outer", side, top, top) : models().stairsOuter(name + "_outer", side, side, side));
                    } else if (pillars) {
                        axisBlock((RotatedPillarBlock) block, side, top);
                    } else if (variant == CoreDecorationBlocks.Variant.PETRIFIED) {
                        getVariantBuilder(block).partialState().setModels(ConfiguredModel.builder().modelFile(models().cubeAll(name, side)).nextModel().modelFile(models().withExistingParent(name + "_mirrored", "minecraft:block/cube_mirrored_all").texture("all", side)).nextModel().modelFile(models().cubeAll(name, side)).rotationY(180).nextModel().modelFile(models().withExistingParent(name + "_mirrored", "minecraft:block/cube_mirrored_all").texture("all", side)).rotationY(180).build());
                    } else {
                        simpleBlock(block, differentTopBottomTexture ? models().cubeBottomTop(name, side, top, top) : models().cubeAll(name, side));
                    }
                    itemModels().withExistingParent(name, modLoc("block/" + name));
                }
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
