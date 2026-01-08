package dev.tauri.jsgdecor;

import dev.tauri.jsg.helpers.TabHelper;
import dev.tauri.jsg.helpers.registry.block.BlockRegistryHelperGeneric;
import dev.tauri.jsg.helpers.registry.block.BlockRegistryHelperWood;
import dev.tauri.jsg.helpers.registry.entity.EntityRegistryHelperBoat;
import dev.tauri.jsg.helpers.registry.item.ItemRegistryHelperGeneric;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.registry.BlockRegistry;
import dev.tauri.jsgdecor.common.registry.EntityRegistry;
import dev.tauri.jsgdecor.common.registry.ItemRegistry;
import dev.tauri.jsgdecor.common.registry.TabRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class Constants {
    public static final BlockRegistryHelperGeneric JSGD_BLOCK_HELPER = new BlockRegistryHelperGeneric(() -> BlockRegistry.REGISTER);
    public static final BlockRegistryHelperWood JSGD_WOOD_HELPER = new BlockRegistryHelperWood(() -> BlockRegistry.REGISTER);

    public static final ItemRegistryHelperGeneric JSGD_ITEM_HELPER = new ItemRegistryHelperGeneric(() -> ItemRegistry.REGISTER);
    public static final TabHelper JSGD_TAB_HELPER = new TabHelper(() -> TabRegistry.REGISTER);

    public static final EntityRegistryHelperBoat JSGD_BOAT_HELPER = new EntityRegistryHelperBoat(() -> ItemRegistry.REGISTER, () -> EntityRegistry.REGISTER);
}
