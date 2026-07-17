package dev.tauri.jsgdecor.common.block;

import net.minecraft.world.level.material.MapColor;

public class BlockWithOverlay {
    public enum Material {
        BROWN_SPARSELY_WRITTEN_BLOCK    ("brown_sparsely_written", "brown_terracotta","sparsely_written", MapColor.COLOR_BROWN),
        BROWN_DENSELY_WRITTEN_BLOCK     ("brown_densely_written", "brown_terracotta","densely_written", MapColor.COLOR_BROWN);

        private final String material;
        private final String baseTexture;
        private final String overlayTexture;
        private final MapColor mapColor;

        Material(String material, String baseTexture, String overlayTexture, MapColor mapColor) {
            this.material = material;
            this.baseTexture = baseTexture;
            this.overlayTexture = overlayTexture;
            this.mapColor = mapColor;
        }

        public String getMaterial() { return material; }
        public String getBaseTexture() { return baseTexture; }
        public String getOverlayTexture() { return overlayTexture; }
        public MapColor getMapColor() { return mapColor; }
    }

    public enum Shape {
        BLOCK       ("block"),
        SLAB        ("slab"),
        STAIRS      ("stairs");

        private final String shape;

        Shape(String shape) {
            this.shape = shape;
        }

        public String getShape() { return shape; }
    }
}
