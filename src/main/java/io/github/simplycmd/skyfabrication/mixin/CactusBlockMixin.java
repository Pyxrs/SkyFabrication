package io.github.simplycmd.skyfabrication.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.simplycmd.skyfabrication.block.SnadBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (world.isAir(pos.up())) {
            int height = 1;
            while(world.getBlockState(pos.down(height)).isOf(((CactusBlock)(Object)this))) {
                ++height;
            }
            BlockState lowerBlock = world.getBlockState(pos.down(height));
            if (!(lowerBlock.getBlock() instanceof SnadBlock)) {
                return;
            }
            if (height < 3) {
                int j = state.get(CactusBlock.AGE);
                if (j == 15) {
                    world.setBlockState(pos.up(), ((CactusBlock)(Object)this).getDefaultState());
                    BlockState blockState = state.with(CactusBlock.AGE, 0);
                    world.setBlockState(pos, blockState.with(CactusBlock.AGE, 0), 4);
                    blockState.neighborUpdate(world, pos.up(), ((CactusBlock)(Object)this), pos, false);
                } else {
                    world.setBlockState(pos, state.with(CactusBlock.AGE, j + 1), 4);
                }
            }
            ci.cancel();
        }
    }
}
