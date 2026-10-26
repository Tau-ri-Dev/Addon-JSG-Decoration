package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsgcore.common.registry.helper.builder.entity.EntityRegistryHelperBoat;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = JSGDecor.MOD_ID, value = Dist.CLIENT)
public class JSGDecorEntities {

    public static final EntityRegistryHelperBoat.BoatRegistryObject JSGD_BOAT = Constants.JSGD_BOAT_HELPER.builder("boat").setProperties(new Item.Properties()).buildBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);
    public static final EntityRegistryHelperBoat.ChestBoatRegistryObject JSGD_CHEST_BOAT = Constants.JSGD_BOAT_HELPER.builder("boat_with_chest").setProperties(new Item.Properties()).buildChestBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(JSGD_BOAT.entity().get(), (ctx) -> JSGD_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
        event.registerEntityRenderer(JSGD_CHEST_BOAT.entity().get(), (ctx) -> JSGD_CHEST_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
    }

    public static void init() {
        JSGDecor.REGISTRY_HELPER.entityRendererRegister(() -> {});
    }
}
