package io.github.simplycmd.skyfabrication.client;

import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.item.WateringCanItem;
import io.github.simplycmd.skyfabrication.registry.BlockRegistry;
import io.github.simplycmd.skyfabrication.registry.ItemRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

import org.lwjgl.glfw.GLFW;

public class ClientMain implements ClientModInitializer {
    public static double ZOOM_LEVEL = 19.0;
    public static KeyBinding zoomKey;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("infinity_reactor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("iron_spikes"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("cow_bait"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("pig_bait"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("sheep_bait"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("chicken_bait"), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.get("cloud_block"), RenderLayer.getTranslucent());

        FabricModelPredicateProviderRegistry.register(ItemRegistry.get("watering_can"), new Identifier("full"), (itemStack, clientWorld, livingEntity, seed) -> WateringCanItem.isFull(itemStack) ? 1 : 0);

        zoomKey = new KeyBinding("key." + Main.MOD_ID + ".zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category." + Main.MOD_ID + ".zoom");
        KeyBindingHelper.registerKeyBinding(zoomKey);
    }
}
