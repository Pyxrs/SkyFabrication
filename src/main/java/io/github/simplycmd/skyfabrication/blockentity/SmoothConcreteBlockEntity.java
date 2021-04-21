package io.github.simplycmd.skyfabrication.blockentity;

import io.github.simplycmd.skyfabrication.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SmoothConcreteBlockEntity extends BlockEntity {
    public SmoothConcreteBlockEntity(BlockPos pos, BlockState state) {
        super(Blocks.smoothConcreteBlockEntity, pos, state);
    }
}
