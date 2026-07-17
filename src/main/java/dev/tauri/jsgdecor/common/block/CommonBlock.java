package dev.tauri.jsgdecor.common.block;

import net.minecraft.world.level.material.MapColor;

public class CommonBlock {
    public enum Material {
        BLUE_ATLANTIS_WALL("blue_atlantis_wall", false, true, MapColor.COLOR_LIGHT_BLUE),
        GRAY_ATLANTIS_WALL("gray_atlantis_wall", false, true,MapColor.COLOR_LIGHT_GRAY),
        BROWN_ATLANTIS_WALL("brown_atlantis_wall", false,false, MapColor.COLOR_BROWN),
        WHITE ("white", true, true, MapColor.NONE, "white_block"),
        ATLANTIS_BLUE ("atlantis_blue", true, true, MapColor.NONE, "atlantis_blue_block");

        private final String material;
        private final Boolean isLightSource;
        private final Boolean hasVariants;
        private final MapColor mapColor;
        private final String texture;

        Material(String material, Boolean isLightSource,Boolean hasVariants, MapColor mapColor, String texture) {
            this.material = material;
            this.isLightSource = isLightSource;
            this.hasVariants = hasVariants;
            this.mapColor = mapColor;
            this.texture = texture;
        }

        Material(String material, Boolean isLightSource, Boolean hasVariants, MapColor mapColor) {
            this(material, isLightSource, hasVariants, mapColor, material);
        }

        public String getMaterial() { return material; }
        public Boolean hasLampVariant() { return isLightSource; }
        public Boolean hasVariants() { return hasVariants; }
        public MapColor getMapColor() { return mapColor; }
        public String getTextureName() { return texture; }
    }

    public enum Variant {
        DEFAULT ("default"),
        AGED    ("aged"),
        FLOODED ("flooded");

        private final String variant;

        Variant(String variant) {
            this.variant = variant;
        }

        public String getVariant() {
            return variant;
        }
    }

    public enum Shape {
        BLOCK       ("block"),
        SLAB        ("slab"),
        STAIRS      ("stairs");

        private final String shape;

        Shape(String shape) {
            this.shape = shape;
        }

        public String getShape() {
            return shape;
        }
    }
}
