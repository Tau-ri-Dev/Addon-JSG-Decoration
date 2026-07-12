package dev.tauri.jsgdecor.common.block;

import dev.tauri.jsg.core.common.registry.CoreItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class CoreDecorationBlocks {

    public enum Material {
        NAQUADAH("naquadah", MapColor.COLOR_GREEN, CoreItems.NAQUADAH_RAW_NUGGET::get),
        NAQUADAH_ALLOY("naquadah_alloy", MapColor.COLOR_CYAN, CoreItems.NAQUADAH_NUGGET::get),
        REFINED_NAQUADAH("refined_naquadah", MapColor.COLOR_LIGHT_GREEN, CoreItems.NAQUADAH_REFINED_NUGGET::get),
        TITANIUM("titanium", MapColor.COLOR_LIGHT_GRAY, CoreItems.TITANIUM_NUGGET::get),
        TRINIUM("trinium", MapColor.SNOW, CoreItems.TRINIUM_NUGGET::get);

        private final String material;
        private final MapColor mapColor;
        private final Supplier<Item> nuggetSupplier;

        Material(String material, MapColor mapColor, Supplier<Item> nuggetSupplier) {
            this.material = material;
            this.mapColor = mapColor;
            this.nuggetSupplier = nuggetSupplier;
        }

        public String getMaterial() {
            return material;
        }

        public MapColor getMapColor() {
            return mapColor;
        }

        public Item getNugget() {
            return nuggetSupplier.get();
        }
    }

    public enum Variant {
        PETRIFIED("petrified"),
        MONOLITHIC("monolithic"),
        SMOOTH("smooth"),
        BURNISHED("burnished"),
        EXPOSED("exposed"),
        WEATHERED("weathered"),
        CRACKED("cracked"),
        PILLAR("pillar"),
        COBBLED("cobbled"),
        CARVED_BRICKS("carved_bricks"),
        LARGE_BRICKS("large_bricks"),
        BIG_BRICKS("big_bricks"),
        CRACKED_BIG_BRICKS("cracked_big_bricks"),
        BRICKS("bricks"),
        CRACKED_BRICKS("cracked_bricks"),
        CHISELED("chiseled"),
        BIG_TILES("big_tiles"),
        SMALL_TILES("small_tiles"),
        POLISHED_TILES("polished_tiles"),
        TILLED_PILLAR("tilled_pillar"),
        CARVED_PILLAR("carved_pillar"),
        SCULPTED("sculpted"),
        SCULPTED_CREEPER("sculpted_creeper"),
        SCULPTED_GUARDIAN("sculpted_guardian"),
        SCULPTED_PIGLIN_SNOUT("sculpted_piglin_snout"),
        SCULPTED_WITHER("sculpted_wither"),
        SCULPTED_WITHER_SKELETON("sculpted_wither_skeleton"),
        SCULPTED_WARDEN("sculpted_warden");

        private final String variant;

        Variant(String variant) {
            this.variant = variant;
        }

        public String getVariant() {
            return variant;
        }
    }

    public enum Shape {
        BLOCK("block"),
        SLAB("slab"),
        STAIRS("stairs");

        private final String shape;

        Shape(String shape) {
            this.shape = shape;
        }

        public String getShape() {
            return shape;
        }
    }
}