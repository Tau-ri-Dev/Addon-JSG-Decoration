package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsg.block.IItemBlock;
import dev.tauri.jsg.item.ITabbedItem;
import dev.tauri.jsg.item.JSGBlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

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
