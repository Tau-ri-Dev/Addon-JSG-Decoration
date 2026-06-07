package dev.tauri.jsgdecor.datagen;

import dev.tauri.jsgdecor.JSGDecor;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JSGDecorBlockStateProvider extends BlockStateProvider {
    public JSGDecorBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JSGDecor.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }

    public static ResourceLocation getRL(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
