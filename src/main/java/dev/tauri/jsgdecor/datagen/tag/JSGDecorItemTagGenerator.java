package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
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
        //JSGDecor wood and wood products tags
        /*tag(JSGDecorItemTags.BOATS)
                .add(BoatTypes.LEMON.getDrop(false))
                .addTag(JSGDecorItemTags.CHEST_BOATS);

        tag(JSGDecorItemTags.BUTTONS)
                .addTag(JSGDecorItemTags.WOODEN_BUTTONS);

        tag(JSGDecorItemTags.CHEST_BOATS)
                .add(BoatTypes.LEMON.getDrop(true));

        tag(JSGDecorItemTags.DOORS)
                .addTag(JSGDecorItemTags.WOODEN_DOORS);

        tag(JSGDecorItemTags.FENCE_GATES)
                .addTag(JSGDecorItemTags.WOODEN_FENCE_GATES);

        tag(JSGDecorItemTags.FENCES)
                .addTag(JSGDecorItemTags.WOODEN_FENCES);

        tag(JSGDecorItemTags.LEAVES)
                .addTag(JSGDecorItemTags.LEMON_LEAVES);

        tag(JSGDecorItemTags.LEMON_LEAVES)
                .add(JSGDecorBlocks.LEMON_LEAVES.get().asItem());

        tag(JSGDecorItemTags.LOGS)
                .addTag(JSGDecorItemTags.LEMON_LOGS);

        tag(JSGDecorItemTags.LEMON_LOGS)
                .add(JSGDecorBlocks.LEMON_LOG.get().asItem())
                .add(JSGDecorBlocks.LEMON_WOOD.get().asItem())
                .add(JSGDecorBlocks.LEMON_LOG_STRIPPED.get().asItem())
                .add(JSGDecorBlocks.LEMON_WOOD_STRIPPED.get().asItem());

        tag(JSGDecorItemTags.LOGS_THAT_BURN)
                .addTag(JSGDecorItemTags.LEMON_LOGS);

        tag(JSGDecorItemTags.PLANKS)
                .add(JSGDecorBlocks.LEMON_PLANKS.get().asItem());

        tag(JSGDecorItemTags.SAPLINGS)
                .add(JSGDecorBlocks.LEMON_SAPLING.get().asItem());

        tag(JSGDecorItemTags.SLABS)
                .addTag(JSGDecorItemTags.WOODEN_SLABS);

        tag(JSGDecorItemTags.STAIRS)
                .addTag(JSGDecorItemTags.WOODEN_STAIRS);

        tag(JSGDecorItemTags.TRAPDOORS)
                .addTag(JSGDecorItemTags.WOODEN_TRAPDOORS);

        tag(JSGDecorItemTags.WOODEN_BUTTONS)
                .add(JSGDecorBlocks.LEMON_BUTTON.get().asItem());

        tag(JSGDecorItemTags.WOODEN_DOORS)
                .add(JSGDecorBlocks.LEMON_DOOR.get().asItem());

        tag(JSGDecorItemTags.WOODEN_FENCES)
                .add(JSGDecorBlocks.LEMON_FENCE.get().asItem());

        tag(JSGDecorItemTags.WOODEN_FENCE_GATES)
                .add(JSGDecorBlocks.LEMON_GATE.get().asItem());

        tag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES)
                .add(JSGDecorBlocks.LEMON_PRESSURE_PLATE.get().asItem());

        tag(JSGDecorItemTags.WOODEN_SLABS)
                .add(JSGDecorBlocks.LEMON_SLAB.get().asItem());

        tag(JSGDecorItemTags.WOODEN_STAIRS)
                .add(JSGDecorBlocks.LEMON_STAIRS.get().asItem());

        tag(JSGDecorItemTags.WOODEN_TRAPDOORS)
                .add(JSGDecorBlocks.LEMON_TRAPDOOR.get().asItem());

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
        tag(ItemTags.BOATS)
                .addTag(JSGDecorItemTags.BOATS);

        tag(ItemTags.BUTTONS)
                .addTag(JSGDecorItemTags.BUTTONS);

        tag(ItemTags.CHEST_BOATS)
                .addTag(JSGDecorItemTags.CHEST_BOATS);

        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL)
                .addTag(JSGDecorItemTags.LOGS)
                .addTag(JSGDecorItemTags.LEAVES);

        tag(ItemTags.DOORS)
                .addTag(JSGDecorItemTags.DOORS);

        tag(ItemTags.FENCE_GATES)
                .addTag(JSGDecorItemTags.FENCE_GATES);

        tag(ItemTags.FENCES)
                .addTag(JSGDecorItemTags.FENCES);

        tag(ItemTags.LEAVES)
                .addTag(JSGDecorItemTags.LEAVES);

        tag(ItemTags.LOGS)
                .addTag(JSGDecorItemTags.LOGS);

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(JSGDecorItemTags.LOGS_THAT_BURN);

        tag(ItemTags.PLANKS)
                .addTag(JSGDecorItemTags.PLANKS);

        tag(ItemTags.SAPLINGS)
                .addTag(JSGDecorItemTags.SAPLINGS);

        tag(ItemTags.SLABS)
                .addTag(JSGDecorItemTags.SLABS);

        tag(ItemTags.STAIRS)
                .addTag(JSGDecorItemTags.STAIRS);

        tag(ItemTags.TRAPDOORS)
                .addTag(JSGDecorItemTags.TRAPDOORS);

        tag(ItemTags.WOODEN_BUTTONS)
                .addTag(JSGDecorItemTags.WOODEN_BUTTONS);

        tag(ItemTags.WOODEN_DOORS)
                .addTag(JSGDecorItemTags.WOODEN_DOORS);

        tag(ItemTags.WOODEN_FENCES)
                .addTag(JSGDecorItemTags.WOODEN_FENCES);

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .addTag(JSGDecorItemTags.WOODEN_PRESSURE_PLATES);

        tag(ItemTags.WOODEN_SLABS)
                .addTag(JSGDecorItemTags.WOODEN_SLABS);

        tag(ItemTags.WOODEN_STAIRS)
                .addTag(JSGDecorItemTags.WOODEN_STAIRS);

        tag(ItemTags.WOODEN_TRAPDOORS)
                .addTag(JSGDecorItemTags.WOODEN_TRAPDOORS);

        //Minecraft braziers tags
        tag(ItemTags.PIGLIN_LOVED)
                .add(BrazierType.HATAK.block().get().asItem());

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
                .addTag(JSGDecorItemTags.GREEN_GLASS);*/
    }
}