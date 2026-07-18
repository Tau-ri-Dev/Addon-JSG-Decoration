package dev.tauri.jsgdecor.common.registry.tag;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class JSGDecorItemTags {
    public static TagKey<Item> BOATS = tag("boats");
    public static TagKey<Item> CHEST_BOATS = tag("chest_boats");
    public static TagKey<Item> LEAVES = tag("leaves");
    public static TagKey<Item> PLANKS = tag("planks");
    public static TagKey<Item> SAPLINGS = tag("saplings");
    public static TagKey<Item> SLABS = tag("slabs");
    public static TagKey<Item> STAIRS = tag("stairs");
    public static TagKey<Item> WOODEN_BUTTONS = tag("wooden_buttons");
    public static TagKey<Item> WOODEN_DOORS = tag("wooden_doors");
    public static TagKey<Item> WOODEN_FENCE_GATES = tag("wooden_fence_gates");
    public static TagKey<Item> WOODEN_FENCES = tag("wooden_fences");
    public static TagKey<Item> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
    public static TagKey<Item> WOODEN_SLABS = tag("wooden_slabs");
    public static TagKey<Item> WOODEN_STAIRS = tag("wooden_stairs");
    public static TagKey<Item> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
    public static TagKey<Item> LOGS_THAT_NOT_BURN = tag("logs_that_not_burn");
    public static TagKey<Item> LOGS_THAT_BURN = tag("logs_that_burn");
    public static TagKey<Item> LEMON_LOGS = tag("lemon_logs");
    public static TagKey<Item> GLASS_RED = tag("red_glass");
    public static TagKey<Item> GLASS_BLUE = tag("blue_glass");
    public static TagKey<Item> GLASS_LIME = tag("lime_glass");
    public static TagKey<Item> GLASS_COLORLESS = tag("colorless_glass");
    public static TagKey<Item> GLASS_PANE_RED = tag("red_glass_pane");
    public static TagKey<Item> GLASS_PANE_BLUE = tag("blue_glass_pane");
    public static TagKey<Item> GLASS_PANE_LIME = tag("lime_glass_pane");
    public static TagKey<Item> GLASS_PANE_COLORLESS = tag("colorless_glass_pane");


    private static TagKey<Item> tag(String name) {
        return ItemTags.create(JSGMapping.rl(JSGDecor.MOD_ID, name));
    }
}
