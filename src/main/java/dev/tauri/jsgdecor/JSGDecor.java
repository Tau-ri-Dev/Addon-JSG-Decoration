package dev.tauri.jsgdecor;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(JSGDecor.MOD_ID)
public class JSGDecor {
    public static final String MOD_ID = "jsg_decor";
    public static final String MOD_NAME = "JSG: Decoration";
    public static Logger logger;

    public static String MOD_VERSION = "";
    public static final String MC_VERSION = "1.20.1";

    public JSGDecor() {
        logger = LoggerFactory.getLogger(MOD_NAME);

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
        });
        JSGDecor.logger.info("Loading JSG:Decoration Addon version {}", JSGDecor.MOD_VERSION);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Constants.load();
    }
}
