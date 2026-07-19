package dev.tauri.jsgdecor.common.block;

import dev.tauri.jsg.core.common.registry.tag.CoreItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MapColor;

public class StoneBasedDecorationBlock {

    public enum Material {
        NAQUADAH("naquadah", MapColor.COLOR_GREEN, CoreItemTags.NUGGET_NAQUADAH),
        NAQUADAH_ALLOY("naquadah_alloy", MapColor.COLOR_CYAN, CoreItemTags.NUGGET_NAQUADAH_ALLOY),
        REFINED_NAQUADAH("refined_naquadah", MapColor.COLOR_LIGHT_GREEN, CoreItemTags.NUGGET_NAQUADAH_REFINED),
        TITANIUM("titanium", MapColor.COLOR_LIGHT_GRAY, CoreItemTags.NUGGET_TITANIUM),
        TRINIUM("trinium", MapColor.SNOW, CoreItemTags.NUGGET_TRINIUM);

        private final String material;
        private final MapColor mapColor;
        private final TagKey<Item> nuggetTag;

        Material(String material, MapColor mapColor, TagKey<Item> nuggetTag) {
            this.material = material;
            this.mapColor = mapColor;
            this.nuggetTag = nuggetTag;
        }

        public String getMaterial() {
            return material;
        }

        public MapColor getMapColor() {
            return mapColor;
        }

        public TagKey<Item> getNuggetTag() {
            return nuggetTag;
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
        PILLAR("pillar", false, true, true),
        COBBLED("cobbled"),
        CARVED_BRICKS("carved_bricks", false),
        LARGE_BRICKS("large_bricks", false),
        BIG_BRICKS("big_bricks", false),
        CRACKED_BIG_BRICKS("cracked_big_bricks", false),
        BRICKS("bricks", false),
        CRACKED_BRICKS("cracked_bricks", false),
        CHISELED("chiseled", true),
        BIG_TILES("big_tiles", false),
        SMALL_TILES("small_tiles", false),
        POLISHED_TILES("polished_tiles", false, true,true),
        TILLED_PILLAR("tilled_pillar", false, true, true),
        CARVED_PILLAR("carved_pillar", false, true, true),
        SCULPTED("sculpted"),
        SCULPTED_CREEPER("sculpted_creeper", true, false, true),
        SCULPTED_GUARDIAN("sculpted_guardian", true, false, true),
        SCULPTED_PIGLIN_SNOUT("sculpted_piglin_snout"),
        SCULPTED_WITHER("sculpted_wither", true, false, true),
        SCULPTED_WITHER_SKELETON("sculpted_wither_skeleton"),
        SCULPTED_WARDEN("sculpted_warden");

        private final String variant;
        private final Boolean shouldSwapOrder;
        private final Boolean isRotatable;
        private final boolean hasDifferentSideTexture;

        Variant(String variant, boolean shouldSwapOrder, boolean isRotatable, boolean hasDifferentSideTexture) {
            this.variant = variant;
            this.shouldSwapOrder = shouldSwapOrder;
            this.isRotatable = isRotatable;
            this.hasDifferentSideTexture = hasDifferentSideTexture;
        }

        Variant(String variant, boolean shouldSwapOrder) { this(variant, shouldSwapOrder, false, false); }

        Variant(String variant) { this(variant, true, false, false); }

        public String getVariant() { return variant; }
        public Boolean shouldSwapOrder() { return shouldSwapOrder; }
        public Boolean isRotatable() { return isRotatable; }
        public Boolean hasDifferentSideTexture() { return hasDifferentSideTexture; }

        public String getTopBottomTexture() {
            return switch(this) {
                case SCULPTED_CREEPER, SCULPTED_WITHER -> "burnished";
                case POLISHED_TILES -> "small_tiles";
                case PILLAR, TILLED_PILLAR, CARVED_PILLAR, SCULPTED_GUARDIAN -> this.variant + "_top";
                default -> this.variant;
            };
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