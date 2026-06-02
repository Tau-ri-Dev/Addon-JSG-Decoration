package dev.tauri.jsgdecor;


import dev.tauri.jsg.core.common.registry.helper.builder.block.BlockRegistryHelperGeneric;
import dev.tauri.jsg.core.common.registry.helper.builder.block.BlockRegistryHelperWood;
import dev.tauri.jsg.core.common.registry.helper.builder.entity.EntityRegistryHelperBoat;
import dev.tauri.jsg.core.common.registry.helper.builder.item.ItemRegistryHelperGeneric;


public class Constants {
    public static final BlockRegistryHelperGeneric JSGD_BLOCK_HELPER = new BlockRegistryHelperGeneric(JSGDecor.REGISTRY_HELPER::block);
    public static final BlockRegistryHelperWood JSGD_WOOD_HELPER = new BlockRegistryHelperWood(JSGDecor.REGISTRY_HELPER::block);

    public static final ItemRegistryHelperGeneric JSGD_ITEM_HELPER = new ItemRegistryHelperGeneric(JSGDecor.REGISTRY_HELPER::item);

    public static final EntityRegistryHelperBoat JSGD_BOAT_HELPER = new EntityRegistryHelperBoat(JSGDecor.REGISTRY_HELPER::item, JSGDecor.REGISTRY_HELPER::entity);
}
