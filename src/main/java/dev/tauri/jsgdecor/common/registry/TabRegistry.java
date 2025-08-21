package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgdecor.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static dev.tauri.jsgdecor.JSGDecor.MOD_ID;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> JSGD_TAB = Constants.JSGD_TAB_HELPER.createCreativeTabWithItemStack("main", () -> new ItemStack(BlockRegistry.LEMON_LOG.get()));

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    /**
     * Handling vanilla recipes hook
     */
    @SubscribeEvent
    public static void buildTabsContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (var type : EntityRegistry.JSGD_BOAT.item().values())
                event.accept(type);
            for (var type : EntityRegistry.JSGD_CHEST_BOAT.item().values())
                event.accept(type);
        }
    }
}
