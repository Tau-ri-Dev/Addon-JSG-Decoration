package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import net.minecraft.Util;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Map;

public class JSGDecorBlockEntities {
    public static final Map<BrazierType, RegistryObject<BlockEntityType<BrazierBE>>> BRAZIERS = BrazierType.registerBEs();

    public static void init() {
        JSGDecor.REGISTRY_HELPER.beRenderers(() -> Util.make(new ArrayList<>(), (list) -> {
            BrazierType.registerBERs(list::add);
        }));
    }
}
