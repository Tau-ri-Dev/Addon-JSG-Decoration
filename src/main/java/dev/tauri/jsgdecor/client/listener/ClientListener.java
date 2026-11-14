package dev.tauri.jsgdecor.client.listener;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.registry.JSGDecorEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JSGDecor.MOD_ID, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JSGDecorEntities.JSGD_BOAT.entity().get(), (ctx) -> JSGDecorEntities.JSGD_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
        event.registerEntityRenderer(JSGDecorEntities.JSGD_CHEST_BOAT.entity().get(), (ctx) -> JSGDecorEntities.JSGD_CHEST_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
    }
}
