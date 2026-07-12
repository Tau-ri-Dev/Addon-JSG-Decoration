package dev.tauri.jsgdecor.common.registry.tag;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class JSGDecorBlockTags {
    public static TagKey<Block> BUTTONS = tag("buttons");
    public static TagKey<Block> LOGS = tag("logs");
    public static TagKey<Block> LOGS_THAT_BURN = tag("logs_that_burn");
    public static TagKey<Block> OVERWORLD_NATURAL_LOGS = tag("overworld_natural_logs");
    public static TagKey<Block> LEAVES = tag("leaves");
    public static TagKey<Block> DOORS = tag("doors");
    public static TagKey<Block> FENCE_GATES = tag("fence_gates");
    public static TagKey<Block> FENCES = tag("fences");
    public static TagKey<Block> PLANKS = tag("planks");
    public static TagKey<Block> PRESSURE_PLATES = tag("pressure_plates");
    public static TagKey<Block> SAPLINGS = tag("saplings");
    public static TagKey<Block> SLABS = tag("slabs");
    public static TagKey<Block> STAIRS = tag("stairs");
    public static TagKey<Block> TRAPDOORS = tag("trapdoors");
    public static TagKey<Block> WOODEN_BUTTONS = tag("wooden_buttons");
    public static TagKey<Block> WOODEN_DOORS = tag("wooden_doors");
    public static TagKey<Block> WOODEN_FENCES = tag("wooden_fences");
    public static TagKey<Block> WOODEN_FENCE_GATES = tag("wooden_fence_gates");
    public static TagKey<Block> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
    public static TagKey<Block> WOODEN_SLABS = tag("wooden_slabs");
    public static TagKey<Block> WOODEN_STAIRS = tag("wooden_stairs");
    public static TagKey<Block> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
    public static TagKey<Block> BRAZIERS = tag("braziers");
    public static TagKey<Block> RED_GLASS = tag("red_glasses");
    public static TagKey<Block> BLUE_GLASS = tag("blue_glasses");
    public static TagKey<Block> GREEN_GLASS = tag("green_glasses");
    public static TagKey<Block> STAINED_GLASS = tag("stained_glasses");
    public static TagKey<Block> GLASS = tag("glass");
    public static TagKey<Block> LEMON_LOGS = tag("lemon_logs");
    public static TagKey<Block> LEMON_LEAVES = tag("lemon_leaves");
    public static TagKey<Block> NAQUADAH_BASED_MATERIAL = tag("naquadah_based_material");
    public static TagKey<Block> NAQUADAH_ALLOY_BASED_MATERIAL = tag("naquadah_alloy_based_material");
    public static TagKey<Block> REFINED_NAQUADAH_BASED_MATERIAL = tag("refined_naquadah_based_material");
    public static TagKey<Block> TITANIUM_BASED_MATERIAL = tag("titanium_based_material");
    public static TagKey<Block> TRINIUM_BASED_MATERIAL = tag("trinium_based_material");


    private static TagKey<Block> tag(String name) {
        return BlockTags.create(JSGMapping.rl(JSGDecor.MOD_ID, name));
    }
}
