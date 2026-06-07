package dev.tauri.jsgdecor.client.renderer.brazier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsg.core.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.item.BrazierItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BrazierBEWLR extends AbstractItemBEWLR {
    @Override
    public void renderItem(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, float partialTick) {
        var item = itemStack.getItem();
        if (!(item instanceof BrazierItem brazier)) return;
        var type = brazier.type;

        poseStack.pushPose();
        if (itemDisplayContext == ItemDisplayContext.GUI) {
            poseStack.translate(0, 0.13, 0);
        } else if (itemDisplayContext == ItemDisplayContext.FIXED) {
            poseStack.translate(0.5, 0.3, 0.15);
        } else {
            if (itemDisplayContext.firstPerson()) {
                poseStack.scale(0.4f, 0.4f, 0.4f);
                poseStack.translate(0.15,-0.45,1.55);
                poseStack.mulPose(Axis.YP.rotationDegrees(-30));
            } else if (itemDisplayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || itemDisplayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND) {
                poseStack.translate(0f, -0.8f, 0.6f);
                poseStack.scale(0.5f, 0.5f, 0.5f);
            }
        }

        if (itemDisplayContext == ItemDisplayContext.GROUND)
            poseStack.scale(0.4f, 0.4f,0.4f);
        else
            poseStack.scale(0.6f, 0.6f, 0.6f);

        if (itemDisplayContext != ItemDisplayContext.GROUND)
            poseStack.translate(type.translation.x - 0.5, type.translation.y - 0.5, type.translation.z);
        poseStack.scale(type.scale, type.scale, type.scale);
        ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(type.texture)).bindTexture();
        ClientConstants.LOADERS_HOLDER.model().getModel(ClientConstants.LOADERS_HOLDER.model().getModelResource(type.model)).render(poseStack, bufferSource, light);

        poseStack.pushPose();
        poseStack.translate(type.coalTranslation.x, type.coalTranslation.y, type.coalTranslation.z);
        poseStack.scale(type.coalScale, type.coalScale, type.coalScale);

        ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(type.coalTexture)).bindTexture();
        ClientConstants.LOADERS_HOLDER.model().getModel(ClientConstants.LOADERS_HOLDER.model().getModelResource(type.coalModel)).render(poseStack, bufferSource, light);
        poseStack.popPose();
        poseStack.popPose();
    }
}
