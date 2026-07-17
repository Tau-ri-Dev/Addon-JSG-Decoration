package dev.tauri.jsgdecor.common.block;

import net.minecraft.world.item.DyeColor;

public class GlassBlock {

    public enum Material {
        //Atlantis glasses
        LEFT_WHITE_AND_GREEN_GLASS  ("left_white_and_green", "green_glass_pane_top", DyeColor.LIME),
        RIGHT_WHITE_AND_GREEN_GLASS ("right_white_and_green", "green_glass_pane_top", DyeColor.LIME),
        LEFT_GREEN_GLASS    ("left_green", "green_glass_pane_top", DyeColor.LIME),
        RIGHT_GREEN_GLASS   ("right_green", "green_glass_pane_top", DyeColor.LIME),
        LEFT_RED_GLASS      ("left_red", "red_glass_pane_top", DyeColor.RED),
        RIGHT_RED_GLASS     ("right_red","red_glass_pane_top", DyeColor.RED),
        LEFT_BLUE_GLASS     ("left_blue","blue_glass_pane_top", DyeColor.BLUE),
        RIGHT_BLUE_GLASS    ("right_blue","blue_glass_pane_top", DyeColor.BLUE),
        ATLANTIS_MOSAIC_TL  ("atlantis_mosaic_top_left", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_UML  ("atlantis_mosaic_upper_middle_left", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_LML  ("atlantis_mosaic_lower_middle_left", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_BL  ("atlantis_mosaic_bottom_left", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_TC  ("atlantis_mosaic_top_center", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_UMC  ("atlantis_mosaic_upper_middle_center", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_LMC  ("atlantis_mosaic_lower_middle_center", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_BC  ("atlantis_mosaic_bottom_center", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_TR  ("atlantis_mosaic_top_right", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_UMR  ("atlantis_mosaic_upper_middle_right", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_LMR  ("atlantis_mosaic_lower_middle_right", "transparent_glass_pane_top"),
        ATLANTIS_MOSAIC_BR  ("atlantis_mosaic_bottom_right", "transparent_glass_pane_top");

        private final String material;
        private final String edgeTexture;
        private final DyeColor dyeColor;

        Material(String material, String edgeTexture, DyeColor dyeColor) {
            this.material = material;
            this.edgeTexture = edgeTexture;
            this.dyeColor = dyeColor;
        }

        Material(String material, String edgeTexture) {
            this(material, edgeTexture, null);
        }

        public String getMaterial() { return material; }
        public String getTopPaneTexture() { return edgeTexture; }
        public DyeColor getDyeColor() { return dyeColor; }
    }

    public enum Shape {
        GLASS_BLOCK("glass_block"),
        GLASS_PANE("glass_pane");

        private final String shape;

        Shape(String shape) {
            this.shape = shape;
        }

        public String getShape() {
            return shape;
        }
    }
}
