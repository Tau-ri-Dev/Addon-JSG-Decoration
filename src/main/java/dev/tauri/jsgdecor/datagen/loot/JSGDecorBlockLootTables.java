package dev.tauri.jsgdecor.datagen.loot;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.StoneBasedDecorationBlock;
import dev.tauri.jsgdecor.common.block.WoodBlock;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;

public class JSGDecorBlockLootTables extends BlockLootSubProvider {

    public JSGDecorBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropStandardBlocks(OVERLAY_BLOCKS);
        dropStandardBlocks(COMMON_BLOCKS);

        generateStoneBasedLoot();
        generateWoodLoot();

        JSGDecorBlocks.BRAZIERS.values().forEach(registryObject -> this.dropSelf(registryObject.get()));

        for (var block : GLASS_BLOCKS.values()) {
            Block dropBlock = block.get();
            add(dropBlock, noDrop().withPool(LootPool.lootPool().when(HAS_SILK_TOUCH).add(LootItem.lootTableItem(dropBlock))));
        }
    }

    private void dropStandardBlocks(Map<String, RegistryObject<Block>> blockMap) {
        for (var block : blockMap.values()) {
            Block dropBlock = block.get();
            if (dropBlock instanceof SlabBlock) {
                add(dropBlock, this::createSlabItemTable);
            } else {
                dropSelf(dropBlock);
            }
        }
    }

    private void generateStoneBasedLoot() {
        for (StoneBasedDecorationBlock.Material material : StoneBasedDecorationBlock.Material.values()) {
            for (StoneBasedDecorationBlock.Variant variant : StoneBasedDecorationBlock.Variant.values()) {
                for (StoneBasedDecorationBlock.Shape shape : StoneBasedDecorationBlock.Shape.values()) {

                    String name = variant.shouldSwapOrder()
                            ? variant.getVariant() + "_" + material.getMaterial() + "_" + shape.getShape()
                            : material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();

                    Block block = STONE_BASED_DECORATION_BLOCKS.get(name).get();

                    if (variant == StoneBasedDecorationBlock.Variant.PETRIFIED && shape == StoneBasedDecorationBlock.Shape.BLOCK) {
                        String cobbledName = StoneBasedDecorationBlock.Variant.COBBLED.getVariant() + "_" + material.getMaterial() + "_block";
                        Block cobbledBlock = STONE_BASED_DECORATION_BLOCKS.get(cobbledName).get();

                        add(block, createSilkTouchDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(cobbledBlock))));
                        continue;
                    }

                    if (block instanceof SlabBlock) {
                        add(block, this::createSlabItemTable);
                        continue;
                    }

                    dropSelf(block);
                }
            }
        }
    }

    private void generateWoodLoot() {
        for (WoodBlock.Material material : WoodBlock.Material.values()) {
            String woodType = material.getMaterial() + "_";

            for (WoodBlock.Shape shape : WoodBlock.Shape.values()) {
                String blockKey = getCorrectWoodBlockKey(shape, woodType);

                Block block = WOOD_BLOCKS.get(blockKey).get();

                switch (shape) {
                    case SLAB -> add(block, this::createSlabItemTable);
                    case DOOR -> add(block, this::createDoorTable);
                    case LEAVES -> {
                        Block saplingBlock = WOOD_BLOCKS.get(woodType + "sapling").get();
                        add(block, leavesBlock -> createLeavesDrops(leavesBlock, saplingBlock, NORMAL_LEAVES_SAPLING_CHANCES));
                    }
                    default -> dropSelf(block);
                }
            }
        }
    }

    private static @NotNull String getCorrectWoodBlockKey
            (WoodBlock.Shape shape, String woodType) {
        String blockKey = woodType + shape.getShape();

        if (shape == WoodBlock.Shape.LOG) {
            blockKey = woodType + "log";
        } else if (shape == WoodBlock.Shape.STRIPPED_LOG) {
            blockKey = "stripped_" + woodType + "log";
        } else if (shape == WoodBlock.Shape.WOOD) {
            blockKey = woodType + "wood";
        } else if (shape == WoodBlock.Shape.STRIPPED_WOOD) {
            blockKey = "stripped_" + woodType + "wood";
        }
        return blockKey;
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();

        JSGDecor.REGISTRY_HELPER.block().getEntries().forEach(registeredBlocks -> blocks.add(registeredBlocks.get()));

        return blocks;
        // return JSGDecor.REGISTRY_HELPER.block().getEntries().stream().map(RegistryObject::get)::iterator;
    }
}