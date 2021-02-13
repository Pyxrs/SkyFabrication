package io.github.simplycmd.skyfabrication.block;

import io.github.simplycmd.skyfabrication.Items;
import io.github.simplycmd.skyfabrication.Main;
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
    public static Block INFINITE_ENERGY_BLOCK;
    public static void RegisterBlocks() {
        RegisterBlock("concern_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.HONEY)), ItemGroup.DECORATIONS);

        RegisterBlock("milk_block", new MilkBlock(FabricBlockSettings.of(Material.ICE).strength(0.5F).sounds(BlockSoundGroup.WET_GRASS).noCollision()), ItemGroup.DECORATIONS);

        RegisterBlock("snad", new SnadBlock(0xc1bba3, FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND)), ItemGroup.BUILDING_BLOCKS);
        RegisterBlock("red_snad", new SnadBlock(0xc1bba4, FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND)), ItemGroup.BUILDING_BLOCKS);

        INFINITE_ENERGY_BLOCK = RegisterBlock("infinite_energy_block", new Block(FabricBlockSettings.of(Material.STONE).strength(5F).sounds(BlockSoundGroup.LODESTONE)), Items.ENERGY);
    }
    public static Block RegisterBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, id),
                new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }
}
