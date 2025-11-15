package dev.tauri.jsgdecor.common.blockentity;

import dev.tauri.jsgdecor.common.block.BrazierType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.function.Supplier;

public class BrazierBE extends BlockEntity {
    public final BrazierType type;

    public BrazierBE(BlockPos pPos, BlockState pBlockState, BrazierType type, Supplier<BlockEntityType<BrazierBE>> beTypeSupplier) {
        super(beTypeSupplier.get(), pPos, pBlockState);
        this.type = type;
    }

    @Override
    public AABB getRenderBoundingBox() {
        return type.blockShape.get().bounds().move(getBlockPos());
    }
}
