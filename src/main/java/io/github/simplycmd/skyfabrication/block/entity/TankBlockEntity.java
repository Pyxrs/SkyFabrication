package io.github.simplycmd.skyfabrication.block.entity;

import io.github.simplycmd.skyfabrication.block.RedstoneClockBlock;
import io.github.simplycmd.skyfabrication.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TankBlockEntity extends BlockEntity {
    public int tick = 0;

    public TankBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.TANK_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TankBlockEntity blockEntity) {

    }
}
