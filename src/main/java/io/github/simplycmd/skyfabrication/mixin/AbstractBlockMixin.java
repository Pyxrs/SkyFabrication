package io.github.simplycmd.skyfabrication.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.simplycmd.skyfabrication.block.SnadBlock;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockMixin {

    @Shadow public abstract Block getBlock();

    @Inject(method = "isOf", at = @At("HEAD"), cancellable = true)
    public void isOf(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block instanceof SandBlock && getBlock() instanceof SnadBlock) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}