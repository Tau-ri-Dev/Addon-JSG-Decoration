package dev.tauri.jsgdecor.datagen.loot;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.CoreDecorationBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSGDecorBlockLootTables extends BlockLootSubProvider {
    public JSGDecorBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(JSGDecorBlocks.LEMON_LOG_STRIPPED.get());
        dropSelf(JSGDecorBlocks.LEMON_LOG.get());
        dropSelf(JSGDecorBlocks.LEMON_WOOD_STRIPPED.get());
        dropSelf(JSGDecorBlocks.LEMON_WOOD.get());
        dropSelf(JSGDecorBlocks.LEMON_SAPLING.get());
        dropSelf(JSGDecorBlocks.LEMON_PLANKS.get());
        add(JSGDecorBlocks.LEMON_LEAVES.get(), block -> createLeavesDrops(block, JSGDecorBlocks.LEMON_SAPLING.get(), 0.5f, 0.0625f, 0.083333336F, 0.1f));

        add(JSGDecorBlocks.LEMON_SLAB.get(), this::createSlabItemTable);
        dropSelf(JSGDecorBlocks.LEMON_STAIRS.get());
        dropSelf(JSGDecorBlocks.LEMON_FENCE.get());
        dropSelf(JSGDecorBlocks.LEMON_GATE.get());
        dropSelf(JSGDecorBlocks.LEMON_BUTTON.get());
        dropSelf(JSGDecorBlocks.LEMON_PRESSURE_PLATE.get());
        add(JSGDecorBlocks.LEMON_DOOR.get(), this::createDoorTable);
        dropSelf(JSGDecorBlocks.LEMON_TRAPDOOR.get());

        for (Map.Entry<String, RegistryObject<Block>> entry : JSGDecorBlocks.ATLANTIS_BLOCKS.entrySet()) {
            String registryName = entry.getKey();
            Block block = entry.getValue().get();

            if (registryName.endsWith("_slab")) {
                add(block, this::createSlabItemTable);
            } else {
                dropSelf(block);
            }
        }

        //Core Block
        for (CoreDecorationBlocks.Material material : CoreDecorationBlocks.Material.values()) {
            for (CoreDecorationBlocks.Variant variant : CoreDecorationBlocks.Variant.values()) {
                for (CoreDecorationBlocks.Shape shape : CoreDecorationBlocks.Shape.values()) {

                    String name = material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();
                    Block block = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(name).get();

                    if (variant == CoreDecorationBlocks.Variant.PETRIFIED && shape == CoreDecorationBlocks.Shape.BLOCK) {
                        String cobbledName = material.getMaterial() + "_" + CoreDecorationBlocks.Variant.COBBLED.getVariant() + "_block";
                        Block cobbledBlock = JSGDecorBlocks.CORE_DECORATION_BLOCKS.get(cobbledName).get();

                        add(block, createSilkTouchDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(cobbledBlock))));
                        continue;
                    }
                    if (shape == CoreDecorationBlocks.Shape.SLAB) {
                        add(block, this::createSlabItemTable);
                        continue;
                    }

                    dropSelf(block);
                }
            }
        }

        dropSelf(JSGDecorBlocks.BROWN_WALL_BLOCK.get());
        dropSelf(JSGDecorBlocks.DENSELY_WRITTEN_BLOCK.get());
        dropSelf(JSGDecorBlocks.SPARSELY_WRITTEN_BLOCK.get());

        add(JSGDecorBlocks.RIGHT_RED_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.LEFT_RED_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.RIGHT_BLUE_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.LEFT_BLUE_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.RIGHT_GREEN_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.LEFT_GREEN_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);
        add(JSGDecorBlocks.GREEN_GLASS_BLOCK.get(), BlockLootSubProvider::createSilkTouchOnlyTable);

        JSGDecorBlocks.BRAZIERS.values().forEach(registryObject -> this.dropSelf(registryObject.get()));
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();

        JSGDecor.REGISTRY_HELPER.block().getEntries().forEach(ro -> blocks.add(ro.get()));
        JSGDecorBlocks.ATLANTIS_BLOCKS.values().forEach(ro -> blocks.add(ro.get()));

        return blocks;
        // return JSGDecor.REGISTRY_HELPER.block().getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
