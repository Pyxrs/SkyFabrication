package io.github.simplycmd.skyfabrication.client;

import io.github.simplycmd.skyfabrication.block.Blocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.infiniteEnergyBlock, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.cowBait, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.pigBait, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.sheepBait, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.chickenBait, RenderLayer.getCutout());
    }
}
