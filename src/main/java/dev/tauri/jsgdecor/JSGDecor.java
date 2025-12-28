package dev.tauri.jsgdecor;

import dev.tauri.jsg.api.JSGAddon;
import dev.tauri.jsg.api.JSGApi;
import dev.tauri.jsg.api.LoggerWrapper;
import dev.tauri.jsgdecor.common.registry.*;
import dev.tauri.jsgdecor.common.worldgen.StructuresInjector;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(JSGDecor.MOD_ID)
public class JSGDecor implements JSGAddon {
    public static final String MOD_ID = "jsg_decor";
    public static final String MOD_NAME = "JSG: Decoration";
    public static Logger logger;

    public static String MOD_VERSION = "";
    public static final String MC_VERSION = "1.20.1";

    public JSGDecor() {
        logger = new LoggerWrapper("[jsg decor] ", LoggerFactory.getLogger(MOD_NAME));

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
        });
        JSGDecor.logger.info("Loading JSG:Decoration Addon version {}", JSGDecor.MOD_VERSION);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Constants.load();

        MinecraftForge.EVENT_BUS.register(this);

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        TabRegistry.register(modEventBus);
        EntityRegistry.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);

        StructuresInjector.register();

        JSGApi.registerAddon(this);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityRegistry.JSGD_BOAT.entity().get(), (ctx) -> EntityRegistry.JSGD_BOAT.rendererProvider().apply(MOD_ID, ctx));
            EntityRenderers.register(EntityRegistry.JSGD_CHEST_BOAT.entity().get(), (ctx) -> EntityRegistry.JSGD_CHEST_BOAT.rendererProvider().apply(MOD_ID, ctx));
        }
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getVersion() {
        return MOD_VERSION;
    }
}
