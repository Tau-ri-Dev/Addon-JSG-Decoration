package dev.tauri.jsgdecor.common.boat;

import dev.tauri.jsg.entity.vehicle.JSGBoatTypeWrapper;
import dev.tauri.jsgdecor.common.registry.EntityRegistry;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public enum BoatTypes implements JSGBoatTypeWrapper.Type, StringRepresentable {
    LEMON("lemon");

    public final String name;

    BoatTypes(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Item getDrop(boolean withChest) {
        if(withChest)
            return EntityRegistry.JSGD_CHEST_BOAT.item().get(this).get();
        return EntityRegistry.JSGD_BOAT.item().get(this).get();
    }

    public static final JSGBoatTypeWrapper<BoatTypes> JSGD_BOAT_TYPE_WRAPPER = new JSGBoatTypeWrapper<>(BoatTypes.LEMON, BoatTypes::values);
}
