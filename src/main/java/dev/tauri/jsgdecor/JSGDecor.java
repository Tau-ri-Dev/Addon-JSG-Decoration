package dev.tauri.jsgdecor;

import dev.tauri.jsg.core.JSGAddon;
import dev.tauri.jsg.core.JSGAddons;
import dev.tauri.jsg.core.LoggerWrapper;
import dev.tauri.jsg.core.common.registry.helper.RegistryHelper;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.injectors.JSGDecorTemplatePoolInjectors;
import dev.tauri.jsgdecor.common.registry.JSGDecorEntities;
import dev.tauri.jsgdecor.common.registry.JSGDecorRegistriesInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
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

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(JSGDecor.MOD_ID);

    public JSGDecor() {
        logger = new LoggerWrapper("[jsg_decor] ", LoggerFactory.getLogger(MOD_NAME));

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
        });
        JSGDecor.logger.info("Loading JSG:Decoration Addon version {}", JSGDecor.MOD_VERSION);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        JSGDecorRegistriesInit.init();
        JSGDecorRegistriesInit.register(modEventBus);

        JSGDecorTemplatePoolInjectors.register();

        JSGAddons.registerAddon(this);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(
                    JSGDecorEntities.JSGD_BOAT.entity().get(),
                    (ctx) -> JSGDecorEntities.JSGD_BOAT.rendererProvider().apply(MOD_ID, ctx)
            );

            event.registerEntityRenderer(
                    JSGDecorEntities.JSGD_CHEST_BOAT.entity().get(),
                    (ctx) -> JSGDecorEntities.JSGD_CHEST_BOAT.rendererProvider().apply(MOD_ID, ctx)
            );
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

    @Override
    public void onJSGCoreLoad() {
        ClientConstants.load();
    }
}
