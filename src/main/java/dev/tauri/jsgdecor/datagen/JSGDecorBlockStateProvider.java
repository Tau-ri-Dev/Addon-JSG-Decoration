package dev.tauri.jsgdecor.datagen;

import com.google.gson.JsonObject;
import dev.tauri.jsg.core.JSGCore;
import dev.tauri.jsg.core.client.model.JSGOBJModelLoaderBuilder;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.*;
import dev.tauri.jsgdecor.common.block.GlassBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;
import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class JSGDecorBlockStateProvider extends BlockStateProvider {
    public JSGDecorBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JSGDecor.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        generateBrazierStates();
        generateBlockWithOverlayStates();
        generateCommonBlockStates();
        generateGlassBlockStates();
        generateStoneBasedDecorationStates();
        generateWoodBlockStates();
    }

    @SuppressWarnings("DataFlowIssue")
    private void generateBrazierStates() {
        var genericModel = models().getBuilder("block/brazier_generic")
                .texture("particle", JSGMapping.rl(JSGCore.MOD_ID, "block/storage_block/naquadah_alloy_block"));

        for (var brazier : BRAZIERS.values()) {
            BrazierBlock block = brazier.get();
            simpleBlock(block, genericModel);

            String name = BLOCKS.getKey(block).getPath();

            var itemModel = itemModels().withExistingParent(name, "minecraft:item/generated")
                    .texture("layer0", JSGMapping.rl(JSGCore.MOD_ID, "block/wip"));

            itemModel.customLoader((parent, helper) ->
                    new JSGOBJModelLoaderBuilder<>(parent, helper) {
                        @Override
                        public JsonObject toJson(JsonObject json) {
                            JsonObject result = super.toJson(json);
                            result.addProperty("override_transformations", true);
                            return result;
                        }
                    }.renderTypes(JSGOBJModelLoaderBuilder.DEFAULT_RENDER_TYPES)
            );
        }
    }

    private void generateBlockWithOverlayStates() {
        ResourceLocation cubeTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/cube_only_side_overlay");
        ResourceLocation slabTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/slab_only_side_overlay");
        ResourceLocation slabTopTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/slab_top_only_side_overlay");
        ResourceLocation stairsTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/stairs_only_side_overlay");
        ResourceLocation stairsInnerTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/inner_stairs_only_side_overlay");
        ResourceLocation stairsOuterTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/outer_stairs_only_side_overlay");

        for (BlockWithOverlay.Material material : BlockWithOverlay.Material.values()) {
            String baseName = material.getMaterial();

            ResourceLocation vanillaTexture = JSGMapping.rl("minecraft", "block/" + material.getBaseTexture());
            ResourceLocation overlayTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/overlays/" + material.getOverlayTexture());

            String blockName = baseName + "_block";
            String slabName = baseName + "_slab";
            String stairsName = baseName + "_stairs";

            Block block = OVERLAY_BLOCKS.get(blockName).get();
            BlockModelBuilder cubeModel = models().withExistingParent(blockName, cubeTemplate)
                    .texture("normal", vanillaTexture)
                    .texture("overlay", overlayTexture);
            simpleBlock(block, cubeModel);
            generateInventoryItem(blockName);

            SlabBlock slab = (SlabBlock) OVERLAY_BLOCKS.get(slabName).get();
            BlockModelBuilder slabModel = models().withExistingParent(slabName, slabTemplate).texture("normal", vanillaTexture).texture("overlay", overlayTexture);
            BlockModelBuilder slabTopModel = models().withExistingParent(slabName + "_top", slabTopTemplate).texture("normal", vanillaTexture).texture("overlay", overlayTexture);
            BlockModelBuilder slabDoubleModel = models().withExistingParent(slabName + "_double", cubeTemplate).texture("normal", vanillaTexture).texture("overlay", overlayTexture);

            slabBlock(slab, slabModel, slabTopModel, slabDoubleModel);
            generateInventoryItem(slabName);

            StairBlock stairs = (StairBlock) OVERLAY_BLOCKS.get(stairsName).get();
            BlockModelBuilder stairsModel = models().withExistingParent(stairsName, stairsTemplate).texture("top", vanillaTexture).texture("bottom", vanillaTexture).texture("side", vanillaTexture).texture("overlay", overlayTexture);
            BlockModelBuilder stairsInner = models().withExistingParent(stairsName + "_inner", stairsInnerTemplate).texture("top", vanillaTexture).texture("bottom", vanillaTexture).texture("side", vanillaTexture).texture("overlay", overlayTexture);
            BlockModelBuilder stairsOuter = models().withExistingParent(stairsName + "_outer", stairsOuterTemplate).texture("top", vanillaTexture).texture("bottom", vanillaTexture).texture("side", vanillaTexture).texture("overlay", overlayTexture);

            stairsBlock(stairs, stairsModel, stairsInner, stairsOuter);
            generateInventoryItem(stairsName);
        }
    }

    private void generateCommonBlockStates() {
        for (CommonBlock.Material material : CommonBlock.Material.values()) {

            CommonBlock.Variant[] generateVariants = material.hasVariants()
                    ? CommonBlock.Variant.values()
                    : new CommonBlock.Variant[]{CommonBlock.Variant.DEFAULT};

            for (CommonBlock.Variant var : generateVariants) {

                String name = (var == CommonBlock.Variant.DEFAULT)
                        ? material.getMaterial()
                        : var.getVariant() + "_" + material.getMaterial();

                String texturePath = (var == CommonBlock.Variant.DEFAULT)
                        ? material.getTextureName()
                        : var.getVariant() + "_" + material.getTextureName();

                ResourceLocation commonTex = JSGMapping.rl(JSGDecor.MOD_ID, "block/common/" + texturePath);

                String blockName = name + "_block";
                String slabName = name + "_slab";
                String stairsName = name + "_stairs";

                generateBlockSlabStairs(COMMON_BLOCKS, blockName, slabName, stairsName, commonTex, false);

                if (material.hasLampVariant()) {
                    String lampBlockName = name + "_lamp_block";
                    String lampSlabName = name + "_lamp_slab";
                    String lampStairsName = name + "_lamp_stairs";

                    generateBlockSlabStairs(COMMON_BLOCKS, lampBlockName, lampSlabName, lampStairsName, commonTex, true);
                }
            }
        }
    }

    private void generateGlassBlockStates() {
        for (GlassBlock.Material material : GlassBlock.Material.values()) {
            String baseName = material.getMaterial();
            ResourceLocation glassTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/glass/" + baseName);

            for (GlassBlock.Shape shape : GlassBlock.Shape.values()) {
                String name = baseName + "_" + shape.getShape();
                Block block = GLASS_BLOCKS.get(name).get();

                if (shape == GlassBlock.Shape.GLASS_BLOCK) {
                    BlockModelBuilder cubeModel = models().cubeAll(name, glassTexture).renderType("translucent");
                    simpleBlock(block, cubeModel);
                    generateInventoryItem(name);
                } else if (shape == GlassBlock.Shape.GLASS_PANE) {
                    ResourceLocation paneTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/glass/" + material.getTopPaneTexture());

                    paneBlockWithRenderType((IronBarsBlock) block, name, glassTexture, paneTopTexture, "translucent");
                    itemModels().withExistingParent(name, "minecraft:item/generated").texture("layer0", glassTexture).renderType("translucent");
                }
            }
        }
    }

    private void generateStoneBasedDecorationStates() {
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {
                for (StoneBasedDecorationBlock.Shape shape : StoneBasedDecorationBlock.Shape.values()) {

                    if (shape != StoneBasedDecorationBlock.Shape.BLOCK) continue;

                    String baseName = variant.shouldSwapOrder() ? variant.getVariant() + "_" + material.getMaterial() : material.getMaterial() + "_" + variant.getVariant();

                    Block block = STONE_BASED_DECORATION_BLOCKS.get(baseName + "_block").get();
                    SlabBlock slab = (SlabBlock) STONE_BASED_DECORATION_BLOCKS.get(baseName + "_slab").get();
                    StairBlock stairs = (StairBlock) STONE_BASED_DECORATION_BLOCKS.get(baseName + "_stairs").get();

                    String textureRL = "block/stone_based/" + material.getMaterial() + "_";

                    ResourceLocation textureSides = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + variant.getVariant());
                    ResourceLocation textureBottomTop = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + variant.getTopBottomTexture());


                    if (variant == StoneBasedDecorationBlock.Variant.SMOOTH) {
                        textureSides = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + "slab_top");
                        textureBottomTop = textureSides;
                    }

                    generateBlockSlabStairs(block, slab, stairs, textureSides, textureBottomTop, variant.isRotatable(), false);
                }
            }
        }
    }

    private void generateWoodBlockStates() {
        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            String woodType = material.getMaterial() + "_";

            ResourceLocation logSideTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "log");
            ResourceLocation logTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "log_top");
            ResourceLocation strippedLogSideTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/stripped_" + woodType + "log");
            ResourceLocation strippedLogTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/stripped_" + woodType + "log_top");
            ResourceLocation planksTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "planks");
            ResourceLocation leavesTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "leaves");
            ResourceLocation saplingTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "sapling");

            axisBlock((RotatedPillarBlock) WOOD_BLOCKS.get(woodType + "log").get(), logSideTexture, logTopTexture);
            axisBlock((RotatedPillarBlock) WOOD_BLOCKS.get("stripped_" + woodType + "log").get(), strippedLogSideTexture, strippedLogTopTexture);
            axisBlock((RotatedPillarBlock) WOOD_BLOCKS.get(woodType + "wood").get(), logSideTexture, logSideTexture);
            axisBlock((RotatedPillarBlock) WOOD_BLOCKS.get("stripped_" + woodType + "wood").get(), strippedLogSideTexture, strippedLogSideTexture);

            simpleBlock(WOOD_BLOCKS.get(woodType + "planks").get(), models().cubeAll(woodType + "planks", planksTexture));

            simpleBlock(WOOD_BLOCKS.get(woodType + "leaves").get(), models().withExistingParent(woodType + "leaves", "minecraft:block/leaves").texture("all", leavesTexture).renderType("cutout"));
            simpleBlock(WOOD_BLOCKS.get(woodType + "sapling").get(), models().cross(woodType + "sapling", saplingTexture).renderType("cutout"));

            slabBlock((SlabBlock) WOOD_BLOCKS.get(woodType + "slab").get(), WOOD_BLOCKS.get(woodType + "planks").getId(), planksTexture);
            stairsBlock((StairBlock) WOOD_BLOCKS.get(woodType + "stairs").get(), planksTexture);
            fenceBlock((FenceBlock) WOOD_BLOCKS.get(woodType + "fence").get(), planksTexture);
            fenceGateBlock((FenceGateBlock) WOOD_BLOCKS.get(woodType + "fence_gate").get(), planksTexture);
            buttonBlock((ButtonBlock) WOOD_BLOCKS.get(woodType + "button").get(), planksTexture);
            pressurePlateBlock((PressurePlateBlock) WOOD_BLOCKS.get(woodType + "pressure_plate").get(), planksTexture);

            doorBlockWithRenderType((DoorBlock) WOOD_BLOCKS.get(woodType + "door").get(),
                    JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "door_bottom"),
                    JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "door_top"), "cutout");
            trapdoorBlock((TrapDoorBlock) WOOD_BLOCKS.get(woodType + "trapdoor").get(),
                    JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "trapdoor"), true);
                    models().getBuilder(woodType + "trapdoor_bottom").renderType("cutout");
                    models().getBuilder(woodType + "trapdoor_top").renderType("cutout");
                    models().getBuilder(woodType + "trapdoor_open").renderType("cutout");

            for (WoodBlock.Shape shape : WoodBlock.Shape.values()) {
                String fullName = woodType + shape.getShape();

                if (shape == WoodBlock.Shape.LOG) fullName = woodType + "log";
                else if (shape == WoodBlock.Shape.STRIPPED_LOG) fullName = "stripped_" + woodType + "log";
                else if (shape == WoodBlock.Shape.WOOD) fullName = woodType + "wood";
                else if (shape == WoodBlock.Shape.STRIPPED_WOOD) fullName = "stripped_" + woodType + "wood";

                if (WOOD_BLOCKS.containsKey(fullName)) {
                    if (shape == WoodBlock.Shape.FENCE) {
                        itemModels().fenceInventory(fullName, planksTexture);
                    } else if (shape == WoodBlock.Shape.BUTTON) {
                        itemModels().buttonInventory(fullName, planksTexture);
                    } else if (shape == WoodBlock.Shape.DOOR || shape == WoodBlock.Shape.SAPLING) {
                        itemModels().withExistingParent(fullName, "minecraft:item/generated")
                                .texture("layer0", JSGMapping.rl(JSGDecor.MOD_ID, "item/wood/" + material.getMaterial() + "/" + fullName));
                    } else if (shape == WoodBlock.Shape.TRAPDOOR) {
                        itemModels().withExistingParent(fullName, JSGMapping.rl(JSGDecor.MOD_ID, "block/" + fullName + "_bottom"));
                    } else {
                        generateInventoryItem(fullName);
                    }
                }
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    private void generateBlockSlabStairs(Block block, SlabBlock slab, StairBlock stairs, ResourceLocation side, ResourceLocation top, boolean isPillar, boolean isLamp) {

        String blockName = BLOCKS.getKey(block).getPath();
        String slabName = BLOCKS.getKey(slab).getPath();
        String stairsName = BLOCKS.getKey(stairs).getPath();


        boolean diff = !side.equals(top);

        if (isPillar) {
            axisBlock((RotatedPillarBlock) block, side, top);
        } else {
            var model = diff ? models().cubeBottomTop(blockName, side, top, top) : models().cubeAll(blockName, side);
            if (isLamp) model.renderType("cutout");
            simpleBlock(block, model);
        }
        generateInventoryItem(blockName);

        var slabModel = diff ? models().slab(slabName, side, top, top) : models().slab(slabName, side, side, side);
        var slabTopModel = diff ? models().slabTop(slabName + "_top", side, top, top) : models().slabTop(slabName + "_top", side, side, side);
        var slabDoubleModel = diff ? models().cubeBottomTop(slabName + "_double", side, top, top) : models().cubeAll(slabName + "_double", side);

        if (isLamp) {
            slabModel.renderType("cutout");
            slabTopModel.renderType("cutout");
            slabDoubleModel.renderType("cutout");
        }
        slabBlock(slab, slabModel, slabTopModel, slabDoubleModel);
        generateInventoryItem(slabName);

        var stairsModel = diff ? models().stairs(stairsName, side, top, top) : models().stairs(stairsName, side, side, side);
        var stairsInner = diff ? models().stairsInner(stairsName + "_inner", side, top, top) : models().stairsInner(stairsName + "_inner", side, side, side);
        var stairsOuter = diff ? models().stairsOuter(stairsName + "_outer", side, top, top) : models().stairsOuter(stairsName + "_outer", side, side, side);

        if (isLamp) {
            stairsModel.renderType("cutout");
            stairsInner.renderType("cutout");
            stairsOuter.renderType("cutout");
        }
        stairsBlock(stairs, stairsModel, stairsInner, stairsOuter);
        generateInventoryItem(stairsName);
    }

    @SuppressWarnings("SameParameterValue")
    private void generateBlockSlabStairs(Map<String, RegistryObject<Block>> blockMap, String blockName, String slabName, String stairsName, ResourceLocation texture, boolean isLamp) {
        Block block = blockMap.get(blockName).get();
        SlabBlock slab = (SlabBlock) blockMap.get(slabName).get();
        StairBlock stairs = (StairBlock) blockMap.get(stairsName).get();

        generateBlockSlabStairs(block, slab, stairs, texture, texture, false, isLamp);
    }

    private void generateInventoryItem(String name) {
        itemModels().withExistingParent(name, JSGMapping.rl(JSGDecor.MOD_ID, "block/" + name));
    }
}