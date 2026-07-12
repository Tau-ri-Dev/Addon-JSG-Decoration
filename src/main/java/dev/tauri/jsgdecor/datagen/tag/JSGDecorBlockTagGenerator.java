package dev.tauri.jsgdecor.datagen.tag;

import dev.tauri.jsg.core.common.registry.tag.CoreBlockTags;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.block.CoreDecorationBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.tag.JSGDecorBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDecorBlockTagGenerator extends BlockTagsProvider {
    public JSGDecorBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {
        //JSGDecor wood and wood products tags
        tag(JSGDecorBlockTags.WOODEN_BUTTONS)
                .add(JSGDecorBlocks.LEMON_BUTTON.get());

        tag(JSGDecorBlockTags.BUTTONS)
                .addTag(JSGDecorBlockTags.WOODEN_BUTTONS);

        tag(JSGDecorBlockTags.WOODEN_DOORS)
                .add(JSGDecorBlocks.LEMON_DOOR.get());

        tag(JSGDecorBlockTags.DOORS)
                .addTag(JSGDecorBlockTags.WOODEN_DOORS);

        tag(JSGDecorBlockTags.WOODEN_FENCE_GATES)
                .add(JSGDecorBlocks.LEMON_GATE.get());

        tag(JSGDecorBlockTags.FENCE_GATES)
                .addTag(JSGDecorBlockTags.WOODEN_FENCE_GATES);

        tag(JSGDecorBlockTags.WOODEN_FENCES)
                .add(JSGDecorBlocks.LEMON_FENCE.get());

        tag(JSGDecorBlockTags.FENCES)
                .addTag(JSGDecorBlockTags.WOODEN_FENCES);

        tag(JSGDecorBlockTags.LEMON_LEAVES)
                .add(JSGDecorBlocks.LEMON_LEAVES.get());

        tag(JSGDecorBlockTags.LEAVES)
                .addTag(JSGDecorBlockTags.LEMON_LEAVES);

        tag(JSGDecorBlockTags.LEMON_LOGS)
                .add(JSGDecorBlocks.LEMON_LOG.get())
                .add(JSGDecorBlocks.LEMON_WOOD.get())
                .add(JSGDecorBlocks.LEMON_LOG_STRIPPED.get())
                .add(JSGDecorBlocks.LEMON_WOOD_STRIPPED.get());

        tag(JSGDecorBlockTags.LOGS)
                .addTag(JSGDecorBlockTags.LEMON_LOGS);

        tag(JSGDecorBlockTags.LOGS_THAT_BURN)
                .addTag(JSGDecorBlockTags.LEMON_LOGS);

        tag(JSGDecorBlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(JSGDecorBlockTags.LEMON_LOGS);

        tag(JSGDecorBlockTags.PLANKS)
                .add(JSGDecorBlocks.LEMON_PLANKS.get());

        tag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES)
                .add(JSGDecorBlocks.LEMON_PRESSURE_PLATE.get());

        tag(JSGDecorBlockTags.PRESSURE_PLATES)
                .addTag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES);

        tag(JSGDecorBlockTags.SAPLINGS)
                .add(JSGDecorBlocks.LEMON_SAPLING.get());

        tag(JSGDecorBlockTags.WOODEN_SLABS)
                .add(JSGDecorBlocks.LEMON_SLAB.get());

        tag(JSGDecorBlockTags.SLABS)
                .addTag(JSGDecorBlockTags.WOODEN_SLABS);

        tag(JSGDecorBlockTags.WOODEN_STAIRS)
                .add(JSGDecorBlocks.LEMON_STAIRS.get());

        tag(JSGDecorBlockTags.STAIRS)
                .addTag(JSGDecorBlockTags.WOODEN_STAIRS);

        tag(JSGDecorBlockTags.WOODEN_TRAPDOORS)
                .add(JSGDecorBlocks.LEMON_TRAPDOOR.get());

        tag(JSGDecorBlockTags.TRAPDOORS)
                .addTag(JSGDecorBlockTags.WOODEN_TRAPDOORS);

        //JSGDecor braziers tags
        tag(JSGDecorBlockTags.BRAZIERS)
                .add(BrazierType.ABYDOS.block().get())
                .add(BrazierType.HATAK.block().get())
                .add(BrazierType.GOAULD.block().get())
                .add(BrazierType.ANUBIS.block().get());

        //Decor blocks tags

        //

                /*.add(JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get())
                .add(JSGDecorBlocks.BLUE_ATLANTIS_LAMP_BLOCK.get())
                .add(JSGDecorBlocks.WHITE_LAMP_BLOCK.get())
                .add(JSGDecorBlocks.ATLANTIS_WALL_BLOCK.get())
                .add(JSGDecorBlocks.FLOODED_ATLANTIS_WALL_BLOCK.get())
                .add(JSGDecorBlocks.AGED_ATLANTIS_WALL_BLOCK.get())
                .add(JSGDecorBlocks.BROWN_WALL_BLOCK.get())
                .add(JSGDecorBlocks.SPARSELY_WRITTEN_BLOCK.get())
                .add(JSGDecorBlocks.DENSELY_WRITTEN_BLOCK.get());*/

        // Core Decoration
        for (CoreDecorationBlocks.Material material : CoreDecorationBlocks.Material.values()) {
            for (CoreDecorationBlocks.Variant variant : CoreDecorationBlocks.Variant.values()) {
                for (CoreDecorationBlocks.Shape shape : CoreDecorationBlocks.Shape.values()) {

                    String name = material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();
                    Block block = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(name).get();

                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                    if (shape == CoreDecorationBlocks.Shape.SLAB)  tag(JSGDecorBlockTags.SLABS).add(block);
                    if (shape == CoreDecorationBlocks.Shape.STAIRS) tag(JSGDecorBlockTags.STAIRS).add(block);

                    switch (material) {
                        case NAQUADAH         -> tag(JSGDecorBlockTags.NAQUADAH_BASED_MATERIAL).add(block);
                        case NAQUADAH_ALLOY   -> tag(JSGDecorBlockTags.NAQUADAH_ALLOY_BASED_MATERIAL).add(block);
                        case REFINED_NAQUADAH -> tag(JSGDecorBlockTags.REFINED_NAQUADAH_BASED_MATERIAL).add(block);
                        case TITANIUM         -> tag(JSGDecorBlockTags.TITANIUM_BASED_MATERIAL).add(block);
                        case TRINIUM          -> tag(JSGDecorBlockTags.TRINIUM_BASED_MATERIAL).add(block);
                    }
                }
            }
        }



        //JSGDecor glass tags
        tag(JSGDecorBlockTags.GLASS)
                .addTag(JSGDecorBlockTags.STAINED_GLASS);

        tag(JSGDecorBlockTags.STAINED_GLASS)
                .addTag(JSGDecorBlockTags.RED_GLASS)
                .addTag(JSGDecorBlockTags.GREEN_GLASS)
                .addTag(JSGDecorBlockTags.BLUE_GLASS);

        tag(JSGDecorBlockTags.RED_GLASS)
                .add(JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get())
                .add(JSGDecorBlocks.LEFT_RED_GLASS_BLOCK.get());

        tag(JSGDecorBlockTags.BLUE_GLASS)
                .add(JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get())
                .add(JSGDecorBlocks.LEFT_BLUE_GLASS_BLOCK.get());

        tag(JSGDecorBlockTags.GREEN_GLASS)
                .add(JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get())
                .add(JSGDecorBlocks.LEFT_GREEN_GLASS_BLOCK.get())
                .add(JSGDecorBlocks.GREEN_GLASS_BLOCK.get());

        //JSG Core tags
        tag(CoreBlockTags.SUPPORT_LEMON)
                .addTag(JSGDecorBlockTags.LEMON_LEAVES);

        //Mining tags
        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(JSGDecorBlockTags.LOGS)
                .addTag(JSGDecorBlockTags.PLANKS)
                .addTag(JSGDecorBlockTags.WOODEN_SLABS)
                .addTag(JSGDecorBlockTags.WOODEN_STAIRS)
                .addTag(JSGDecorBlockTags.WOODEN_FENCES)
                .addTag(JSGDecorBlockTags.WOODEN_FENCE_GATES)
                .addTag(JSGDecorBlockTags.WOODEN_BUTTONS)
                .addTag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES)
                .addTag(JSGDecorBlockTags.WOODEN_DOORS)
                .addTag(JSGDecorBlockTags.WOODEN_TRAPDOORS)
                .addTag(JSGDecorBlockTags.SAPLINGS);

        tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(JSGDecorBlockTags.LEAVES);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(JSGDecorBlockTags.BRAZIERS);

        tag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(JSGDecorBlockTags.NAQUADAH_BASED_MATERIAL)
                .addTag(JSGDecorBlockTags.TITANIUM_BASED_MATERIAL)
                .addTag(JSGDecorBlockTags.NAQUADAH_ALLOY_BASED_MATERIAL);

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(JSGDecorBlockTags.BRAZIERS)
                .addTag(JSGDecorBlockTags.TRINIUM_BASED_MATERIAL)
                .addTag(JSGDecorBlockTags.REFINED_NAQUADAH_BASED_MATERIAL);

        //minecraft wood and wood products tags
        tag(BlockTags.BUTTONS)
                .addTag(JSGDecorBlockTags.BUTTONS);

        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .addTag(JSGDecorBlockTags.LOGS)
                .addTag(JSGDecorBlockTags.LEAVES);

        tag(BlockTags.DOORS)
                .addTag(JSGDecorBlockTags.DOORS);

        tag(BlockTags.FENCE_GATES)
                .addTag(JSGDecorBlockTags.FENCE_GATES);

        tag(BlockTags.FENCES)
                .addTag(JSGDecorBlockTags.FENCES);

        tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)
                .addTag(JSGDecorBlockTags.LOGS)
                .addTag(JSGDecorBlockTags.LEAVES);

        tag(BlockTags.LEAVES)
                .addTag(JSGDecorBlockTags.LEAVES);

        tag(BlockTags.LOGS)
                .addTag(JSGDecorBlockTags.LOGS);

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(JSGDecorBlockTags.LOGS_THAT_BURN);

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(JSGDecorBlockTags.OVERWORLD_NATURAL_LOGS);

        tag(BlockTags.PARROTS_SPAWNABLE_ON)
                .addTag(JSGDecorBlockTags.LOGS);

        tag(BlockTags.PLANKS)
                .addTag(JSGDecorBlockTags.PLANKS);

        tag(BlockTags.PRESSURE_PLATES)
                .addTag(JSGDecorBlockTags.PRESSURE_PLATES);

        tag(BlockTags.REPLACEABLE_BY_TREES)
                .addTag(JSGDecorBlockTags.LEAVES);

        tag(BlockTags.SAPLINGS)
                .addTag(JSGDecorBlockTags.SAPLINGS);

        tag(BlockTags.SLABS)
                .addTag(JSGDecorBlockTags.SLABS);

        tag(BlockTags.SNAPS_GOAT_HORN)
                .addTag(JSGDecorBlockTags.LOGS);

        tag(BlockTags.STAIRS)
                .addTag(JSGDecorBlockTags.STAIRS);

        tag(BlockTags.SWORD_EFFICIENT)
                .addTag(JSGDecorBlockTags.LEAVES)
                .addTag(JSGDecorBlockTags.SAPLINGS);

        tag(BlockTags.TRAPDOORS)
                .addTag(JSGDecorBlockTags.TRAPDOORS);

        tag(BlockTags.UNSTABLE_BOTTOM_CENTER)
                .addTag(JSGDecorBlockTags.FENCE_GATES);

        tag(BlockTags.WALL_POST_OVERRIDE)
                .addTag(JSGDecorBlockTags.PRESSURE_PLATES);

        tag(BlockTags.WOODEN_BUTTONS)
                .addTag(JSGDecorBlockTags.WOODEN_BUTTONS);

        tag(BlockTags.WOODEN_DOORS)
                .addTag(JSGDecorBlockTags.WOODEN_DOORS);

        tag(BlockTags.WOODEN_FENCES)
                .addTag(JSGDecorBlockTags.WOODEN_FENCES);

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .addTag(JSGDecorBlockTags.WOODEN_PRESSURE_PLATES);

        tag(BlockTags.WOODEN_SLABS)
                .addTag(JSGDecorBlockTags.WOODEN_SLABS);

        tag(BlockTags.WOODEN_STAIRS)
                .addTag(JSGDecorBlockTags.WOODEN_STAIRS);

        tag(BlockTags.WOODEN_TRAPDOORS)
                .addTag(JSGDecorBlockTags.WOODEN_TRAPDOORS);

        //Minecraft braziers tags
        tag(BlockTags.GUARDED_BY_PIGLINS)
                .add(BrazierType.HATAK.block().get());

        //Forge Tags
        tag(Tags.Blocks.FENCES)
                .addTag(JSGDecorBlockTags.FENCES);

        tag(Tags.Blocks.FENCES_WOODEN)
                .addTag(JSGDecorBlockTags.WOODEN_FENCES);

        tag(Tags.Blocks.FENCE_GATES)
                .addTag(JSGDecorBlockTags.FENCE_GATES);

        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .addTag(JSGDecorBlockTags.WOODEN_FENCE_GATES);

        tag(Tags.Blocks.GLASS)
                .addTag(JSGDecorBlockTags.GLASS);

        tag(Tags.Blocks.STAINED_GLASS)
                .addTag(JSGDecorBlockTags.STAINED_GLASS);

        tag(Tags.Blocks.GLASS_RED)
                .addTag(JSGDecorBlockTags.RED_GLASS);

        tag(Tags.Blocks.GLASS_BLUE)
                .addTag(JSGDecorBlockTags.BLUE_GLASS);

        tag(Tags.Blocks.GLASS_GREEN)
                .addTag(JSGDecorBlockTags.GREEN_GLASS);
    }
}