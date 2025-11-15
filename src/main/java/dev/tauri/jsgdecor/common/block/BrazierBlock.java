package dev.tauri.jsgdecor.common.block;

import dev.tauri.jsg.block.IHighlightBlock;
import dev.tauri.jsg.block.IItemBlock;
import dev.tauri.jsg.block.JSGBlock;
import dev.tauri.jsg.helpers.BlockPosHelper;
import dev.tauri.jsg.item.JSGBlockItem;
import dev.tauri.jsg.property.JSGProperties;
import dev.tauri.jsgdecor.common.blockentity.BrazierBE;
import dev.tauri.jsgdecor.common.item.BrazierItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiFunction;

public class BrazierBlock extends JSGBlock implements EntityBlock, IItemBlock, SimpleWaterloggedBlock, IHighlightBlock {
    public final BrazierType type;
    public final BiFunction<BlockPos, BlockState, BrazierBE> beFactory;

    public BrazierBlock(Properties pProperties, BrazierType type, BiFunction<BlockPos, BlockState, BrazierBE> beFactory) {
        super(pProperties);
        this.type = type;
        this.beFactory = beFactory;
        registerDefaultState(defaultBlockState()
                .setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, Direction.NORTH)
                .setValue(BlockStateProperties.WATERLOGGED, false)
                .setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .setValue(BlockStateProperties.LIT, false));
    }

    public static boolean canLight(BlockState pState) {
        return !pState.getOptionalValue(BlockStateProperties.WATERLOGGED).orElse(false) && !pState.getOptionalValue(BlockStateProperties.LIT).orElse(false);
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState blockstate, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (canLight(blockstate) && player.getItemInHand(hand).is(Items.FLINT_AND_STEEL) && blockstate.getOptionalValue(BlockStateProperties.DOUBLE_BLOCK_HALF).orElse(DoubleBlockHalf.LOWER) == DoubleBlockHalf.UPPER) {
            level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, true), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
            player.getItemInHand(hand).hurtAndBreak(1, player, (p_41303_) -> {
                p_41303_.broadcastBreakEvent(hand);
            });

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.use(blockstate, level, blockpos, player, hand, hit);
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        return pState.getOptionalValue(BlockStateProperties.DOUBLE_BLOCK_HALF).orElse(DoubleBlockHalf.LOWER) != DoubleBlockHalf.UPPER || blockstate.is(this);
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.rotateDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), rotation));
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.flipDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), mirror));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(JSGProperties.FACING_HORIZONTAL_PROPERTY);
        builder.add(BlockStateProperties.LIT);
        builder.add(BlockStateProperties.DOUBLE_BLOCK_HALF);
        builder.add(BlockStateProperties.WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull FluidState getFluidState(BlockState p_152045_) {
        return p_152045_.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152045_);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @Nullable BrazierBE newBlockEntity(BlockPos pPos, BlockState pState) {
        return beFactory.apply(pPos, pState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos blockpos = ctx.getClickedPos();
        Level level = ctx.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(ctx)) {
            return defaultBlockState()
                    .setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, ctx.getHorizontalDirection().getOpposite())
                    .setValue(BlockStateProperties.WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
        }
        return null;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide && pPlayer.isCreative()) {
            preventCreativeDropFromBottomPart(pLevel, pPos, pState, pPlayer);
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    public static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos bottomPos = pos.below();
            BlockState bottomState = level.getBlockState(bottomPos);
            if (bottomState.is(state.getBlock()) && bottomState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                BlockState newState = bottomState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(bottomPos, newState, 35);
                level.levelEvent(player, 2001, bottomPos, Block.getId(bottomState));
            }
            return;
        }
        BlockPos topPos = pos.above();
        BlockState topState = level.getBlockState(topPos);
        if (topState.is(state.getBlock()) && topState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
            BlockState newState = topState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
            level.setBlock(topPos, newState, 35);
            level.levelEvent(player, 2001, topPos, Block.getId(topState));
        }

    }

    @Override
    @ParametersAreNonnullByDefault
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        var box = type.blockShape.get().bounds();
        if (box.maxY - box.minY > 1)
            pLevel.setBlock(pPos.above(), pState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public JSGBlockItem getItemBlock() {
        return new BrazierItem(this, type);
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        var box = type.blockShape.get().bounds();
        if (box.maxY - box.minY > 1) {
            var topBox = Shapes.box(box.minX, 0, box.minZ, box.maxX, box.maxY - 1, box.maxZ);
            var bottomBox = Shapes.box(box.minX, box.minY, box.minZ, box.maxX, 1, box.maxZ);
            return (pState.getOptionalValue(BlockStateProperties.DOUBLE_BLOCK_HALF).orElse(DoubleBlockHalf.LOWER) == DoubleBlockHalf.LOWER ? bottomBox : topBox);
        }
        return type.blockShape.get();
    }

    @Override
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    @Nonnull
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean renderHighlight(BlockState blockState) {
        return false;
    }
}
