package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsg.core.common.registry.tag.CoreBlockTags;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.*;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;
import static net.minecraft.core.registries.Registries.BLOCK;

public class JSGDecorBlockTagGenerator extends BlockTagsProvider {
    public JSGDecorBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider pProvider) {
        //Braziers
        var brazierTag = tag(JSGDecorBlockTags.BRAZIERS);

        for (RegistryObject<BrazierBlock> brazier : JSGDecorBlocks.BRAZIERS.values()) {
            brazierTag.add(brazier.get());
        }

        //WOOD
        var overworldLogTag = tag(JSGDecorBlockTags.OVERWORLD_NATURAL_LOGS);
        var lemonLogTag = tag(JSGDecorBlockTags.LEMON_LOGS);
        var logTag = tag(JSGDecorBlockTags.LOGS_THAT_NOT_BURN);
        var burnLogTag = tag(JSGDecorBlockTags.LOGS_THAT_BURN);
        var leavesTag = tag(JSGDecorBlockTags.LEAVES);
        var saplingTag = tag(JSGDecorBlockTags.SAPLINGS);
        var planksTag = tag(JSGDecorBlockTags.PLANKS);
        var woodSlabTag = tag(JSGDecorBlockTags.WOODEN_SLABS);
        var woodStairsTag = tag(JSGDecorBlockTags.WOODEN_STAIRS);
        var woodFenceTag = tag(JSGDecorBlockTags.WOODEN_FENCES);
        var woodFenceGateTag = tag(JSGDecorBlockTags.WOODEN_FENCE_GATES);
        var woodButtonTag = tag(JSGDecorBlockTags.WOODEN_BUTTONS);
        var woodPressurePlateTag = tag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES);
        var woodDoorTag = tag(JSGDecorBlockTags.WOODEN_DOORS);
        var woodTrapDoorTag = tag(JSGDecorBlockTags.WOODEN_TRAPDOORS);

        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            if (!material.isFlammable()) {
                addLogsToTag(material, logTag);
            }
            if (material.isFlammable()) {
                addLogsToTag(material, burnLogTag);
            }
            if (material == WoodBlock.Material.LEMON) {
                addLogsToTag(material, lemonLogTag);
                overworldLogTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "log").get());
            }

            leavesTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "leaves").get());
            saplingTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "sapling").get());
            planksTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "planks").get());
            woodSlabTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "slab").get());
            woodStairsTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "stairs").get());
            woodFenceTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence").get());
            woodFenceGateTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence_gate").get());
            woodButtonTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "button").get());
            woodPressurePlateTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "pressure_plate").get());
            woodDoorTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "door").get());
            woodTrapDoorTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "trapdoor").get());
        }

        //Overlay Blocks
        var stoneLevelPickaxeToolTag = tag(JSGDecorBlockTags.MINEABLE_STONE_PICKAXE);
        var slabTag = tag(JSGDecorBlockTags.SLABS);
        var stairsTag = tag(JSGDecorBlockTags.STAIRS);

        for (RegistryObject<Block> block : OVERLAY_BLOCKS.values()) {
            stoneLevelPickaxeToolTag.add(block.get());

            if (block.get() instanceof SlabBlock) { slabTag.add(block.get()); }
            else if (block.get() instanceof StairBlock) { stairsTag.add(block.get()); }
        }

        //Common Blocks
        for (RegistryObject<Block> block : COMMON_BLOCKS.values()) {
            stoneLevelPickaxeToolTag.add(block.get());

            if (block.get() instanceof SlabBlock) { slabTag.add(block.get()); }
            else if (block.get() instanceof StairBlock) { stairsTag.add(block.get()); }
        }

        //Stone Based Blocks
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {
                for (StoneBasedDecorationBlock.Shape shape : StoneBasedDecorationBlock.Shape.values()) {

                    String name = variant.shouldSwapOrder()
                            ? variant.getVariant() + "_" + material.getMaterial() + "_" + shape.getShape()
                            : material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();

                    Block block = STONE_BASED_DECORATION_BLOCKS.get(name).get();

                    if (block instanceof SlabBlock) { slabTag.add(block); }
                    else if (block instanceof StairBlock) { stairsTag.add(block); }

                    switch (material) {
                        case NAQUADAH -> tag(JSGDecorBlockTags.NAQUADAH_BASED_MATERIAL).add(block);
                        case NAQUADAH_ALLOY -> tag(JSGDecorBlockTags.NAQUADAH_ALLOY_BASED_MATERIAL).add(block);
                        case REFINED_NAQUADAH -> tag(JSGDecorBlockTags.REFINED_NAQUADAH_BASED_MATERIAL).add(block);
                        case TITANIUM -> tag(JSGDecorBlockTags.TITANIUM_BASED_MATERIAL).add(block);
                        case TRINIUM -> tag(JSGDecorBlockTags.TRINIUM_BASED_MATERIAL).add(block);
                    }
                }
            }
        }

        //Glass blocks
        var blueGlassTag = tag(JSGDecorBlockTags.GLASS_BLUE);
        var limeGlassTag = tag(JSGDecorBlockTags.GLASS_LIME);
        var redGlassTag = tag(JSGDecorBlockTags.GLASS_RED);
        var colorlessGlassTag = tag(JSGDecorBlockTags.GLASS_COLORLESS);
        var bluePaneTag = tag(JSGDecorBlockTags.GLASS_PANE_BLUE);
        var limePaneTag = tag(JSGDecorBlockTags.GLASS_PANE_LIME);
        var redPaneTag = tag(JSGDecorBlockTags.GLASS_PANE_RED);
        var colorlessPaneTag = tag(JSGDecorBlockTags.GLASS_PANE_COLORLESS);
        var notLeakingTag = tag(JSGDecorBlockTags.NOT_LEAKING_BLOCKS);

        for (GlassBlock.Material materials : GlassBlock.Material.values()) {
            String material = materials.getMaterial();
            DyeColor dyeColor = materials.getDyeColor();

            for (GlassBlock.Shape shape : GlassBlock.Shape.values()) {
                String name = material + "_" + shape.getShape();

                if (shape == GlassBlock.Shape.GLASS_BLOCK) {
                    notLeakingTag.add(GLASS_BLOCKS.get(name).get());
                    if (dyeColor == DyeColor.BLUE) {
                        blueGlassTag.add(GLASS_BLOCKS.get(name).get());
                    } else if (dyeColor == DyeColor.LIME) {
                        limeGlassTag.add(GLASS_BLOCKS.get(name).get());
                    } else if (dyeColor == DyeColor.RED) {
                        redGlassTag.add(GLASS_BLOCKS.get(name).get());
                    } else {
                        colorlessGlassTag.add(GLASS_BLOCKS.get(name).get());
                    }
                } else {
                    if (dyeColor == DyeColor.BLUE) {
                        bluePaneTag.add(GLASS_BLOCKS.get(name).get());
                    } else if (dyeColor == DyeColor.LIME) {
                        limePaneTag.add(GLASS_BLOCKS.get(name).get());
                    } else if (dyeColor == DyeColor.RED) {
                        redPaneTag.add(GLASS_BLOCKS.get(name).get());
                    } else {
                        colorlessPaneTag.add(GLASS_BLOCKS.get(name).get());
                    }
                }
            }
        }

        //JSG Core tags
        tag(CoreBlockTags.SUPPORT_LEMON).add(JSGDecorBlocks.WOOD_BLOCKS.get("lemon_leaves").get());

        //Minecraft tags
            //Mining
            tag(BlockTags.MINEABLE_WITH_PICKAXE).addTags(
                    JSGDecorBlockTags.BRAZIERS,
                    JSGDecorBlockTags.MINEABLE_STONE_PICKAXE,
                    JSGDecorBlockTags.NAQUADAH_BASED_MATERIAL,
                    JSGDecorBlockTags.NAQUADAH_ALLOY_BASED_MATERIAL,
                    JSGDecorBlockTags.REFINED_NAQUADAH_BASED_MATERIAL,
                    JSGDecorBlockTags.TITANIUM_BASED_MATERIAL,
                    JSGDecorBlockTags.TRINIUM_BASED_MATERIAL);

            tag(BlockTags.MINEABLE_WITH_HOE).addTag(JSGDecorBlockTags.LEAVES);

            tag(BlockTags.NEEDS_STONE_TOOL).addTag(JSGDecorBlockTags.MINEABLE_STONE_PICKAXE);

            tag(BlockTags.NEEDS_IRON_TOOL).addTags(
                    JSGDecorBlockTags.NAQUADAH_BASED_MATERIAL,
                    JSGDecorBlockTags.NAQUADAH_ALLOY_BASED_MATERIAL,
                    JSGDecorBlockTags.TITANIUM_BASED_MATERIAL);

            tag(BlockTags.NEEDS_DIAMOND_TOOL).addTags(
                    JSGDecorBlockTags.BRAZIERS,
                    JSGDecorBlockTags.TRINIUM_BASED_MATERIAL,
                    JSGDecorBlockTags.REFINED_NAQUADAH_BASED_MATERIAL);

            //Wood
            tag(BlockTags.OVERWORLD_NATURAL_LOGS).addTag(JSGDecorBlockTags.OVERWORLD_NATURAL_LOGS);
            tag(BlockTags.LOGS).addTag(JSGDecorBlockTags.LOGS_THAT_NOT_BURN);
            tag(BlockTags.LOGS_THAT_BURN).addTag(JSGDecorBlockTags.LOGS_THAT_BURN);
            tag(BlockTags.LEAVES).addTag(JSGDecorBlockTags.LEAVES);
            tag(BlockTags.SAPLINGS).addTag(JSGDecorBlockTags.SAPLINGS);
            tag(BlockTags.PLANKS).addTag(JSGDecorBlockTags.PLANKS);
            tag(BlockTags.WOODEN_SLABS).addTag(JSGDecorBlockTags.WOODEN_SLABS);
            tag(BlockTags.WOODEN_STAIRS).addTag(JSGDecorBlockTags.WOODEN_STAIRS);
            tag(BlockTags.WOODEN_FENCES).addTag(JSGDecorBlockTags.WOODEN_FENCES);
            tag(BlockTags.FENCE_GATES).addTag(JSGDecorBlockTags.WOODEN_FENCE_GATES);
            tag(BlockTags.WOODEN_BUTTONS).addTag(JSGDecorBlockTags.WOODEN_BUTTONS);
            tag(BlockTags.WOODEN_PRESSURE_PLATES).addTag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES);
            tag(BlockTags.WOODEN_DOORS).addTag(JSGDecorBlockTags.WOODEN_DOORS);
            tag(BlockTags.WOODEN_TRAPDOORS).addTag(JSGDecorBlockTags.WOODEN_TRAPDOORS);

            //OverlayBlocks + Common Blocks + StoneBasedBlocks
            tag(BlockTags.SLABS).addTag(JSGDecorBlockTags.SLABS);
            tag(BlockTags.STAIRS).addTag(JSGDecorBlockTags.STAIRS);

            //Special mechanics
            tag(BlockTags.IMPERMEABLE).addTag(JSGDecorBlockTags.NOT_LEAKING_BLOCKS);
            tag(BlockTags.GUARDED_BY_PIGLINS).add(BrazierType.HATAK.block().get());

        //forge tags
            tag(Tags.Blocks.FENCE_GATES_WOODEN).addTag(JSGDecorBlockTags.WOODEN_FENCE_GATES);
            tag(Tags.Blocks.FENCES_WOODEN).addTag(JSGDecorBlockTags.WOODEN_FENCES);

            tag(Tags.Blocks.STAINED_GLASS).addTags(
                    JSGDecorBlockTags.GLASS_RED,
                    JSGDecorBlockTags.GLASS_LIME,
                    JSGDecorBlockTags.GLASS_BLUE);

            tag(Tags.Blocks.GLASS_RED).addTag(JSGDecorBlockTags.GLASS_RED);
            tag(Tags.Blocks.GLASS_BLUE).addTag(JSGDecorBlockTags.GLASS_BLUE);
            tag(Tags.Blocks.GLASS_LIME).addTag(JSGDecorBlockTags.GLASS_LIME);
            tag(Tags.Blocks.GLASS_COLORLESS).addTag(JSGDecorBlockTags.GLASS_COLORLESS);

            tag(Tags.Blocks.STAINED_GLASS_PANES).addTags(
                    JSGDecorBlockTags.GLASS_PANE_RED,
                    JSGDecorBlockTags.GLASS_PANE_LIME,
                    JSGDecorBlockTags.GLASS_PANE_BLUE);

            tag(Tags.Blocks.GLASS_PANES_RED).addTag(JSGDecorBlockTags.GLASS_PANE_RED);
            tag(Tags.Blocks.GLASS_PANES_BLUE).addTag(JSGDecorBlockTags.GLASS_PANE_BLUE);
            tag(Tags.Blocks.GLASS_PANES_LIME).addTag(JSGDecorBlockTags.GLASS_PANE_LIME);
            tag(Tags.Blocks.GLASS_PANES_COLORLESS).addTag(JSGDecorBlockTags.GLASS_PANE_COLORLESS);
    }

    private void addLogsToTag(WoodBlock.Material material, TagAppender<Block> tagAppender) {
        String materialType = material.getMaterial();

        tagAppender.add(ResourceKey.create(BLOCK, JSGMapping.rl(JSGDecor.MOD_ID, materialType + "_log")));
        tagAppender.add(ResourceKey.create(BLOCK, JSGMapping.rl(JSGDecor.MOD_ID, "stripped_" + materialType + "_log")));
        tagAppender.add(ResourceKey.create(BLOCK, JSGMapping.rl(JSGDecor.MOD_ID, materialType + "_wood")));
        tagAppender.add(ResourceKey.create(BLOCK, JSGMapping.rl(JSGDecor.MOD_ID, "stripped_" + materialType + "_wood")));
    }
}