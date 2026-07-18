package dev.tauri.jsgdecor.common.registry.tag;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class JSGDecorBlockTags {
    public static TagKey<Block> LOGS_THAT_NOT_BURN = tag("logs_that_not_burn");
    public static TagKey<Block> LOGS_THAT_BURN = tag("logs_that_burn");
    public static TagKey<Block> LEMON_LOGS = tag("lemon_logs");
    public static TagKey<Block> OVERWORLD_NATURAL_LOGS = tag("overworld_natural_logs");
    public static TagKey<Block> LEAVES = tag("leaves");
    public static TagKey<Block> PLANKS = tag("planks");
    public static TagKey<Block> SAPLINGS = tag("saplings");
    public static TagKey<Block> SLABS = tag("slabs");
    public static TagKey<Block> STAIRS = tag("stairs");
    public static TagKey<Block> WOODEN_BUTTONS = tag("wooden_buttons");
    public static TagKey<Block> WOODEN_DOORS = tag("wooden_doors");
    public static TagKey<Block> WOODEN_FENCES = tag("wooden_fences");
    public static TagKey<Block> WOODEN_FENCE_GATES = tag("wooden_fence_gates");
    public static TagKey<Block> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
    public static TagKey<Block> WOODEN_SLABS = tag("wooden_slabs");
    public static TagKey<Block> WOODEN_STAIRS = tag("wooden_stairs");
    public static TagKey<Block> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
    public static TagKey<Block> BRAZIERS = tag("braziers");
    public static TagKey<Block> NAQUADAH_BASED_MATERIAL = tag("naquadah_based_material");
    public static TagKey<Block> NAQUADAH_ALLOY_BASED_MATERIAL = tag("naquadah_alloy_based_material");
    public static TagKey<Block> REFINED_NAQUADAH_BASED_MATERIAL = tag("refined_naquadah_based_material");
    public static TagKey<Block> TITANIUM_BASED_MATERIAL = tag("titanium_based_material");
    public static TagKey<Block> TRINIUM_BASED_MATERIAL = tag("trinium_based_material");
    public static TagKey<Block> MINEABLE_STONE_PICKAXE = tag("mineable_with_stone_pickaxe");
    public static TagKey<Block> NOT_LEAKING_BLOCKS = tag("impermeable");
    public static TagKey<Block> GLASS_BLUE = tag("blue_glass");
    public static TagKey<Block> GLASS_LIME = tag("lime_glass");
    public static TagKey<Block> GLASS_RED = tag("red_glass");
    public static TagKey<Block> GLASS_COLORLESS = tag("colorless_glass");
    public static TagKey<Block> GLASS_PANE_BLUE = tag("blue_glass_pane");
    public static TagKey<Block> GLASS_PANE_LIME = tag("lime_glass_pane");
    public static TagKey<Block> GLASS_PANE_RED = tag("red_glass_pane");
    public static TagKey<Block> GLASS_PANE_COLORLESS = tag("colorless_glass_pane");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(JSGMapping.rl(JSGDecor.MOD_ID, name));
    }
}
