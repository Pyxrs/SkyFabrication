package io.github.simplycmd.skyfabrication.registry;

import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.item.CloudBucketItem;
import io.github.simplycmd.skyfabrication.item.SilkwormItem;
import io.github.simplycmd.skyfabrication.item.TerracottaBucketItem;
import io.github.simplycmd.skyfabrication.item.WateringCanItem;
import io.github.simplycmd.skyfabrication.tools.BaseAIOT;
import io.github.simplycmd.skyfabrication.tools.BaseToolMaterial;
import net.devtech.arrp.json.models.JModel;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

import static net.devtech.arrp.json.models.JModel.model;

public class ItemRegistry {
    public static final ItemGroup ENERGY = FabricItemGroupBuilder.build(
            new Identifier(Main.MOD_ID, "energy"),
            () -> new ItemStack(BlockRegistry.get("infinity_reactor"))
    );

    private static final HashMap<String, Item> ITEMS = new HashMap<>() {{
        // AIOTs
        put("wooden_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.WOOD, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("stone_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.STONE, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("iron_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.IRON, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("golden_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.GOLD, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("diamond_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.DIAMOND, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("netherite_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.NETHERITE, new FabricItemSettings().group(ItemGroup.TOOLS).fireproof()));

        // Buckets
        put("clay_bucket", new Item(new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("terracotta_bucket", new TerracottaBucketItem(Fluids.EMPTY, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("water_terracotta_bucket", new TerracottaBucketItem(Fluids.WATER, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("lava_terracotta_bucket", new TerracottaBucketItem(Fluids.LAVA, new FabricItemSettings().group(ItemGroup.TOOLS)));
        put("cloud_in_a_bucket", new CloudBucketItem(new FabricItemSettings().group(ItemGroup.TOOLS)));

        // Utility
        put("watering_can", new WateringCanItem(1, new FabricItemSettings().group(ItemGroup.TOOLS)));

        // Beings
        put("silkworm", new SilkwormItem(new FabricItemSettings().group(ItemGroup.MISC)));
    }};

    public static void register() {
        for (Map.Entry<String, Item> item : ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
            Main.RESOURCE_PACK.addModel(model().parent("minecraft:item/generated").textures(JModel.textures().layer0(Main.MOD_ID + ":item/" + item.getKey())), Main.ID("item/" + item.getKey()));
        }
    }

    public static Item get(String itemId) {
        if (ITEMS != null) return ITEMS.get(itemId);
        else return Items.AIR;
    }
}
