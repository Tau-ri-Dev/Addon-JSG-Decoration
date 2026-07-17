package dev.tauri.jsgdecor.common.block;

import dev.tauri.jsgdecor.common.worldgen.tree.Growers;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class WoodBlock {
    public enum Material {
        LEMON("lemon", MapColor.WOOD, MapColor.PODZOL, MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_LIGHT_GRAY, Growers.LEMON_TREE_GROWER);

        private final String material;
        private final MapColor woodColor;
        private final MapColor barkColor;
        private final MapColor leavesColor;
        private final MapColor planksColor;
        private final Supplier<AbstractTreeGrower> treeGrower;

        Material(String material, MapColor woodColor, MapColor barkColor, MapColor leavesColor, MapColor planksColor, Supplier<AbstractTreeGrower> treeGrower) {
            this.material = material;
            this.woodColor = woodColor;
            this.barkColor = barkColor;
            this.leavesColor = leavesColor;
            this.planksColor = planksColor;
            this.treeGrower = treeGrower;
        }

        public String getMaterial() { return material; }
        public MapColor getWoodColor() { return woodColor; }
        public MapColor getBarkColor() { return barkColor; }
        public MapColor getLeavesColor() { return leavesColor; }
        public MapColor getPlanksColor() { return planksColor; }
        public AbstractTreeGrower getTreeGrower() { return treeGrower.get(); }
    }

    public enum Shape {
        LOG                 ("log",5,5),
        STRIPPED_LOG        ("stripped_log",5,5),
        WOOD                ("wood",5,5),
        STRIPPED_WOOD       ("stripped_wood",5,5),
        LEAVES              ("leaves", 30, 60),
        SAPLING             ("sapling", 0, 0),
        PLANKS              ("planks", 5, 20),
        SLAB                ("slab", 5, 20),
        STAIRS              ("stairs", 5, 20),
        FENCE               ("fence", 5, 20),
        FENCE_GATE          ("fence_gate", 5, 20),
        BUTTON              ("button", 0, 0),
        PRESSURE_PLATE      ("pressure_plate", 0, 0),
        DOOR                ("door", 0, 0),
        TRAPDOOR            ("trapdoor", 0, 0);

        private final String shape;
        private final int flammability;
        private final int fireSpreadSpeed;

        Shape(String shape, int flammability, int fireSpreadSpeed) {
            this.shape = shape;
            this.flammability = flammability;
            this.fireSpreadSpeed = fireSpreadSpeed;
        }

        public String getShape() { return shape; }
        public int getFlammability() { return flammability; }
        public int getFireSpreadSpeed() { return fireSpreadSpeed; }
    }
}
