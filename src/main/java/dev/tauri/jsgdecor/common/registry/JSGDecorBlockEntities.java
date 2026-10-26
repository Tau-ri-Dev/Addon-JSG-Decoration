package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import net.minecraft.Util;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.Map;

public class JSGDecorBlockEntities {
    public static final Map<BrazierType, RegistryObjectWrapper<BlockEntityType<BrazierBE>>> BRAZIERS = BrazierType.registerBEs();

    public static void init() {
        JSGDecor.REGISTRY_HELPER.beRenderers(() -> Util.make(new ArrayList<>(), (list) -> {
            BrazierType.registerBERs(list::add);
        }));
    }
}
