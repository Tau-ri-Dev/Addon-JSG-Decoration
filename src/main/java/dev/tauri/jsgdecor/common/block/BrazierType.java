package dev.tauri.jsgdecor.common.block;

import dev.tauri.jsgdecor.client.renderer.brazier.BrazierRenderer;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import dev.tauri.jsgdecor.common.registry.BlockEntityRegistry;
import dev.tauri.jsgdecor.common.registry.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum BrazierType {
    ABYDOS("abydos_brazier",
            "braziers/brazier_type_1.obj", "braziers/brazier_abydos.png",
            "braziers/brazier_type_1_coal.obj", "braziers/brazier_coal_off.png", "braziers/brazier_coal_on.png",
            new Vec3(0.44, 0, 0.5), 0.015f,
            new Vec3(0, 0, 0), 1f,
            new Vec3(0.5, 1.8, 0.5), 0.15f, 0.05f,
            () -> Shapes.box(0.2, 0, 0.2, 0.8, 1.8, 0.8)
    ),
    HATAK("hatak_brazier",
            "braziers/brazier_type_1.obj", "braziers/brazier_hatak.png",
            "braziers/brazier_type_1_coal.obj", "braziers/brazier_coal_off.png", "braziers/brazier_coal_on.png",
            new Vec3(0.44, 0, 0.5), 0.015f,
            new Vec3(0, 0, 0), 1f,
            new Vec3(0.5, 1.8, 0.5), 0.15f, 0.05f,
            () -> Shapes.box(0.2, 0, 0.2, 0.8, 1.8, 0.8)
    ),

    ANUBIS("anubis_brazier",
            "braziers/brazier_type_2.obj", "braziers/brazier_anubis.png",
            "braziers/brazier_type_2_coal.obj", "braziers/brazier_coal_off.png", "braziers/brazier_coal_on.png",
            new Vec3(0.5, 0, 0.5), 0.015f,
            new Vec3(0, 0.02, 0), 1f,
            new Vec3(0.5, 1.75, 0.5), 0.1f, 0.05f,
            () -> Shapes.box(0.2, 0, 0.2, 0.8, 1.8, 0.8)
    ),
    GOAULD("goauld_brazier",
            "braziers/brazier_type_2.obj", "braziers/brazier_goauld.png",
            "braziers/brazier_type_2_coal.obj", "braziers/brazier_coal_off.png", "braziers/brazier_coal_on.png",
            new Vec3(0.5, 0, 0.5), 0.015f,
            new Vec3(0, 0.02, 0), 1f,
            new Vec3(0.5, 1.75, 0.5), 0.1f, 0.05f,
            () -> Shapes.box(0.2, 0, 0.2, 0.8, 1.8, 0.8)
    );

    public final String id;
    public final String model;
    public final String texture;
    public final String coalModel;
    public final String coalTexture;
    public final String coalTextureLit;
    public final Vec3 translation;
    public final float scale;
    public final Vec3 coalTranslation;
    public final float coalScale;
    public final Vec3 flamesPosition;
    public final float flamesSpreadXZ;
    public final float flamesSpreadY;
    public final Supplier<VoxelShape> blockShape;

    private RegistryObject<BrazierBlock> block;
    private RegistryObject<BlockEntityType<BrazierBE>> blockEntity;

    BrazierType(String id, String model, String texture, String coalModel, String coalTexture, String coalTextureLit, Vec3 translation, float scale, Vec3 coalTranslation, float coalScale, Vec3 flamesPosition, float flamesSpreadXZ, float flamesSpreadY, Supplier<VoxelShape> blockShape) {
        this.id = id;
        this.model = model;
        this.texture = texture;
        this.coalModel = coalModel;
        this.coalTexture = coalTexture;
        this.coalTextureLit = coalTextureLit;
        this.translation = translation;
        this.scale = scale;
        this.coalTranslation = coalTranslation;
        this.coalScale = coalScale;
        this.flamesPosition = flamesPosition;
        this.flamesSpreadXZ = flamesSpreadXZ;
        this.flamesSpreadY = flamesSpreadY;
        this.blockShape = blockShape;
    }

    public RegistryObject<BrazierBlock> block() {
        return block;
    }

    public RegistryObject<BlockEntityType<BrazierBE>> blockEntity() {
        return blockEntity;
    }


    public static Map<BrazierType, RegistryObject<BrazierBlock>> registerBlocks() {
        var map = new HashMap<BrazierType, RegistryObject<BrazierBlock>>();
        for (var type : values()) {
            type.block = BlockRegistry.REGISTER.register(type.id, () -> new BrazierBlock(
                    BlockBehaviour.Properties.copy(dev.tauri.jsg.registry.BlockRegistry.NAQUADAH_BLOCK.get())
                            .noOcclusion()
                            .lightLevel((state) -> (state.getOptionalValue(BlockStateProperties.LIT).orElse(false) && state.getOptionalValue(BlockStateProperties.DOUBLE_BLOCK_HALF).orElse(DoubleBlockHalf.LOWER) == DoubleBlockHalf.UPPER) ? 15 : 0)
                            .isRedstoneConductor((pState, pLevel, pPos) -> false),
                    type, (pos, state) -> new BrazierBE(pos, state, type, type.blockEntity())));
            map.put(type, type.block);
        }
        return map;
    }

    public static Map<BrazierType, RegistryObject<BlockEntityType<BrazierBE>>> registerBEs() {
        var map = new HashMap<BrazierType, RegistryObject<BlockEntityType<BrazierBE>>>();
        for (var type : values()) {
            type.blockEntity = BlockEntityRegistry.registerBE(type.id, (pos, state) -> new BrazierBE(pos, state, type, () -> map.get(type).get()), type.block());
            map.put(type, type.blockEntity);
        }
        return map;
    }

    public static void registerBERs(EntityRenderersEvent.RegisterRenderers event) {
        for (var type : values()) {
            event.registerBlockEntityRenderer(type.blockEntity.get(), BrazierRenderer::new);
        }
    }
}
