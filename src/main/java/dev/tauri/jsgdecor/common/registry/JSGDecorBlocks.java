package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsg.core.common.registry.CoreTabs;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.common.block.BrazierBlock;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.worldgen.tree.Growers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JSGDecorBlocks {

    //TODO: map colors

    /**
     * TREE BLOCKS
     */
    public static final RegistryObject<Block> LEMON_LOG_STRIPPED = Constants.JSGD_BLOCK_HELPER.builder("stripped_lemon_log").clearTooltip().setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_LOG = Constants.JSGD_BLOCK_HELPER.builder("lemon_log").clearTooltip().addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, LEMON_LOG_STRIPPED).setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_WOOD_STRIPPED = Constants.JSGD_BLOCK_HELPER.builder("stripped_lemon_wood").clearTooltip().setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_WOOD = Constants.JSGD_BLOCK_HELPER.builder("lemon_wood").clearTooltip().addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, LEMON_WOOD_STRIPPED).setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_LEAVES = Constants.JSGD_BLOCK_HELPER.builder("lemon_leaves").clearTooltip().setFlammability(30).setFireSpreadSpeed(60).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion().mapColor(MapColor.COLOR_LIGHT_GREEN)).buildLeaves();
    public static final RegistryObject<Block> LEMON_SAPLING = Constants.JSGD_WOOD_HELPER.builder("lemon_sapling").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noCollission()).buildSapling(Growers.LEMON_TREE_GROWER);

    /**
     * WOOD & WOOD BLOCKS
     */

    public static final RegistryObject<Block> LEMON_PLANKS = Constants.JSGD_BLOCK_HELPER.builder("lemon_planks").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildGeneric();
    public static final RegistryObject<Block> LEMON_SLAB = Constants.JSGD_BLOCK_HELPER.builder("lemon_slab").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildSlab();
    public static final RegistryObject<Block> LEMON_STAIRS = Constants.JSGD_BLOCK_HELPER.builder("lemon_stairs").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildStairs(() -> LEMON_PLANKS.get().defaultBlockState());

    public static final RegistryObject<Block> LEMON_FENCE = Constants.JSGD_BLOCK_HELPER.builder("lemon_fence").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildFence();
    public static final RegistryObject<Block> LEMON_GATE = Constants.JSGD_BLOCK_HELPER.builder("lemon_fence_gate").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildFenceGate(() -> SoundEvents.FENCE_GATE_OPEN, () -> SoundEvents.FENCE_GATE_CLOSE);

    public static final RegistryObject<Block> LEMON_BUTTON = Constants.JSGD_BLOCK_HELPER.builder("lemon_button").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)).buildButton(BlockSetType.OAK, 30, true);
    public static final RegistryObject<Block> LEMON_PRESSURE_PLATE = Constants.JSGD_BLOCK_HELPER.builder("lemon_pressure_plate").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)).buildPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK);

    public static final RegistryObject<Block> LEMON_DOOR = Constants.JSGD_BLOCK_HELPER.builder("lemon_door").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion()).buildDoor(BlockSetType.OAK);
    public static final RegistryObject<Block> LEMON_TRAPDOOR = Constants.JSGD_BLOCK_HELPER.builder("lemon_trapdoor").clearTooltip().setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion()).buildTrapdoor(BlockSetType.OAK);

    /**
     * Goauld Decoration Blocks
     */

    public static final Map<BrazierType, RegistryObject<BrazierBlock>> BRAZIERS = BrazierType.registerBlocks();

    /**
     * SGC Decoration Blocks
     */

    /**
     * Core block variants
     */

    public static final Map<String, RegistryObject<Block>> CORE_DECORATION_BLOCKS = new LinkedHashMap<>();

    private enum Material {
        NAQUADAH         ("naquadah", MapColor.COLOR_GREEN),
        NAQUADAH_ALLOY   ("naquadah_alloy", MapColor.COLOR_CYAN),
        REFINED_NAQUADAH ("refined_naquadah", MapColor.COLOR_LIGHT_GREEN),
        TITANIUM         ("titanium", MapColor.COLOR_GRAY),
        TRINIUM          ("trinium", MapColor.SNOW);

        final String id;
        final MapColor color;

        Material(String id, MapColor color) {
            this.id = id;
            this.color = color;
        }
    }

    private static final List<String> CORE_VARIANTS = List.of(
            "petrified", "monolithic", "smooth", "burnished", "exposed", "weathered", "cracked", "pillar",
            "cobbled", "carved_bricks", "large_bricks", "big_bricks", "cracked_big_bricks", "bricks", "cracked_bricks",
            "chiseled", "big_tiles", "small_tiles", "polished_tiles", "tilled_pillar", "carved_pillar",
            "sculpted", "sculpted_creeper", "sculpted_guardian", "sculpted_piglin_snout", "sculpted_wither",
            "sculpted_wither_skeleton", "sculpted_warden"
    );

    private static final List<String> SHAPES = List.of("block", "slab", "stairs");

    static {
        for (Material material : Material.values()) {
            CORE_VARIANTS.forEach(variant -> SHAPES.forEach(shape -> registerCoreBlock(material, variant, shape)));
        }
    }

    private static void registerCoreBlock(Material material, String variant, String shape) {
        String name = material.id + (variant != null ? "_" + variant : "") + "_" + shape;
        var properties = BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(material.color);

        var builder = Constants.JSGD_BLOCK_HELPER.builder(name)
                .clearTooltip()
                .setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB))
                .setProperties(properties);

        RegistryObject<Block> block = switch (shape) {
            case "slab" -> builder.buildSlab();
            case "stairs" -> builder.buildStairs(() ->
                    CORE_DECORATION_BLOCKS.get(material.id + "_" + variant + "_block").get().defaultBlockState()
            );
            default -> (name.contains("pillar") || name.contains("polished_tiles"))
                    ? builder.buildPillar(material.color, material.color)
                    : builder.buildGeneric();
        };

        CORE_DECORATION_BLOCKS.put(name, block);
    }

    /**
     * Atlantis Decoration Blocks
     */

    public static final Map<String, RegistryObject<Block>> ATLANTIS_BLOCKS = new LinkedHashMap<>();

    static {
        String[] materials = {"blue_atlantis", "blue_atlantis_lamp", "white", "white_lamp", "gray_atlantis_wall"};
        String[] variants = {"", "aged", "flooded"};
        String[] shapes = {"block", "slab", "stairs"};

        for (String material : materials) {
            for (String variant : variants) {
                for (String shape : shapes) {

                    String registryName = variant.isEmpty() ? material + "_" + shape : variant + "_" + material + "_" + shape;

                    var properties = BlockBehaviour.Properties.copy(Blocks.STONE);

                    if (material.contains("blue")) {
                        properties.mapColor(MapColor.COLOR_LIGHT_BLUE);
                    } else if (material.contains("white")) {
                        properties.mapColor(MapColor.SNOW);
                    } else if (material.contains("red")) {
                        properties.mapColor(MapColor.TERRACOTTA_RED);
                    } else {
                        properties.mapColor(MapColor.COLOR_LIGHT_GRAY);
                    }

                    if (material.contains("lamp")) {
                        properties.lightLevel((state) -> 15)
                                .noOcclusion();
                    }

                    var builder = Constants.JSGD_BLOCK_HELPER.builder(registryName)
                            .clearTooltip()
                            .setInTabs(List.of(CoreTabs.TAB_BUILDING_BLOCKS, JSGDecorTabs.JSGD_BUILDING_TAB))
                            .setProperties(properties);

                    RegistryObject<Block> blockForRegistry = switch (shape) {
                        case "slab" -> builder.buildSlab();
                        case "stairs" -> {
                            String baseBlockName = variant.isEmpty() ? material + "_block" : variant + "_" + material + "_block";
                            yield builder.buildStairs(() -> ATLANTIS_BLOCKS.get(baseBlockName).get().defaultBlockState());
                        }
                        default -> builder.buildGeneric();
                    };

                    ATLANTIS_BLOCKS.put(registryName, blockForRegistry);
                }
            }
        }
    }

    // Glass

    //Glass panes

    //Doors

    public static final RegistryObject<Block> BROWN_WALL_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("brown_wall_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> DENSELY_WRITTEN_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("densely_written_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> SPARSELY_WRITTEN_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("sparsely_written_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();


    public static final RegistryObject<Block> LEFT_GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_green_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_green_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("green_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> LEFT_RED_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_red_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_RED_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_red_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> LEFT_BLUE_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_blue_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_BLUE_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_blue_glass_block").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();

    //Prepared for glass panes
/*
    public static final RegistryObject<Block> LEFT_GREEN_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("left_green_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.GREEN);
    public static final RegistryObject<Block> RIGHT_GREEN_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("right_green_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.GREEN);
    public static final RegistryObject<Block> GREEN_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("green_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.GREEN);
    public static final RegistryObject<Block> LEFT_RED_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("left_red_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.RED);
    public static final RegistryObject<Block> RIGHT_RED_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("right_red_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.RED);
    public static final RegistryObject<Block> LEFT_BLUE_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("left_blue_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BLUE_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.BLUE);
    public static final RegistryObject<Block> RIGHT_BLUE_GLASS_PANE = Constants.JSGD_BLOCK_HELPER.builder("right_blue_glass_pane").clearTooltip().setInTabs(List.of(JSGDecorTabs.JSGD_BUILDING_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BLUE_STAINED_GLASS_PANE)).buildGlassPane(DyeColor.BLUE);

*/

    /**
     * SGU Decoration Blocks
     */

    public static void init() {
    }
}
