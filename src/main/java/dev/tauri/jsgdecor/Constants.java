package dev.tauri.jsgdecor;

import dev.tauri.jsg.helpers.registry.block.BlockRegistryHelperGeneric;
import dev.tauri.jsg.helpers.registry.block.BlockRegistryHelperWood;
import dev.tauri.jsg.helpers.registry.entity.EntityRegistryHelperBoat;
import dev.tauri.jsg.helpers.registry.item.ItemRegistryHelperGeneric;
import dev.tauri.jsgdecor.common.registry.BlockRegistry;
import dev.tauri.jsgdecor.common.registry.ItemRegistry;

public class Constants {
    public static final BlockRegistryHelperGeneric JSGD_BLOCK_HELPER = new BlockRegistryHelperGeneric(() -> BlockRegistry.REGISTER);
    public static final BlockRegistryHelperWood JSGD_WOOD_HELPER = new BlockRegistryHelperWood(() -> BlockRegistry.REGISTER);

    public static final ItemRegistryHelperGeneric JSGD_ITEM_HELPER = new ItemRegistryHelperGeneric(() -> ItemRegistry.REGISTER);

    public static final EntityRegistryHelperBoat JSGD_BOAT_HELPER = new EntityRegistryHelperBoat(() ->  );


    public static void load() {
    }
}
