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
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;

public class JSGDecorBlockStateProvider extends BlockStateProvider {
    protected static final IForgeRegistry<Block> BLOCKS_REGISTRY = ForgeRegistries.BLOCKS;

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

            String name = BLOCKS_REGISTRY.getKey(block).getPath();

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

    @SuppressWarnings("DataFlowIssue")
    private void generateBlockWithOverlayStates() {
        var cubeTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/cube_only_side_overlay");
        var slabTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/slab_only_side_overlay");
        var slabTopTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/slab_top_only_side_overlay");
        var stairsTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/stairs_only_side_overlay");
        var stairsInnerTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/inner_stairs_only_side_overlay");
        var stairsOuterTemplate = JSGMapping.rl(JSGDecor.MOD_ID, "block/templates/outer_stairs_only_side_overlay");

        for (BlockWithOverlay.Material material : BlockWithOverlay.Material.values()) {
            var baseName = material.getMaterial();

            var bottomTexture = JSGMapping.rl("minecraft", "block/" + BLOCKS_REGISTRY.getKey(material.getBaseBlock()).getPath());
            var overlayTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/overlays/" + material.getOverlayTexture());

            var blockName = baseName + "_block";
            var slabName = baseName + "_slab";
            var stairsName = baseName + "_stairs";

            var block = OVERLAY_BLOCKS.get(blockName).get();
            var cubeModel = models().withExistingParent(blockName, cubeTemplate)
                    .texture("normal", bottomTexture)
                    .texture("overlay", overlayTexture);
            simpleBlock(block, cubeModel);
            generateInventoryItem(blockName);

            var slab = (SlabBlock) OVERLAY_BLOCKS.get(slabName).get();
            var slabModel = models().withExistingParent(slabName, slabTemplate).texture("normal", bottomTexture).texture("overlay", overlayTexture);
            var slabTopModel = models().withExistingParent(slabName + "_top", slabTopTemplate).texture("normal", bottomTexture).texture("overlay", overlayTexture);
            var slabDoubleModel = models().withExistingParent(slabName + "_double", cubeTemplate).texture("normal", bottomTexture).texture("overlay", overlayTexture);

            slabBlock(slab, slabModel, slabTopModel, slabDoubleModel);
            generateInventoryItem(slabName);

            var stairs = (StairBlock) OVERLAY_BLOCKS.get(stairsName).get();
            var stairsModel = models().withExistingParent(stairsName, stairsTemplate).texture("top", bottomTexture).texture("bottom", bottomTexture).texture("side", bottomTexture).texture("overlay", overlayTexture);
            var stairsInner = models().withExistingParent(stairsName + "_inner", stairsInnerTemplate).texture("top", bottomTexture).texture("bottom", bottomTexture).texture("side", bottomTexture).texture("overlay", overlayTexture);
            var stairsOuter = models().withExistingParent(stairsName + "_outer", stairsOuterTemplate).texture("top", bottomTexture).texture("bottom", bottomTexture).texture("side", bottomTexture).texture("overlay", overlayTexture);

            stairsBlock(stairs, stairsModel, stairsInner, stairsOuter);
            generateInventoryItem(stairsName);
        }
    }

    private void generateCommonBlockStates() {
        for (CommonBlock.Material material : CommonBlock.Material.values()) {
            for (CommonBlock.Variant var : material.hasVariants() ? CommonBlock.Variant.values() : new CommonBlock.Variant[]{CommonBlock.Variant.DEFAULT}) {
                var name = (var == CommonBlock.Variant.DEFAULT)
                        ? material.getMaterial()
                        : var.getVariant() + "_" + material.getMaterial();

                var texturePath = (var == CommonBlock.Variant.DEFAULT)
                        ? material.getTextureName()
                        : var.getVariant() + "_" + material.getTextureName();

                var commonTex = JSGMapping.rl(JSGDecor.MOD_ID, "block/common/" + texturePath);

                var blockName = name + "_block";
                var slabName = name + "_slab";
                var stairsName = name + "_stairs";

                generateBlockSlabStairs(COMMON_BLOCKS.get(blockName).get(), (SlabBlock) COMMON_BLOCKS.get(slabName).get(), (StairBlock) COMMON_BLOCKS.get(stairsName).get(), commonTex, commonTex, false, false);

                if (material.hasLampVariant()) {
                    var lampBlockName = name + "_lamp_block";
                    var lampSlabName = name + "_lamp_slab";
                    var lampStairsName = name + "_lamp_stairs";

                    generateBlockSlabStairs(COMMON_BLOCKS.get(lampBlockName).get(), (SlabBlock) COMMON_BLOCKS.get(lampSlabName).get(), (StairBlock) COMMON_BLOCKS.get(lampStairsName).get(), commonTex, commonTex, false, true);
                }
            }
        }
    }

    private void generateGlassBlockStates() {
        for (GlassBlock.Material material : GlassBlock.Material.values()) {
            var baseName = material.getMaterial();
            var glassTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/glass/" + baseName);

            for (GlassBlock.Shape shape : GlassBlock.Shape.values()) {
                var name = baseName + "_" + shape.getShape();
                var block = GLASS_BLOCKS.get(name).get();

                if (shape == GlassBlock.Shape.GLASS_BLOCK) {
                    var cubeModel = models().cubeAll(name, glassTexture).renderType("translucent");
                    simpleBlock(block, cubeModel);
                    generateInventoryItem(name);
                } else if (shape == GlassBlock.Shape.GLASS_PANE) {
                    var paneTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/glass/" + material.getTopPaneTexture());

                    paneBlockWithRenderType((IronBarsBlock) block, name, glassTexture, paneTopTexture, "translucent");
                    itemModels().withExistingParent(name, "minecraft:item/generated").texture("layer0", glassTexture).renderType("translucent");
                }
            }
        }
    }

