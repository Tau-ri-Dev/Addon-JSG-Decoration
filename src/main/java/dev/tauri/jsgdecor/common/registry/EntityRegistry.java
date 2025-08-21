package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsg.helpers.registry.entity.EntityRegistryHelperBoat;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@SuppressWarnings("unused")
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JSGDecor.MOD_ID);

    public static final EntityRegistryHelperBoat.BoatRegistryObject JSGD_BOAT = ((EntityRegistryHelperBoat.BoatEntityBuilder) Constants.JSGD_BOAT_HELPER.builder("boat").setInTabs(List.of(TabRegistry.JSGD_TAB))).setProperties(new Item.Properties()).buildBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);
    public static final EntityRegistryHelperBoat.ChestBoatRegistryObject JSGD_CHEST_BOAT = ((EntityRegistryHelperBoat.BoatEntityBuilder) Constants.JSGD_BOAT_HELPER.builder("boat_with_chest").setInTabs(List.of(TabRegistry.JSGD_TAB))).setProperties(new Item.Properties()).buildChestBoats(BoatTypes.JSGD_BOAT_TYPE_WRAPPER);

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
