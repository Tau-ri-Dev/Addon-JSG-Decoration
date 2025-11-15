package dev.tauri.jsgdecor.common.registry;

import dev.tauri.jsgdecor.JSGDecor;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JSGDecor.MOD_ID);

    public static final Map<BrazierType, RegistryObject<BlockEntityType<BrazierBE>>> BRAZIERS = BrazierType.registerBEs();


    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<T> beSupplier, Supplier<? extends Block> blockSupplier) {
        return registerBE(name, beSupplier, List.of(blockSupplier));
    }

    @SuppressWarnings("all")
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBE(String name, BlockEntityType.BlockEntitySupplier<T> beSupplier, List<? extends Supplier<? extends Block>> blockSuppliers) {
        return REGISTER.register(name, () -> {
            List<Block> blocks = new ArrayList<>();
            for (var object : blockSuppliers) {
                blocks.add(object.get());
            }
            return BlockEntityType.Builder.of(beSupplier, blocks.toArray(new Block[0])).build(null);
        });
    }


    public static void register(IEventBus bus) {
        REGISTER.register(bus);
        bus.addListener(BlockEntityRegistry::registerBERs);
    }

    @SubscribeEvent
    public static void registerBERs(EntityRenderersEvent.RegisterRenderers event) {
        BrazierType.registerBERs(event);
    }
}
