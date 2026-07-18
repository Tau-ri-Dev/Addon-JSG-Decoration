package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.*;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;
import static net.minecraft.core.registries.Registries.ITEM;

public class JSGDecorItemTagGenerator extends ItemTagsProvider {
    public JSGDecorItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider pProvider) {
        //Boats
        var boatTag = tag(JSGDecorItemTags.BOATS);
        var chestBoatTag = tag(JSGDecorItemTags.CHEST_BOATS);

        for (BoatTypes type : BoatTypes.values()) {
            boatTag.add(type.getDrop(false));
            chestBoatTag.add(type.getDrop(true));
        }

        //WOOD
        var lemonLogTag = tag(JSGDecorItemTags.LEMON_LOGS);
        var logTag = tag(JSGDecorItemTags.LOGS_THAT_NOT_BURN);
        var burnLogTag = tag(JSGDecorItemTags.LOGS_THAT_BURN);
        var leavesTag = tag(JSGDecorItemTags.LEAVES);
        var saplingTag = tag(JSGDecorItemTags.SAPLINGS);
        var planksTag = tag(JSGDecorItemTags.PLANKS);
        var woodSlabTag = tag(JSGDecorItemTags.WOODEN_SLABS);
        var woodStairsTag = tag(JSGDecorItemTags.WOODEN_STAIRS);
        var woodFenceTag = tag(JSGDecorItemTags.WOODEN_FENCES);
        var woodFenceGateTag = tag(JSGDecorItemTags.WOODEN_FENCE_GATES);
        var woodButtonTag = tag(JSGDecorItemTags.WOODEN_BUTTONS);
        var woodPressurePlateTag = tag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES);
        var woodDoorTag = tag(JSGDecorItemTags.WOODEN_DOORS);
        var woodTrapDoorTag = tag(JSGDecorItemTags.WOODEN_TRAPDOORS);

        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            if (!material.isFlammable()) { addLogsToTag(material, logTag); }
            if (material.isFlammable()) { addLogsToTag(material, burnLogTag); }
            if (material == WoodBlock.Material.LEMON) { addLogsToTag(material, lemonLogTag); }

            leavesTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "leaves").get().asItem());
            saplingTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "sapling").get().asItem());
            planksTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "planks").get().asItem());
            woodSlabTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "slab").get().asItem());
            woodStairsTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "stairs").get().asItem());
            woodFenceTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence").get().asItem());
            woodFenceGateTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence_gate").get().asItem());
            woodButtonTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "button").get().asItem());
            woodPressurePlateTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "pressure_plate").get().asItem());
            woodDoorTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "door").get().asItem());
            woodTrapDoorTag.add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "trapdoor").get().asItem());
        }

        //Blocks with Overlays
        var slabTag = tag(JSGDecorItemTags.SLABS);
        var stairsTag = tag(JSGDecorItemTags.STAIRS);

            for (var block : OVERLAY_BLOCKS.values()) {
                if (block.get() instanceof SlabBlock) slabTag.add(block.get().asItem());
                if (block.get() instanceof StairBlock) stairsTag.add(block.get().asItem());
            }

        //Stone Based Blocks
        for (var block : STONE_BASED_DECORATION_BLOCKS.values()) {
            if (block.get() instanceof SlabBlock)  slabTag.add(block.get().asItem());
            if (block.get() instanceof StairBlock) stairsTag.add(block.get().asItem());
        }

        //Common Blocks
        for (var block : COMMON_BLOCKS.values()) {
            if (block.get() instanceof SlabBlock)  slabTag.add(block.get().asItem());
            if (block.get() instanceof StairBlock) stairsTag.add(block.get().asItem());
        }

        //Glass Blocks
        var blueGlassTag = tag(JSGDecorItemTags.GLASS_BLUE);
        var limeGlassTag = tag(JSGDecorItemTags.GLASS_LIME);
        var redGlassTag = tag(JSGDecorItemTags.GLASS_RED);
        var colorlessGlassTag = tag(JSGDecorItemTags.GLASS_COLORLESS);
        var bluePaneTag = tag(JSGDecorItemTags.GLASS_PANE_BLUE);
        var limePaneTag = tag(JSGDecorItemTags.GLASS_PANE_LIME);
        var redPaneTag = tag(JSGDecorItemTags.GLASS_PANE_RED);
        var colorlessPaneTag = tag(JSGDecorItemTags.GLASS_PANE_COLORLESS);

        for (GlassBlock.Material materials : GlassBlock.Material.values()) {
            String material = materials.getMaterial();
            DyeColor dyeColor = materials.getDyeColor();

            for (GlassBlock.Shape shape : GlassBlock.Shape.values()) {
                String name = material + "_" + shape.getShape();

                if (shape == GlassBlock.Shape.GLASS_BLOCK) {
                    if (dyeColor == DyeColor.BLUE) {
                        blueGlassTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else if (dyeColor == DyeColor.LIME) {
                        limeGlassTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else if (dyeColor == DyeColor.RED) {
                        redGlassTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else {
                        colorlessGlassTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    }
                } else {
                    if (dyeColor == DyeColor.BLUE) {
                        bluePaneTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else if (dyeColor == DyeColor.LIME) {
                        limePaneTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else if (dyeColor == DyeColor.RED) {
                        redPaneTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    } else {
                        colorlessPaneTag.add(GLASS_BLOCKS.get(name).get().asItem());
                    }
                }
            }
        }

    //minecraft tags
        //Braziers
        tag(ItemTags.PIGLIN_LOVED).add(BrazierType.HATAK.block().get().asItem());

        //Boats
        tag(ItemTags.BOATS).addTag(JSGDecorItemTags.BOATS);
        tag(ItemTags.CHEST_BOATS).addTag(JSGDecorItemTags.CHEST_BOATS);

        //Wood
        tag(ItemTags.LOGS).addTag(JSGDecorItemTags.LOGS_THAT_NOT_BURN);
        tag(ItemTags.LOGS_THAT_BURN).addTag(JSGDecorItemTags.LOGS_THAT_BURN);
        tag(ItemTags.LEAVES).addTag(JSGDecorItemTags.LEAVES);
        tag(ItemTags.SAPLINGS).addTag(JSGDecorItemTags.SAPLINGS);
        tag(ItemTags.PLANKS).addTag(JSGDecorItemTags.PLANKS);
        tag(ItemTags.WOODEN_SLABS).addTag(JSGDecorItemTags.WOODEN_SLABS);
        tag(ItemTags.WOODEN_STAIRS).addTag(JSGDecorItemTags.WOODEN_STAIRS);
        tag(ItemTags.WOODEN_FENCES).addTag(JSGDecorItemTags.WOODEN_FENCES);
        tag(ItemTags.FENCE_GATES).addTag(JSGDecorItemTags.WOODEN_FENCE_GATES);
        tag(ItemTags.WOODEN_BUTTONS).addTag(JSGDecorItemTags.WOODEN_BUTTONS);
        tag(ItemTags.WOODEN_PRESSURE_PLATES).addTag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES);
        tag(ItemTags.WOODEN_DOORS).addTag(JSGDecorItemTags.WOODEN_DOORS);
        tag(ItemTags.WOODEN_TRAPDOORS).addTag(JSGDecorItemTags.WOODEN_TRAPDOORS);

        //OverlayBlocks + Common Blocks + StoneBasedBlocks
        tag(ItemTags.SLABS).addTag(JSGDecorItemTags.SLABS);
        tag(ItemTags.STAIRS).addTag(JSGDecorItemTags.STAIRS);

    //forge tags
        tag(Tags.Items.FENCE_GATES_WOODEN).addTag(JSGDecorItemTags.WOODEN_FENCE_GATES);
        tag(Tags.Items.FENCES_WOODEN).addTag(JSGDecorItemTags.WOODEN_FENCES);

        tag(Tags.Items.STAINED_GLASS).addTags(
                JSGDecorItemTags.GLASS_RED,
                JSGDecorItemTags.GLASS_LIME,
                JSGDecorItemTags.GLASS_BLUE);

        tag(Tags.Items.GLASS_RED).addTag(JSGDecorItemTags.GLASS_RED);
        tag(Tags.Items.GLASS_BLUE).addTag(JSGDecorItemTags.GLASS_BLUE);
        tag(Tags.Items.GLASS_LIME).addTag(JSGDecorItemTags.GLASS_LIME);
        tag(Tags.Items.GLASS_COLORLESS).addTag(JSGDecorItemTags.GLASS_COLORLESS);

        tag(Tags.Items.STAINED_GLASS_PANES).addTags(
                JSGDecorItemTags.GLASS_PANE_RED,
                JSGDecorItemTags.GLASS_PANE_LIME,
                JSGDecorItemTags.GLASS_PANE_BLUE);

        tag(Tags.Items.GLASS_PANES_RED).addTag(JSGDecorItemTags.GLASS_PANE_RED);
        tag(Tags.Items.GLASS_PANES_BLUE).addTag(JSGDecorItemTags.GLASS_PANE_BLUE);
        tag(Tags.Items.GLASS_PANES_LIME).addTag(JSGDecorItemTags.GLASS_PANE_LIME);
        tag(Tags.Items.GLASS_PANES_COLORLESS).addTag(JSGDecorItemTags.GLASS_PANE_COLORLESS);
    }

    private void addLogsToTag(WoodBlock.Material material, TagAppender<Item> tagAppender) {
        String materialType = material.getMaterial();

        tagAppender.add(ResourceKey.create(ITEM, JSGMapping.rl(JSGDecor.MOD_ID, materialType + "_log")));
        tagAppender.add(ResourceKey.create(ITEM, JSGMapping.rl(JSGDecor.MOD_ID, "stripped_" + materialType + "_log")));
        tagAppender.add(ResourceKey.create(ITEM, JSGMapping.rl(JSGDecor.MOD_ID, materialType + "_wood")));
        tagAppender.add(ResourceKey.create(ITEM, JSGMapping.rl(JSGDecor.MOD_ID, "stripped_" + materialType + "_wood")
        ));
    }
}