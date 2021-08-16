package io.github.simplycmd.skyfabrication.block.entity;

import io.github.simplycmd.skyfabrication.block.RedstoneClockBlock;
import io.github.simplycmd.skyfabrication.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RedstoneClockBlockEntity extends BlockEntity {
    public int tick = 0;

    public RedstoneClockBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.REDSTONE_CLOCK_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RedstoneClockBlockEntity blockEntity) {
        blockEntity.tick++;
        if (blockEntity.tick > (world.getBlockState(pos).get(RedstoneClockBlock.SPEED) * 2)) {
            blockEntity.tick = 0;
            world.setBlockState(pos, state.with(RedstoneClockBlock.POWERED, !world.getBlockState(pos).get(RedstoneClockBlock.POWERED)));
        }
    }
}
