package dev.tauri.jsgdecor.common.listener;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.WoodBlock;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = JSGDecor.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModListener {
    @SubscribeEvent
    public static void registerColoredBlocks(RegisterColorHandlersEvent.Block event) {
        Block[] leaves = getAllLeavesBlocks();

        if (leaves.length > 0) {
            event.register((blockState, level, pos, tintIndex) ->
                            level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
                    leaves
            );
        }
    }

    @SubscribeEvent
    public static void registerColoredBlocks(RegisterColorHandlersEvent.Item event) {
        Block[] leaves = getAllLeavesBlocks();

        if (leaves.length > 0) {
            event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), leaves);
        }
    }

    private static Block[] getAllLeavesBlocks() {
        List<Block> leavesList = new ArrayList<>();

        for (WoodBlock.Material mat : WoodBlock.Material.values()) {
            String leavesKey = mat.getMaterial() + "_leaves";
            RegistryObject<Block> leavesObj = JSGDecorBlocks.WOOD_BLOCKS.get(leavesKey);

            if (leavesObj != null) {
                leavesList.add(leavesObj.get());
            }
        }

        return leavesList.toArray(new Block[0]);
    }


}
