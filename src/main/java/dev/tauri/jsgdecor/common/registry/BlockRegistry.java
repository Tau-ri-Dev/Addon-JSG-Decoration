package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsg.api.block.util.IItemBlock;
import dev.tauri.jsg.api.item.ITabbedItem;
import dev.tauri.jsg.api.item.JSGBlockItem;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.common.block.BrazierBlock;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.worldgen.tree.Growers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

@SuppressWarnings("unused")
public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    /**
     * TREE BLOCKS
     */
    public static final RegistryObject<Block> LEMON_LOG_STRIPPED = Constants.JSGD_BLOCK_HELPER.builder("stripped_lemon_log").clearTooltip().setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_LOG = Constants.JSGD_BLOCK_HELPER.builder("lemon_log").clearTooltip().addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, LEMON_LOG_STRIPPED).setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_WOOD_STRIPPED = Constants.JSGD_BLOCK_HELPER.builder("stripped_lemon_wood").clearTooltip().setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_WOOD = Constants.JSGD_BLOCK_HELPER.builder("lemon_wood").clearTooltip().addToolStateModifierPillar(tool -> tool.getItem() instanceof AxeItem, LEMON_WOOD_STRIPPED).setFlammability(5).setFireSpreadSpeed(5).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)).buildPillar(MapColor.WOOD, MapColor.PODZOL);
    public static final RegistryObject<Block> LEMON_LEAVES = Constants.JSGD_BLOCK_HELPER.builder("lemon_leaves").clearTooltip().setFlammability(30).setFireSpreadSpeed(60).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion().mapColor(MapColor.COLOR_LIGHT_GREEN)).buildLeaves();
    public static final RegistryObject<Block> LEMON_SAPLING = Constants.JSGD_WOOD_HELPER.builder("lemon_sapling").clearTooltip().setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noCollission()).buildSapling(Growers.LEMON_TREE_GROWER);

    /**
     * WOOD & WOOD BLOCKS
     */

    public static final RegistryObject<Block> LEMON_PLANKS = Constants.JSGD_BLOCK_HELPER.builder("lemon_planks").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildGeneric();
    public static final RegistryObject<Block> LEMON_SLAB = Constants.JSGD_BLOCK_HELPER.builder("lemon_slab").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildSlab();
    public static final RegistryObject<Block> LEMON_STAIRS = Constants.JSGD_BLOCK_HELPER.builder("lemon_stairs").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildStairs(() -> LEMON_PLANKS.get().defaultBlockState());

    public static final RegistryObject<Block> LEMON_FENCE = Constants.JSGD_BLOCK_HELPER.builder("lemon_fence").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildFence();
    public static final RegistryObject<Block> LEMON_GATE = Constants.JSGD_BLOCK_HELPER.builder("lemon_fence_gate").clearTooltip().setFlammability(5).setFireSpreadSpeed(20).setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_LIGHT_GRAY)).buildFenceGate(() -> SoundEvents.FENCE_GATE_OPEN, () -> SoundEvents.FENCE_GATE_CLOSE);

    public static final RegistryObject<Block> LEMON_BUTTON = Constants.JSGD_BLOCK_HELPER.builder("lemon_button").clearTooltip().setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)).buildButton(BlockSetType.OAK, 30, true);
    public static final RegistryObject<Block> LEMON_PRESSURE_PLATE = Constants.JSGD_BLOCK_HELPER.builder("lemon_pressure_plate").clearTooltip().setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)).buildPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK);

    public static final RegistryObject<Block> LEMON_DOOR = Constants.JSGD_BLOCK_HELPER.builder("lemon_door").clearTooltip().setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion()).buildDoor(BlockSetType.OAK);
    public static final RegistryObject<Block> LEMON_TRAPDOOR = Constants.JSGD_BLOCK_HELPER.builder("lemon_trapdoor").clearTooltip().setInTabs(List.of(dev.tauri.jsg.registry.TabRegistry.TAB_BUILDING_BLOCKS, TabRegistry.JSGD_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion()).buildTrapdoor(BlockSetType.OAK);

    /**
     * Goauld Decoration Blocks
     */

    public static final Map<BrazierType, RegistryObject<BrazierBlock>> BRAZIERS = BrazierType.registerBlocks();

    /**
     * SGC Decoration Blocks
     */

    /**
     * Atlantis Decoration Blocks
     */
    public static final RegistryObject<Block> BLUE_ATLANTIS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("blue_atlantis_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> BLUE_ATLANTIS_LAMP_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("blue_atlantis_lamp_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)).buildGeneric();
    public static final RegistryObject<Block> CLEAR_WHITE_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("clear_white_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)).buildGeneric();
    public static final RegistryObject<Block> LIGHT_WALL_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("light_wall_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> STANDARD_WALL_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("standard_wall_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> AGED_WALL_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("aged_wall_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> BROWN_WALL_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("brown_wall_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> DENSELY_WRITTEN_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("densely_written_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> SPARSELY_WRITTEN_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("sparsely_written_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)).buildGeneric();
    public static final RegistryObject<Block> LEFT_GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_green_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_green_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> GREEN_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("green_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> LEFT_RED_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_red_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_RED_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_red_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> LEFT_BLUE_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("left_blue_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();
    public static final RegistryObject<Block> RIGHT_BLUE_GLASS_BLOCK = Constants.JSGD_BLOCK_HELPER.builder("right_blue_glass_block").clearTooltip().setInTabs(List.of(TabRegistry.JSGD_ATLANTIS_TAB)).setProperties(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)).buildGeneric();

    /**
     * SGU Decoration Blocks
     */

    public static void register(IEventBus bus) {
        for (RegistryObject<Block> i : REGISTER.getEntries().stream().toList()) {
            ItemRegistry.REGISTER.register(i.getId().getPath(),
                    () -> {
                        List<RegistryObject<CreativeModeTab>> tabs = List.of();
                        if (i.get() instanceof ITabbedItem t) {
                            tabs = t.getTabs();
                        }
                        if (i.get() instanceof IItemBlock itemBlock)
                            return itemBlock.getItemBlock();
                        return new JSGBlockItem(i.get(), new Item.Properties(), tabs);
                    });
        }
        REGISTER.register(bus);
    }
}
