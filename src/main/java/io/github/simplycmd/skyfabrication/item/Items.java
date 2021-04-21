package io.github.simplycmd.skyfabrication.item;

import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.block.Blocks;
import io.github.simplycmd.skyfabrication.tools.BaseAIOT;
import io.github.simplycmd.skyfabrication.tools.BaseToolMaterial;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final ItemGroup ENERGY = FabricItemGroupBuilder.build(
            new Identifier(Main.MOD_ID, "energy"),
            () -> new ItemStack(Blocks.infiniteEnergyBlock)
    );
    public static Item terracotta_bucket;
    public static Item water_terracotta_bucket;
    public static Item lava_terracotta_bucket;

    public static void RegisterItems() {
        RegisterItem("wooden_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.WOOD, new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("stone_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.STONE, new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("iron_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.IRON, new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("golden_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.GOLD, new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("diamond_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.DIAMOND, new Item.Settings().group(ItemGroup.TOOLS)));
        RegisterItem("netherite_aiot", new BaseAIOT(3.5F, -2.8F, BaseToolMaterial.NETHERITE, new Item.Settings().group(ItemGroup.TOOLS).fireproof()));

        RegisterItem("clay_bucket", new Item(new Item.Settings().group(ItemGroup.TOOLS)));
        terracotta_bucket = RegisterItem("terracotta_bucket", new TerracottaBucketItem(Fluids.EMPTY, new Item.Settings().group(ItemGroup.TOOLS)));
        water_terracotta_bucket = RegisterItem("water_terracotta_bucket", new TerracottaBucketItem(Fluids.WATER, new Item.Settings().group(ItemGroup.TOOLS)));
        lava_terracotta_bucket = RegisterItem("lava_terracotta_bucket", new TerracottaBucketItem(Fluids.LAVA, new Item.Settings().group(ItemGroup.TOOLS)));
    }
    public static Item RegisterItem(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id), item);
        return item;
    }
}
