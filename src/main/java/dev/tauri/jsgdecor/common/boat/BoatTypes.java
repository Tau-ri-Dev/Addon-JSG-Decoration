package dev.tauri.jsgdecor.common.boat;


import dev.tauri.jsg.core.common.entity.vehicle.JSGBoatTypeWrapper;
import dev.tauri.jsgdecor.common.registry.JSGDecorBlocks;
import dev.tauri.jsgdecor.common.registry.JSGDecorEntities;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum BoatTypes implements JSGBoatTypeWrapper.Type, StringRepresentable {
    LEMON("lemon", () -> JSGDecorBlocks.WOOD_BLOCKS.get("lemon_planks").get());

    public final String name;
    public final Supplier<Block> materialSupplier;

    BoatTypes(String name, Supplier<Block> materialSupplier) {
        this.name = name;
        this.materialSupplier = materialSupplier;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name.toLowerCase();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Block getMaterial() {
        return this.materialSupplier.get();
    }

    @Override
    public Item getDrop(boolean withChest) {
        if(withChest)
            return JSGDecorEntities.JSGD_CHEST_BOAT.item().get(this).get();
        return JSGDecorEntities.JSGD_BOAT.item().get(this).get();
    }

    public static final JSGBoatTypeWrapper<BoatTypes> JSGD_BOAT_TYPE_WRAPPER = new JSGBoatTypeWrapper<>(BoatTypes.LEMON, BoatTypes::values);
}
