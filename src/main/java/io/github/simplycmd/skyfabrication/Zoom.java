package io.github.simplycmd.skyfabrication;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Zoom {
    public static void Zooom() {
        /*KeyBinding zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.libzoomertest.michelle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.libzoomertest.category"));
        ZoomInstance electricBoogaloo = ZoomRegistry.registerInstance(new ZoomInstance(
                new Identifier(Main.MOD_ID + ":zoom"),
                3.0F, new InstantTransitionMode(),
                new CinematicCameraMouseModifier(),
                new NoZoomOverlay()
        ));
        for (ZoomInstance instance : ZoomRegistry.getZoomInstances()) {
            System.out.println("Id: " + instance.getInstanceId() + " | Zooming: " + instance.getZoom() + " | Divisor: " + instance.getZoomDivisor());
        }
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            electricBoogaloo.setZoom(zoomKey.isPressed());
        });*/
    }
}
