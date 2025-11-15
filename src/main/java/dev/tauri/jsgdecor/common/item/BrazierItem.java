package dev.tauri.jsgdecor.common.item;

import dev.tauri.jsg.helpers.ItemHelper;
import dev.tauri.jsg.item.JSGBlockItem;
import dev.tauri.jsg.item.JSGModelOBJInGUIRenderer;
import dev.tauri.jsgdecor.client.renderer.brazier.BrazierItemRenderer;
import dev.tauri.jsgdecor.common.block.BrazierType;
import dev.tauri.jsgdecor.common.registry.TabRegistry;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

public class BrazierItem extends JSGBlockItem {
    public final BrazierType type;

    public BrazierItem(Block pBlock, BrazierType type) {
        super(pBlock, new Properties(), List.of(TabRegistry.JSGD_TAB));
        this.type = type;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private static final JSGModelOBJInGUIRenderer instance = new JSGModelOBJInGUIRenderer();

            static {
                instance.renderPartInterface = new BrazierItemRenderer();
            }

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return instance;
            }
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        ItemHelper.applyGenericToolTip(this.getDescriptionId(), components, tooltipFlag);
    }
}
