package dev.tauri.jsgdecor.datagen.loot;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

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

        dropSelf(JSGDecorBlocks.BLUE_ATLANTIS_BLOCK.get());
        dropSelf(JSGDecorBlocks.BLUE_ATLANTIS_LAMP_BLOCK.get());
        dropSelf(JSGDecorBlocks.CLEAR_WHITE_BLOCK.get());
        dropSelf(JSGDecorBlocks.LIGHT_WALL_BLOCK.get());
        dropSelf(JSGDecorBlocks.STANDARD_WALL_BLOCK.get());
        dropSelf(JSGDecorBlocks.AGED_WALL_BLOCK.get());
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

        JSGDecorBlocks.BRAZIERS.values().forEach(registryObject -> {
            this.add(registryObject.get(), this::createDoorTable);
        });
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return JSGDecor.REGISTRY_HELPER.block().getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
