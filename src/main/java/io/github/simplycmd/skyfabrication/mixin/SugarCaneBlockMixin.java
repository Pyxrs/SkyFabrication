package io.github.simplycmd.skyfabrication.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.simplycmd.skyfabrication.block.SnadBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (world.isAir(pos.up())) {
            int height = 1;
            while(world.getBlockState(pos.down(height)).isOf(((SugarCaneBlock)(Object)this))) {
                ++height;
            }
            BlockState lowerBlock = world.getBlockState(pos.down(height));
            if (!(lowerBlock.getBlock() instanceof SnadBlock)) {
                return;
            }
            if (height < 3) {
                int j = state.get(SugarCaneBlock.AGE);
                if (j == 15) {
                    world.setBlockState(pos.up(), ((SugarCaneBlock)(Object)this).getDefaultState());
                    world.setBlockState(pos, state.with(SugarCaneBlock.AGE, 0), 4);
                } else {
                    world.setBlockState(pos, state.with(SugarCaneBlock.AGE, j + 1), 4);
                }
            }
            ci.cancel();
        }

    }


}
