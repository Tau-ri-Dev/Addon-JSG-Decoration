package dev.tauri.jsgdecor.common.block.glass;

import dev.tauri.jsgcore.common.block.core.GlassBlockTabbed;
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

public class DecorGlassBlock extends GlassBlockTabbed {
    public final Predicate<BlockState> skipRenderPredicate;

    public DecorGlassBlock(DyeColor pDyeColor, Properties pProperties, Predicate<BlockState> skipRenderPredicate) {
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
