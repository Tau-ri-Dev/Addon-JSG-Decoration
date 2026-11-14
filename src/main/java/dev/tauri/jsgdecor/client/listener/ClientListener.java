package dev.tauri.jsgdecor.client.listener;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.registry.JSGDecorEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = JSGDecor.MOD_ID, value = Dist.CLIENT)
public class ClientListener {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JSGDecorEntities.JSGD_BOAT.entity().get(), (ctx) -> JSGDecorEntities.JSGD_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
        event.registerEntityRenderer(JSGDecorEntities.JSGD_CHEST_BOAT.entity().get(), (ctx) -> JSGDecorEntities.JSGD_CHEST_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
    }
}
