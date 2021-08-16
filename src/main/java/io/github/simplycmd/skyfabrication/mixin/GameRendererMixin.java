package io.github.simplycmd.skyfabrication.mixin;

import io.github.simplycmd.skyfabrication.client.ClientMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> cir) {
        if (ClientMain.zoomKey.isPressed()) {
            cir.setReturnValue(19.0);
            MinecraftClient.getInstance().options.smoothCameraEnabled = true;
        } else {
            MinecraftClient.getInstance().options.smoothCameraEnabled = false;
        }
    }
}