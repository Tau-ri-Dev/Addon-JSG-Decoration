package dev.tauri.jsgdecor.common.block.glass;

import dev.tauri.jsgcore.common.block.core.GlassPaneBlockTabbed;
import dev.tauri.jsgcore.common.registry.CoreTabs;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdecor.common.registry.JSGDecorTabs;
import net.minecraft.core.Direction;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DecorGlassPaneBlock extends GlassPaneBlockTabbed {
    public final Predicate<BlockState> skipRenderPredicate;

    public DecorGlassPaneBlock(DyeColor pDyeColor, Properties pProperties, Predicate<BlockState> skipRenderPredicate) {
        super(pDyeColor, pProperties);
        this.skipRenderPredicate = skipRenderPredicate;
    }

    @Override
    public List<Supplier<RegistryObjectWrapper<CreativeModeTab>>> getTabs() {
        return List.of(() -> CoreTabs.TAB_BUILDING_BLOCKS, () -> JSGDecorTabs.JSGD_BUILDING_TAB);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return skipRenderPredicate.test(pAdjacentBlockState);
    }
}
