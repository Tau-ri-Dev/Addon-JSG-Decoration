package dev.tauri.jsgdecor.common.boat;

import dev.tauri.jsg.entity.vehicle.JSGBoatTypeWrapper;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public enum BoatTypes implements JSGBoatTypeWrapper.Type, StringRepresentable {
    LEMON("lemon", () -> );

    public final String name;
    public final Supplier<Item> item;

    BoatTypes(String name, Supplier<Item> item){
        this.name = name;
        this.item = item;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Item getDrop() {
        return this.item.get();
    }

    public static final JSGBoatTypeWrapper<BoatTypes> JSGD_BOAT_TYPE_WRAPPER = new JSGBoatTypeWrapper<>(BoatTypes.LEMON, BoatTypes::values);
}
