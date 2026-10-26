package dev.tauri.jsgdecor;

import dev.tauri.jsgcore.JSGAddon;
import dev.tauri.jsgcore.JSGAddons;
import dev.tauri.jsgcore.LoggerWrapper;
import dev.tauri.jsgcore.common.registry.helper.RegistryHelper;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.injectors.JSGDecorTemplatePoolInjectors;
import dev.tauri.jsgdecor.common.registry.JSGDecorEntities;
import dev.tauri.jsgdecor.common.registry.JSGDecorRegistriesInit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Mod(JSGDecor.MOD_ID)
public class JSGDecor implements JSGAddon {
    public static final String MOD_ID = "jsg_decor";
    public static final String MOD_NAME = "JSG: Decoration";
    public static LoggerWrapper logger;

    public static String MOD_VERSION = "";
    public static final String MC_VERSION = "1.20.1";

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(JSGDecor.MOD_ID);

    public JSGDecor(IEventBus eventBus) {
        logger = new LoggerWrapper("[jsg_decor] ", LoggerFactory.getLogger(MOD_NAME));

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(
                container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
                });
        JSGDecor.logger.info("Loading JSG:Decoration Addon version {}", JSGDecor.MOD_VERSION);

        //NeoForge.EVENT_BUS.register(this);

        JSGDecorRegistriesInit.init();
        JSGDecorRegistriesInit.register(eventBus);

        JSGDecorEntities.init();

        JSGDecorTemplatePoolInjectors.register();

        JSGAddons.registerAddon(this);
    }

    @Override
    public @NotNull String getId() {
        return MOD_ID;
    }

    @Override
    public String @NotNull [] getWelcomeLogo() {
        return new String[]{
                "░░░░░██╗░██████╗░██████╗░██╗██████╗░███████╗░█████╗░░█████╗░██████╗░░█████╗░████████╗██╗░█████╗░███╗░░██╗",
                "░░░░░██║██╔════╝██╔════╝░╚═╝██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║",
                "░░░░░██║╚█████╗░██║░░██╗░░░░██║░░██║█████╗░░██║░░╚═╝██║░░██║██████╔╝███████║░░░██║░░░██║██║░░██║██╔██╗██║",
                "██╗░░██║░╚═══██╗██║░░╚██╗░░░██║░░██║██╔══╝░░██║░░██╗██║░░██║██╔══██╗██╔══██║░░░██║░░░██║██║░░██║██║╚████║",
                "╚█████╔╝██████╔╝╚██████╔╝██╗██████╔╝███████╗╚█████╔╝╚█████╔╝██║░░██║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║",
                "░╚════╝░╚═════╝░░╚═════╝░╚═╝╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝"
        };
    }

    @Override
    @NotNull
    public Optional<LoggerWrapper> getLoggerWrapper() {
        return Optional.of(logger);
    }

    @Override
    public void onJSGCoreLoad() {
        ClientConstants.load();
    }
}
