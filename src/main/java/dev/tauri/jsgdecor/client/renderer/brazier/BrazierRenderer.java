package dev.tauri.jsgdecor.client.renderer.brazier;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.tauri.jsg.loader.model.OBJModel;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import javax.annotation.ParametersAreNonnullByDefault;

public class BrazierRenderer implements BlockEntityRenderer<BrazierBE> {
    public BrazierRenderer(BlockEntityRendererProvider.Context ignored) {
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(BrazierBE be, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int light, int overlay) {
        var level = be.getLevel();
        if (level == null) return;
        var state = level.getBlockState(be.getBlockPos());
        if (state.getOptionalValue(BlockStateProperties.DOUBLE_BLOCK_HALF).orElse(null) != DoubleBlockHalf.LOWER)
            return;
        var topState = level.getBlockState(be.getBlockPos().above());

        stack.pushPose();
        OBJModel.packedLight = light;
        OBJModel.source = bufferSource;

        var type = be.type;

        stack.translate(type.translation.x, type.translation.y, type.translation.z);
        stack.scale(type.scale, type.scale, type.scale);
        ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(type.texture)).bindTexture();
        ClientConstants.LOADERS_HOLDER.model().getModel(ClientConstants.LOADERS_HOLDER.model().getModelResource(type.model)).render(stack);

        stack.pushPose();
        stack.translate(type.coalTranslation.x, type.coalTranslation.y, type.coalTranslation.z);
        stack.scale(type.coalScale, type.coalScale, type.coalScale);
        if (topState.getOptionalValue(BlockStateProperties.LIT).orElse(false)) {
            ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(type.coalTextureLit)).bindTexture();
            if (level.getGameTime() % 5 == 0 && level.getRandom().nextBoolean()) {
                level.addParticle(
                        ParticleTypes.FLAME,
                        type.flamesPosition.x + be.getBlockPos().getX() + (level.getRandom().nextFloat() * (type.flamesSpreadXZ * 2f) - type.flamesSpreadXZ),
                        type.flamesPosition.y + be.getBlockPos().getY() + (level.getRandom().nextFloat() * type.flamesSpreadY),
                        type.flamesPosition.z + be.getBlockPos().getZ() + (level.getRandom().nextFloat() * (type.flamesSpreadXZ * 2f) - type.flamesSpreadXZ),
                        0, 0.001, 0
                );
            }
            if (level.getGameTime() % 10 == 0 && level.getRandom().nextFloat() < 0.15f) {
                level.addParticle(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        type.flamesPosition.x + be.getBlockPos().getX() + (level.getRandom().nextFloat() * (type.flamesSpreadXZ * 2f) - type.flamesSpreadXZ),
                        type.flamesPosition.y + 0.2 + be.getBlockPos().getY() + (level.getRandom().nextFloat() * type.flamesSpreadY),
                        type.flamesPosition.z + be.getBlockPos().getZ() + (level.getRandom().nextFloat() * (type.flamesSpreadXZ * 2f) - type.flamesSpreadXZ),
                        0, 0.03, 0
                );
            }
        } else
            ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(type.coalTexture)).bindTexture();
        ClientConstants.LOADERS_HOLDER.model().getModel(ClientConstants.LOADERS_HOLDER.model().getModelResource(type.coalModel)).render(stack);
        stack.popPose();

        stack.popPose();
    }
}
