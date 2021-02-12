package io.github.simplycmd.skyfabrication;

import io.github.simplycmd.skyfabrication.tools.BaseAIOT;
import io.github.simplycmd.skyfabrication.tools.BaseToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static void RegisterItems() {
        RegisterItem("wooden_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.WOOD,
                new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("stone_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.STONE,
                new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("iron_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.IRON,
                new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("gold_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.GOLD,
                new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("diamond_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.DIAMOND,
                new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("netherite_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.NETHERITE,
                new Item.Settings().group(ItemGroup.TOOLS).fireproof()));
    }
    public static void RegisterItem(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), item);
    }
}
