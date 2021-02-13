package io.github.simplycmd.skyfabrication.client;

import io.github.simplycmd.skyfabrication.block.Blocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.INFINITE_ENERGY_BLOCK, RenderLayer.getCutout());
    }
}
