package dev.tauri.jsgdecor.common.item;


import dev.tauri.jsgcore.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgcore.common.item.JSGBlockItem;
import dev.tauri.jsgdecor.client.renderer.brazier.BrazierBEWLR;
import dev.tauri.jsgdecor.common.block.BrazierType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.function.Consumer;

public class BrazierItem extends JSGBlockItem {
    public final BrazierType type;

    public BrazierItem(Block pBlock, BrazierType type) {
        super(pBlock, new Properties(), List.of());
        this.type = type;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(AbstractItemBEWLR.create(BrazierBEWLR::new));
    }
}
