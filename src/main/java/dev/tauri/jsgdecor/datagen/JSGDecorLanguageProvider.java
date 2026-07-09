package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class JSGDecorLanguageProvider extends LanguageProvider {

    @Override
    protected void addTranslations() {
        //Tabs
        add("itemGroup.jsg_decor.building_blocks", "JSG Decor: Building Blocks");

        //Lemon wood and products
        add("block.jsg_decor.lemon_log", "Lemon Log");
        add("block.jsg_decor.stripped_lemon_log", "Stripped Lemon Log");
        add("block.jsg_decor.lemon_wood", "Lemon Wood");
        add("block.jsg_decor.stripped_lemon_wood", "Stripped Lemon Wood");
        add("block.jsg_decor.lemon_planks", "Lemon Planks");
        add("block.jsg_decor.lemon_stairs", "Lemon Stairs");
        add("block.jsg_decor.lemon_slab", "Lemon Slab");
        add("block.jsg_decor.lemon_fence", "Lemon Fence");
        add("block.jsg_decor.lemon_fence_gate", "Lemon Fence Gate");
        add("block.jsg_decor.lemon_door", "Lemon Door");
        add("block.jsg_decor.lemon_trapdoor", "Lemon Trapdoor");
        add("block.jsg_decor.lemon_pressure_plate", "Lemon Pressure Plate");
        add("block.jsg_decor.lemon_button", "Lemon Button");
        add("block.jsg_decor.lemon_leaves", "Lemon Leaves");
        add("block.jsg_decor.lemon_sapling", "Lemon Sapling");
        add("item.jsg_decor.lemon_boat", "Lemon Boat");
        add("item.jsg_decor.lemon_boat_with_chest", "Lemon Boat with Chest");

        //Braziers
        for (var registryObject : JSGDecorBlocks.BRAZIERS.values()) {
            Block block = registryObject.get();
            net.minecraft.resources.ResourceLocation location = net.minecraftforge.registries.ForgeRegistries.BLOCKS.getKey(block);

            if (location != null) {
                String registryName = location.getPath();
                String localizedName = createLocalizedName(registryName);
                add(block, localizedName);
            }
        }

        // Core Decoration Blocks
        for (Map.Entry<String, RegistryObject<Block>> entry : JSGDecorBlocks.CORE_DECORATION_BLOCKS.entrySet()) {
            String registryName = entry.getKey();
            Block block = entry.getValue().get();

            String localizedName = createLocalizedName(registryName);
            add(block, localizedName);
        }

        //Atlantis Decoration Blocks
    }

    private static String createLocalizedName(String registryName) {
        String cleanName = registryName.replace("_", " ").trim().toLowerCase();

        String capitalizedName = java.util.regex.Pattern.compile("\\b([a-z])")
                .matcher(cleanName)
                .replaceAll(match -> match.group(1).toUpperCase());

        return capitalizedName.replace("Goauld", "Goa'uld")
                              .replace("Hatak", "Ha'tak");
    }

    public JSGDecorLanguageProvider(PackOutput output, String local) {
        super(output, "jsg_decor", local);
    }
}