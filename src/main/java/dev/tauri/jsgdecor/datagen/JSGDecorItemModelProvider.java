package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.boat.BoatTypes;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class JSGDecorItemModelProvider extends ItemModelProvider {
    public JSGDecorItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JSGDecor.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (BoatTypes type : BoatTypes.values()) {
            String woodName = type.getSerializedName();
            boatModel(woodName);
        }
    }

    private void boatModel(String woodName) {
        withExistingParent(woodName + "_boat", "minecraft:item/generated")
                .texture("layer0", JSGMapping.rl(JSGDecor.MOD_ID, "item/wood/"+ woodName + "/" + woodName + "_boat"));

        withExistingParent(woodName + "_boat_with_chest", "minecraft:item/generated")
                .texture("layer0", JSGMapping.rl(JSGDecor.MOD_ID, "item/wood/"+ woodName + "/" + woodName + "_boat_with_chest"));
    }
}
