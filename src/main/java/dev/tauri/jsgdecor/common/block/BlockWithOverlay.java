package dev.tauri.jsgdecor.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;

public class BlockWithOverlay {
    public enum Material {
        BROWN_SPARSELY_WRITTEN_BLOCK("brown_sparsely_written", Blocks.BROWN_TERRACOTTA, "sparsely_written", MapColor.COLOR_BROWN),
        BROWN_DENSELY_WRITTEN_BLOCK("brown_densely_written", Blocks.BROWN_TERRACOTTA, "densely_written", MapColor.COLOR_BROWN);

        private final String material;
        private final Block baseBlock;
        private final String overlayTexture;
        private final MapColor mapColor;

        Material(String material, Block baseBlock, String overlayTexture, MapColor mapColor) {
            this.material = material;
            this.baseBlock = baseBlock;
            this.overlayTexture = overlayTexture;
            this.mapColor = mapColor;
        }

        public String getMaterial() {
            return material;
        }

        public Block getBaseBlock() {
            return baseBlock;
        }

        public String getOverlayTexture() {
            return overlayTexture;
        }

        public MapColor getMapColor() {
            return mapColor;
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
