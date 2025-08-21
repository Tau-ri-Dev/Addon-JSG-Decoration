package dev.tauri.jsgdecor.common.registry;


import dev.tauri.jsg.entity.vehicle.JSGBoat;
import dev.tauri.jsg.entity.vehicle.JSGChestBoat;
import dev.tauri.jsgdecor.Constants;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JSGDecor.MOD_ID);

    public static final RegistryObject<EntityType<? extends JSGBoat<?>>> LEMON_BOAT_ENTITY = Constants.JSGD_BOAT_HELPER.builder("lemon boat").setBoatTypeWrapper(BoatTypes.JSGD_BOAT_TYPE_WRAPPER).buildBoat();
    public static final RegistryObject<EntityType<? extends JSGChestBoat<?>>> LEMON_CHEST_BOAT_ENTITY = Constants.JSGD_BOAT_HELPER.builder("lemon chest boat").setBoatTypeWrapper(BoatTypes.JSGD_BOAT_TYPE_WRAPPER).buildChestBoat();

    public static void register(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
