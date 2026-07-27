package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsg.core.common.registry.CoreTabs;
import dev.tauri.jsg.core.common.registry.helper.builder.block.BlockRegistryHelperGeneric;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.common.block.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JSGDecorBlocks {

    /**
     * Braziers
     */
    public static final Map<BrazierType, RegistryObject<BrazierBlock>> BRAZIERS = BrazierType.registerBlocks();


    public static final Map<String, RegistryObject<Block>> OVERLAY_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, RegistryObject<Block>> COMMON_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, RegistryObject<Block>> GLASS_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, RegistryObject<Block>> STONE_BASED_DECORATION_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, RegistryObject<Block>> WOOD_BLOCKS = new LinkedHashMap<>();
    public static final Map<WoodBlock.Shape, List<RegistryObject<Block>>> WOOD_BY_SHAPE = new LinkedHashMap<>();

    static {
        registerBlockWithOverlay();
        registerCommonBlocks();
        registerGlassBlocks();
        registerStoneBasedDecorationBlocks();
        registerWoodBlocks();
    }

    private static void registerBlockWithOverlay() {
        for (var mat : BlockWithOverlay.Material.values()) {
            var baseName = mat.getMaterial();

            var blockProps = BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(mat.getMapColor()).strength(2.0f, 3.0f);

            String blockName = baseName + "_block";
            var blockBuilder = createBuildingBlockBuilder(blockName, blockProps);
            OVERLAY_BLOCKS.put(blockName, blockBuilder.buildGeneric());

            String slabName = baseName + "_slab";
            var slabBuilder = createBuildingBlockBuilder(slabName, blockProps);
            OVERLAY_BLOCKS.put(slabName, slabBuilder.buildSlab());

            String stairsName = baseName + "_stairs";
            var stairsBuilder = createBuildingBlockBuilder(stairsName, blockProps);
            OVERLAY_BLOCKS.put(stairsName, stairsBuilder.buildStairs(() -> OVERLAY_BLOCKS.get(blockName).get().defaultBlockState()));
        }
    }

    private static void registerCommonBlocks() {
        for (var material : CommonBlock.Material.values()) {
            for (var variant : material.hasVariants() ? CommonBlock.Variant.values() : new CommonBlock.Variant[]{CommonBlock.Variant.DEFAULT}) {
                for (var shape : CommonBlock.Shape.values()) {

                    var baseName = (variant == CommonBlock.Variant.DEFAULT) ? material.getMaterial() : variant.getVariant() + "_" + material.getMaterial();

                    var name = baseName + "_" + shape.getShape();
                    var properties = BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(material.getMapColor());
                    var builder = createBuildingBlockBuilder(name, properties);

                    var block = switch (shape) {
                        case SLAB -> builder.buildSlab();
                        case STAIRS -> {
                            String parentBlock = baseName + "_block";
                            yield builder.buildStairs(() -> COMMON_BLOCKS.get(parentBlock).get().defaultBlockState());
                        }
                        default -> builder.buildGeneric();
                    };
                    COMMON_BLOCKS.put(name, block);

                    if (material.hasLampVariant()) {
                        String lampName = baseName + "_lamp_" + shape.getShape();
                        var lampProperties = BlockBehaviour.Properties.copy(Blocks.STONE)
                                .mapColor(material.getMapColor())
                                .lightLevel((state) -> 15)
                                .noOcclusion();
                        var lampBuilder = createBuildingBlockBuilder(lampName, lampProperties);

                        var lampBlock = switch (shape) {
                            case SLAB -> lampBuilder.buildSlab();
                            case STAIRS -> {
                                String parentLitBlock = baseName + "_lamp_block";
                                yield lampBuilder.buildStairs(() -> COMMON_BLOCKS.get(parentLitBlock).get().defaultBlockState());
                            }
                            default -> lampBuilder.buildGeneric();
                        };
                        COMMON_BLOCKS.put(lampName, lampBlock);
                    }
                }
            }
        }
    }

    private static void registerGlassBlocks() {
        for (var material : GlassBlock.Material.values()) {
            for (var shape : GlassBlock.Shape.values()) {
                var name = material.getMaterial() + "_" + shape.getShape();

                var properties = BlockBehaviour.Properties.copy(Blocks.GLASS);
                var builder = createBuildingBlockBuilder(name, properties);

                var registeredBlock = switch (shape) {
                    case GLASS_PANE -> builder.buildGlassPane(material.getDyeColor());
                    case GLASS_BLOCK -> builder.buildGlass(material.getDyeColor());
                };

                GLASS_BLOCKS.put(name, registeredBlock);
            }
        }
    }

    private static void registerStoneBasedDecorationBlocks() {
        StoneBasedDecorationBlock.forEach((material, variant, shape) -> {
            var name = variant.shouldSwapNameOrder() ? variant.getVariant() + "_" + material.getMaterial() + "_" + shape.getShape() : material.getMaterial() + "_" + variant.getVariant() + "_" + shape.getShape();

            var properties = BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(material.getMapColor());
            var builder = createBuildingBlockBuilder(name, properties);

            var registeredBlock = switch (shape) {
                case SLAB -> builder.buildSlab();
                case STAIRS -> {
                    String baseBlockName = variant.shouldSwapNameOrder()
                            ? variant.getVariant() + "_" + material.getMaterial() + "_block"
                            : material.getMaterial() + "_" + variant.getVariant() + "_block";
                    yield builder.buildStairs(() -> STONE_BASED_DECORATION_BLOCKS.get(baseBlockName).get().defaultBlockState());
                }
                default -> (variant.isRotatable() || variant.hasDifferentSideTexture())
                        ? builder.buildPillar(material.getMapColor(), material.getMapColor())
                        : builder.buildGeneric();
            };

            STONE_BASED_DECORATION_BLOCKS.put(name, registeredBlock);
        });
    }

    private static void registerWoodBlocks() {
        for (WoodBlock.Material mat : WoodBlock.Material.values()) {
            var woodType = mat.getMaterial() + "_";

            var strippedLogName = "stripped_" + woodType + "log";
            var logName = woodType + "log";
            var strippedWoodName = "stripped_" + woodType + "wood";
            var woodName = woodType + "wood";
            var planksName = woodType + "planks";

            var logProperties = BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(mat.getWoodColor());
            var leavesProperties = BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion().mapColor(mat.getLeavesColor());
            var saplingProperties = BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noCollission();
            var plankProperties = BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(mat.getPlanksColor());

            WOOD_BLOCKS.put(strippedLogName, createWoodBlockBuilder(strippedLogName, WoodBlock.Shape.STRIPPED_LOG, logProperties).buildPillar(mat.getWoodColor(), mat.getBarkColor()));
            WOOD_BLOCKS.put(strippedWoodName, createWoodBlockBuilder(strippedWoodName, WoodBlock.Shape.STRIPPED_WOOD, logProperties).buildPillar(mat.getWoodColor(), mat.getBarkColor()));

            WOOD_BLOCKS.put(logName, createWoodBlockBuilder(logName, WoodBlock.Shape.LOG, logProperties).addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, WOOD_BLOCKS.get(strippedLogName)).buildPillar(mat.getWoodColor(), mat.getBarkColor()));
            WOOD_BLOCKS.put(woodName, createWoodBlockBuilder(woodName, WoodBlock.Shape.WOOD, logProperties).addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, WOOD_BLOCKS.get(strippedWoodName)).buildPillar(mat.getWoodColor(), mat.getBarkColor()));


            WOOD_BLOCKS.put(woodType + "leaves", createWoodBlockBuilder(woodType + "leaves", WoodBlock.Shape.LEAVES, leavesProperties).buildLeaves());
            WOOD_BLOCKS.put(woodType + "sapling", Constants.JSGD_WOOD_HELPER.builder(woodType + "sapling").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(saplingProperties).buildSapling(mat::getTreeGrower));

            WOOD_BLOCKS.put(planksName, createWoodBlockBuilder(planksName, WoodBlock.Shape.PLANKS, plankProperties).buildGeneric());
            WOOD_BLOCKS.put(woodType + "slab", createWoodBlockBuilder(woodType + "slab", WoodBlock.Shape.SLAB, plankProperties).buildSlab());
            WOOD_BLOCKS.put(woodType + "stairs", createWoodBlockBuilder(woodType + "stairs", WoodBlock.Shape.STAIRS, plankProperties).buildStairs(() -> WOOD_BLOCKS.get(planksName).get().defaultBlockState()));

            WOOD_BLOCKS.put(woodType + "fence", createWoodBlockBuilder(woodType + "fence", WoodBlock.Shape.FENCE, plankProperties).buildFence());
            WOOD_BLOCKS.put(woodType + "fence_gate", createWoodBlockBuilder(woodType + "fence_gate", WoodBlock.Shape.FENCE_GATE, plankProperties).buildFenceGate(() -> SoundEvents.FENCE_GATE_OPEN, () -> SoundEvents.FENCE_GATE_CLOSE));

            WOOD_BLOCKS.put(woodType + "button", createWoodBlockBuilder(woodType + "button", WoodBlock.Shape.BUTTON, BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)).buildButton(BlockSetType.OAK, 30, true));
            WOOD_BLOCKS.put(woodType + "pressure_plate", createWoodBlockBuilder(woodType + "pressure_plate", WoodBlock.Shape.PRESSURE_PLATE, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)).buildPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK));

            WOOD_BLOCKS.put(woodType + "door", createWoodBlockBuilder(woodType + "door", WoodBlock.Shape.DOOR, BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion()).buildDoor(BlockSetType.OAK));
            WOOD_BLOCKS.put(woodType + "trapdoor", createWoodBlockBuilder(woodType + "trapdoor", WoodBlock.Shape.TRAPDOOR, BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion()).buildTrapdoor(BlockSetType.OAK));
        }

        for (WoodBlock.Shape shape : WoodBlock.Shape.values()) {
            String shapeSuffix = shape.getShape();
            for (Map.Entry<String, RegistryObject<Block>> entry : WOOD_BLOCKS.entrySet()) {
                if (entry.getKey().endsWith(shapeSuffix)) {
                    WOOD_BY_SHAPE.computeIfAbsent(shape, key -> new ArrayList<>()).add(entry.getValue());
                }
            }
        }
    }

    private static BlockRegistryHelperGeneric.GenericBlockBuilder createBuildingBlockBuilder(String name, BlockBehaviour.Properties properties) {
        return Constants.JSGD_BLOCK_HELPER.builder(name)
                .clearTooltip()
                .setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS, JSGDecorTabs.JSGD_BUILDING_TAB))
                .setProperties(properties);
    }

    private static BlockRegistryHelperGeneric.GenericBlockBuilder createWoodBlockBuilder(String name, WoodBlock.Shape shape, BlockBehaviour.Properties properties) {
        return Constants.JSGD_BLOCK_HELPER.builder(name)
                .clearTooltip()
                .setInTabs(List.of())
                .setFlammability(shape.getFlammability())
                .setFireSpreadSpeed(shape.getFireSpreadSpeed())
                .setProperties(properties);
    }

    public static void init() {
    }
}
