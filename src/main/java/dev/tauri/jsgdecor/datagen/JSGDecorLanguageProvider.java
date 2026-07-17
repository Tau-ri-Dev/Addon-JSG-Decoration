package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

import static dev.tauri.jsgdecor.common.registry.JSGDecorBlocks.*;

public class JSGDecorLanguageProvider extends LanguageProvider {

    @Override
    protected void addTranslations() {
        add("itemGroup.jsg_decor.building_blocks", "JSG Decor: Building Blocks");

        addMapTranslations(OVERLAY_BLOCKS);
        addMapTranslations(COMMON_BLOCKS);
        addMapTranslations(GLASS_BLOCKS);
        addMapTranslations(STONE_BASED_DECORATION_BLOCKS);
        addMapTranslations(WOOD_BLOCKS);


        for (BoatTypes boat : BoatTypes.values()) {
            add(boat.getDrop(false), createLocalizedName(boat.getName() + "_boat"));
            add(boat.getDrop(true), createLocalizedName(boat.getName()) + " Boat with Chest");
        }
        add("entity" + "." + JSGDecor.MOD_ID + "." + "boat", "Boat");
        add("entity" + "." + JSGDecor.MOD_ID + "." + "boat_with_chest", "Boat with Chest");

        for (var registryObject : JSGDecorBlocks.BRAZIERS.values()) {
            Block block = registryObject.get();
            var id = registryObject.getId();
            if (id != null) {
                add(block, createLocalizedName(id.getPath()));
            }
        }
    }

    private void addMapTranslations(Map<String, RegistryObject<Block>> blockMap) {
        for (Map.Entry<String, RegistryObject<Block>> entry : blockMap.entrySet()) {
            add(entry.getValue().get(), createLocalizedName(entry.getKey()));
        }
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