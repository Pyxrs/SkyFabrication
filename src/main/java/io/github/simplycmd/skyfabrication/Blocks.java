package io.github.simplycmd.skyfabrication;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static void RegisterBlocks() {
        RegisterBlock("concern_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.HONEY)), ItemGroup.DECORATIONS);
    }
    public static void RegisterBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id),
                new BlockItem(block, new Item.Settings().group(group)));
    }
}
