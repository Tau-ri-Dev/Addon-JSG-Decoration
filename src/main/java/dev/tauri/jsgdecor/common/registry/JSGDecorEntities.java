package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsg.core.common.registry.helper.RegistryHelper;
import dev.tauri.jsg.core.common.registry.helper.builder.entity.EntityRegistryHelperBoat;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;

import java.util.List;


public class JSGDecorEntities {

    public static final EntityRegistryHelperBoat.BoatRegistryObject JSGD_BOAT = ((EntityRegistryHelperBoat.BoatEntityBuilder) Constants.JSGD_BOAT_HELPER.builder("boat").setInTabs(List.of(JSGDecorTabs.JSGD_TAB))).setProperties(new Item.Properties()).buildBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);
    public static final EntityRegistryHelperBoat.ChestBoatRegistryObject JSGD_CHEST_BOAT = ((EntityRegistryHelperBoat.BoatEntityBuilder) Constants.JSGD_BOAT_HELPER.builder("boat_with_chest").setInTabs(List.of(JSGDecorTabs.JSGD_TAB))).setProperties(new Item.Properties()).buildChestBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);

    public static void init() {
        JSGDecor.REGISTRY_HELPER.entityRendererRegister(() -> {
           // RegistryHelper.registerEntityRenderer((EntityType<Boat>) JSGDecorEntities.JSGD_BOAT.entity().get(),(ctx) -> JSGDecorEntities.JSGD_BOAT.rendererProvider().apply(JSGDecor.MOD_ID, ctx));
        });
    }
}
