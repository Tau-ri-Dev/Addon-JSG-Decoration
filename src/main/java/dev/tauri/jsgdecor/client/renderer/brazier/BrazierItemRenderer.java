package dev.tauri.jsgdecor.client.renderer.brazier;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.tauri.jsg.item.JSGModelOBJInGUIRenderer;
import dev.tauri.jsgdecor.client.ClientConstants;
import dev.tauri.jsgdecor.common.item.BrazierItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BrazierItemRenderer implements JSGModelOBJInGUIRenderer.RenderPartInterface {

    @Override
    public void render(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        var item = itemStack.getItem();
        if (!(item instanceof BrazierItem brazier)) return;
        var type = brazier.type;

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
    }
}
