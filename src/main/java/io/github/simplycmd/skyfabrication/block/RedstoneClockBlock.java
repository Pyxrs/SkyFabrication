package io.github.simplycmd.skyfabrication.block;

import io.github.simplycmd.skyfabrication.block.entity.RedstoneClockBlockEntity;
import io.github.simplycmd.skyfabrication.registry.BlockEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneClockBlock extends BlockWithEntity {
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");
    public static final IntProperty SPEED = IntProperty.of("speed", 1, 8);

    public RedstoneClockBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(POWERED, false));
        setDefaultState(getStateManager().getDefaultState().with(SPEED, 4));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedstoneClockBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityRegistry.REDSTONE_CLOCK_BLOCK_ENTITY, RedstoneClockBlockEntity::tick);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(POWERED);
        stateManager.add(SPEED);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        int power = 0;
        if (state.get(POWERED))
            power = 15;
        return power;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockState(pos).get(SPEED) == 8)
            world.setBlockState(pos, state.with(SPEED, 1));
        else
            world.setBlockState(pos, state.with(SPEED, world.getBlockState(pos).get(SPEED) + 1));
        return ActionResult.SUCCESS;
    }
}
