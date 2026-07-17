package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.block.WoodBlock;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDecorItemTagGenerator extends ItemTagsProvider {
    public JSGDecorItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {

        //Boats
        for (BoatTypes type : BoatTypes.values()) {
            tag(JSGDecorItemTags.BOATS).add(type.getDrop(false));
            tag(JSGDecorItemTags.CHEST_BOATS).add(type.getDrop(true));
        }

        //WOOD
        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            tag(JSGDecorItemTags.LOGS)
                    .add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "log").get().asItem())
                    .add(JSGDecorBlocks.WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_" + "log").get().asItem())
                    .add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "wood").get().asItem())
                    .add(JSGDecorBlocks.WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_" + "wood").get().asItem());

            if (material.isFlammable()) {
                tag(JSGDecorItemTags.LOGS_THAT_BURN)
                        .add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "log").get().asItem())
                        .add(JSGDecorBlocks.WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_" + "log").get().asItem())
                        .add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "wood").get().asItem())
                        .add(JSGDecorBlocks.WOOD_BLOCKS.get("stripped_" + material.getMaterial() + "_" + "wood").get().asItem());
            }

            tag(JSGDecorItemTags.LEAVES).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "leaves").get().asItem());
            tag(JSGDecorItemTags.SAPLINGS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "sapling").get().asItem());
            tag(JSGDecorItemTags.PLANKS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "planks").get().asItem());
            tag(JSGDecorItemTags.WOODEN_SLABS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "slab").get().asItem());
            tag(JSGDecorItemTags.WOODEN_STAIRS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "stairs").get().asItem());
            tag(JSGDecorItemTags.WOODEN_FENCES).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence").get().asItem());
            tag(JSGDecorItemTags.WOODEN_FENCE_GATES).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "fence_gate").get().asItem());
            tag(JSGDecorItemTags.WOODEN_BUTTONS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "button").get().asItem());
            tag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "pressure_plate").get().asItem());
            tag(JSGDecorItemTags.WOODEN_DOORS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "door").get().asItem());
            tag(JSGDecorItemTags.WOODEN_TRAPDOORS).add(JSGDecorBlocks.WOOD_BLOCKS.get(material.getMaterial() + "_" + "trapdoor").get().asItem());
        }




        var buttonsTag = tag(JSGDecorItemTags.BUTTONS).addTag(JSGDecorItemTags.WOODEN_BUTTONS);
        var doorsTag = tag(JSGDecorItemTags.DOORS).addTag(JSGDecorItemTags.WOODEN_DOORS);
        var trapdoorsTag = tag(JSGDecorItemTags.TRAPDOORS).addTag(JSGDecorItemTags.WOODEN_TRAPDOORS);
        var fencesTag = tag(JSGDecorItemTags.FENCES).addTag(JSGDecorItemTags.WOODEN_FENCES);
        var fenceGatesTag = tag(JSGDecorItemTags.FENCE_GATES).addTag(JSGDecorItemTags.WOODEN_FENCE_GATES);
        var slabsTag = tag(JSGDecorItemTags.SLABS).addTag(JSGDecorItemTags.WOODEN_SLABS);
        var stairsTag = tag(JSGDecorItemTags.STAIRS).addTag(JSGDecorItemTags.WOODEN_STAIRS);


    //minecraft tags

        //Braziers
        tag(ItemTags.PIGLIN_LOVED).add(BrazierType.HATAK.block().get().asItem());

        //Boats
        tag(ItemTags.BOATS).addTag(JSGDecorItemTags.BOATS);
        tag(ItemTags.CHEST_BOATS).addTag(JSGDecorItemTags.CHEST_BOATS);

        //Wood
        tag(ItemTags.LOGS).addTag(JSGDecorItemTags.LOGS);
        tag(ItemTags.LOGS_THAT_BURN).addTag(JSGDecorItemTags.LOGS_THAT_BURN);
        tag(ItemTags.LEAVES).addTag(JSGDecorItemTags.LEAVES);
        tag(ItemTags.SAPLINGS).addTag(JSGDecorItemTags.SAPLINGS);
        tag(ItemTags.PLANKS).addTag(JSGDecorItemTags.PLANKS);
        tag(ItemTags.WOODEN_SLABS).addTag(JSGDecorItemTags.WOODEN_SLABS);
        tag(ItemTags.WOODEN_STAIRS).addTag(JSGDecorItemTags.WOODEN_STAIRS);
        tag(ItemTags.WOODEN_FENCES).addTag(JSGDecorItemTags.WOODEN_FENCES);
        tag(ItemTags.WOODEN_BUTTONS).addTag(JSGDecorItemTags.WOODEN_BUTTONS);
        tag(ItemTags.WOODEN_PRESSURE_PLATES).addTag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES);
        tag(ItemTags.WOODEN_DOORS).addTag(JSGDecorItemTags.WOODEN_DOORS);
        tag(ItemTags.WOODEN_TRAPDOORS).addTag(JSGDecorItemTags.WOODEN_TRAPDOORS);



    //forge tags

        tag(Tags.Items.FENCE_GATES_WOODEN).addTag(JSGDecorItemTags.WOODEN_FENCE_GATES); //mozna i do forge tagu fence gate










        //JSGDecor wood and wood products tags



        //JSGDecor glass tags
        tag(JSGDecorItemTags.GLASS)
                .addTag(JSGDecorItemTags.STAINED_GLASS);

        tag(JSGDecorItemTags.STAINED_GLASS)
                .addTag(JSGDecorItemTags.RED_GLASS)
                .addTag(JSGDecorItemTags.GREEN_GLASS)
                .addTag(JSGDecorItemTags.BLUE_GLASS);

        tag(JSGDecorItemTags.RED_GLASS)
                .add(JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get().asItem())
                .add(JSGDecorBlocks.LEFT_RED_GLASS_BLOCK.get().asItem());

        tag(JSGDecorItemTags.BLUE_GLASS)
                .add(JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get().asItem())
                .add(JSGDecorBlocks.LEFT_BLUE_GLASS_BLOCK.get().asItem());

        tag(JSGDecorItemTags.GREEN_GLASS)
                .add(JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get().asItem())
                .add(JSGDecorBlocks.LEFT_GREEN_GLASS_BLOCK.get().asItem())
                .add(JSGDecorBlocks.GREEN_GLASS_BLOCK.get().asItem());

        // Core Decoration
        for (CoreDecorationBlocks.Material material : CoreDecorationBlocks.Material.values()) {
            for (CoreDecorationBlocks.Variant variant : CoreDecorationBlocks.Variant.values()) {
                for (CoreDecorationBlocks.Shape shape : CoreDecorationBlocks.Shape.values()) {

                    String name = material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();
                    Item item = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(name).get().asItem();

                    if (shape == CoreDecorationBlocks.Shape.SLAB) tag(JSGDecorItemTags.SLABS).add(item);
                    if (shape == CoreDecorationBlocks.Shape.STAIRS) tag(JSGDecorItemTags.STAIRS).add(item);
                }
            }
        }

        //minecraft wood and wood products tags


        tag(ItemTags.BUTTONS)
                .addTag(JSGDecorItemTags.BUTTONS);

        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL)
                .addTag(JSGDecorItemTags.LOGS)
                .addTag(JSGDecorItemTags.LEAVES);

        tag(ItemTags.DOORS)
                .addTag(JSGDecorItemTags.DOORS);

        tag(ItemTags.FENCE_GATES)
                .addTag(JSGDecorItemTags.FENCE_GATES);

        tag(ItemTags.FENCES)
                .addTag(JSGDecorItemTags.FENCES);

        tag(ItemTags.SLABS)
                .addTag(JSGDecorItemTags.SLABS);

        tag(ItemTags.STAIRS)
                .addTag(JSGDecorItemTags.STAIRS);

        tag(ItemTags.TRAPDOORS)
                .addTag(JSGDecorItemTags.TRAPDOORS);

        //Forge Tags
        tag(Tags.Items.FENCES)
                .addTag(JSGDecorItemTags.FENCES);

        tag(Tags.Items.FENCES_WOODEN)
                .addTag(JSGDecorItemTags.WOODEN_FENCES);

        tag(Tags.Items.FENCE_GATES)
                .addTag(JSGDecorItemTags.FENCE_GATES);

        tag(Tags.Items.FENCE_GATES_WOODEN)
                .addTag(JSGDecorItemTags.WOODEN_FENCE_GATES);

        tag(Tags.Items.GLASS)
                .addTag(JSGDecorItemTags.GLASS);

        tag(Tags.Items.STAINED_GLASS)
                .addTag(JSGDecorItemTags.STAINED_GLASS);

        tag(Tags.Items.GLASS_RED)
                .addTag(JSGDecorItemTags.RED_GLASS);

        tag(Tags.Items.GLASS_BLUE)
                .addTag(JSGDecorItemTags.BLUE_GLASS);

        tag(Tags.Items.GLASS_GREEN)
                .addTag(JSGDecorItemTags.GREEN_GLASS);
    }
}