    private void generateStoneBasedDecorationStates() {
        StoneBasedDecorationBlock.forEach((material, variant, shape) -> {
            if (shape != StoneBasedDecorationBlock.Shape.BLOCK) return;

            var baseName = variant.shouldSwapNameOrder() ? variant.getVariant() + "_" + material.getMaterial() : material.getMaterial() + "_" + variant.getVariant();

            var block = STONE_BASED_DECORATION_BLOCKS.get(baseName + "_block").get();
            var slab = (SlabBlock) STONE_BASED_DECORATION_BLOCKS.get(baseName + "_slab").get();
            var stairs = (StairBlock) STONE_BASED_DECORATION_BLOCKS.get(baseName + "_stairs").get();

            var textureRL = "block/stone_based/" + material.getMaterial() + "_";

            var textureSides = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + variant.getVariant());
            var textureBottomTop = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + variant.getTopBottomTexture());


            if (variant == StoneBasedDecorationBlock.Variant.SMOOTH) {
                textureSides = JSGMapping.rl(JSGDecor.MOD_ID, textureRL + "slab_top");
                textureBottomTop = textureSides;
            }

            generateBlockSlabStairs(block, slab, stairs, textureSides, textureBottomTop, variant.isRotatable(), false);
        });
    }

    private void generateWoodBlockStates() {
        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            String woodType = material.getMaterial() + "_";

            var logSideTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "log");
            var logTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "log_top");
            var strippedLogSideTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/stripped_" + woodType + "log");
            var strippedLogTopTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/stripped_" + woodType + "log_top");
            var planksTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "planks");
            var leavesTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "leaves");
            var saplingTexture = JSGMapping.rl(JSGDecor.MOD_ID, "block/wood/" + material.getMaterial() + "/" + woodType + "sapling");

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

    private void generateBlockSlabStairs(Block block, SlabBlock slabBlock, StairBlock stairsBlock, ResourceLocation sideTextureLoc, ResourceLocation topTextureLoc, boolean isPillar, boolean isCutout) {
        generateSimpleBlock(block, sideTextureLoc, topTextureLoc, isPillar, isCutout);
        generateSlab(slabBlock, sideTextureLoc, topTextureLoc, isCutout);
        generateStairs(stairsBlock, sideTextureLoc, topTextureLoc, isCutout);
    }

    private void generateSlab(SlabBlock slabBlock, ResourceLocation sideTextureLoc, ResourceLocation topTextureLoc, boolean isCutout) {
        var slabName = Objects.requireNonNull(BLOCKS_REGISTRY.getKey(slabBlock)).getPath();

        boolean isTopTexDifferent = !sideTextureLoc.equals(topTextureLoc);

        var slabModel = models().slab(slabName, sideTextureLoc, topTextureLoc, topTextureLoc);
        var slabTopModel = models().slabTop(slabName + "_top", sideTextureLoc, topTextureLoc, topTextureLoc);
        var slabDoubleModel = isTopTexDifferent ? models().cubeBottomTop(slabName + "_double", sideTextureLoc, topTextureLoc, topTextureLoc) : models().cubeAll(slabName + "_double", sideTextureLoc);

        if (isCutout) {
            slabModel.renderType("cutout");
            slabTopModel.renderType("cutout");
            slabDoubleModel.renderType("cutout");
        }
        slabBlock(slabBlock, slabModel, slabTopModel, slabDoubleModel);
        generateInventoryItem(slabName);
    }

    private void generateStairs(StairBlock stairsBlock, ResourceLocation sideTextureLoc, ResourceLocation topTextureLoc, boolean isCutout) {
        var stairsName = Objects.requireNonNull(BLOCKS_REGISTRY.getKey(stairsBlock)).getPath();

        var stairsModel = models().stairs(stairsName, sideTextureLoc, topTextureLoc, topTextureLoc);
        var stairsInnerModel = models().stairsInner(stairsName + "_inner", sideTextureLoc, topTextureLoc, topTextureLoc);
        var stairsOuterModel = models().stairsOuter(stairsName + "_outer", sideTextureLoc, topTextureLoc, topTextureLoc);

        if (isCutout) {
            stairsModel.renderType("cutout");
            stairsInnerModel.renderType("cutout");
            stairsOuterModel.renderType("cutout");
        }
        stairsBlock(stairsBlock, stairsModel, stairsInnerModel, stairsOuterModel);
        generateInventoryItem(stairsName);
    }

    private void generateSimpleBlock(Block block, ResourceLocation sideTextureLoc, ResourceLocation topTextureLoc, boolean isPillar, boolean isCutout) {
        var blockName = Objects.requireNonNull(BLOCKS_REGISTRY.getKey(block)).getPath();

        boolean isTopTexDifferent = !sideTextureLoc.equals(topTextureLoc);

        if (isPillar) {
            axisBlock((RotatedPillarBlock) block, sideTextureLoc, topTextureLoc);
        } else {
            var model = isTopTexDifferent ? models().cubeBottomTop(blockName, sideTextureLoc, topTextureLoc, topTextureLoc) : models().cubeAll(blockName, sideTextureLoc);
            if (isCutout) model.renderType("cutout");
            simpleBlock(block, model);
        }
        generateInventoryItem(blockName);
    }

    private void generateInventoryItem(String name) {
        itemModels().withExistingParent(name, JSGMapping.rl(JSGDecor.MOD_ID, "block/" + name));
    }
}