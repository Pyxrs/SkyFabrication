package io.github.simplycmd.skyfabrication.registry;

import io.github.simplycmd.skyfabrication.block.*;
import io.github.simplycmd.skyfabrication.Main;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JPosition;
import net.devtech.arrp.json.models.JRotation;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.devtech.arrp.api.RuntimeResourcePack.id;
import static net.devtech.arrp.json.loot.JLootTable.*;

public class BlockRegistry {
    enum BlockstateType {
        NORMAL,
        RANDOM_X,
        RANDOM_Y,
    }
    enum ItemModelType {
        NORMAL,
        TEXTURE
    }

    private static final HashMap<String, Block> BLOCKS = new HashMap<String, Block>() {{
        // Oddities
        put("concern_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.HONEY)));
        put("milk_block", new MilkBlock(FabricBlockSettings.of(Material.ICE).strength(0.5F).sounds(BlockSoundGroup.WET_GRASS).noCollision()));
        put("congealed_green_slime_block", new CongealedSlimeBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.SLIME), 1.0D));

        //Power/Redstone
        put("infinity_reactor", new Block(FabricBlockSettings.of(Material.METAL).strength(5F).sounds(BlockSoundGroup.LODESTONE)));
        put("redstone_clock", new RedstoneClockBlock(FabricBlockSettings.of(Material.STONE).strength(5F).sounds(BlockSoundGroup.STONE)));

        // Actually Useful lol
        put("cloud_block", new Block(FabricBlockSettings.of(Material.GLASS).strength(0.1F).sounds(BlockSoundGroup.WOOL).noCollision()));
        put("snad", new SnadBlock(0xc1bba3));
        put("red_snad", new SnadBlock(0xc1bba3));

        // Automation
        put("iron_spikes", new SpikedBlock(2, FabricBlockSettings.of(Material.METAL).strength(1F).sounds(BlockSoundGroup.METAL).noCollision()));

        // Decoration
        //put("smooth_concrete", new SmoothConcreteBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.8F)));
        //ColorProviderRegistry.BLOCK.register(BlockRegistry::smoothConcreteColor, BlockRegistry.get("smooth_concrete"));

        // Bait
        put("cow_bait", new BaitBlock(EntityType.COW));
        put("pig_bait", new BaitBlock(EntityType.PIG));
        put("sheep_bait", new BaitBlock(EntityType.SHEEP));
        put("chicken_bait", new BaitBlock(EntityType.CHICKEN));

        // Compressed
        put("compressed_dust", new CompressedBlock());
        put("compressed_cobblestone", new CompressedBlock());
        put("compressed_gravel", new CompressedBlock());
        put("compressed_sand", new CompressedBlock());
        put("compressed_dirt", new CompressedBlock());
        put("compressed_flint", new CompressedBlock());
        put("compressed_soul_sand", new CompressedBlock());
        put("compressed_netherrack", new CompressedBlock());
        put("compressed_end_stone", new CompressedBlock());
    }};

    public static void register() {
        for (Map.Entry<String, Block> block : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, Main.ID(block.getKey()), block.getValue());
            Registry.register(Registry.ITEM, Main.ID(block.getKey()), new BlockItem(block.getValue(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Main.RESOURCE_PACK.addBlockState(blockstate(block.getKey(), BlockstateType.NORMAL), Main.ID(block.getKey()));
            Main.RESOURCE_PACK.addModel(model(block.getKey(), ItemModelType.NORMAL), Main.ID("item/" + block.getKey()));
            standardLootTable(block.getKey());
        }

        //for (BlockItem block_item = BLOCK_ITEMS.iterator().next(); BLOCK_ITEMS.iterator().hasNext(); block_item = BLOCK_ITEMS.iterator().next()) {
        //    Registry.register(Registry.ITEM, Main.ID(block_item.getTranslationKey()), block_item);
        //}
    }

    public static Block get(String blockId) {
        return BLOCKS.getOrDefault(blockId, Blocks.AIR);
    }

    public static void standardLootTable(String blockId) {
        Main.RESOURCE_PACK.addLootTable(id(Main.MOD_ID + ":blocks/" + blockId),
                loot("minecraft:block")
                        .pool(pool()
                                .rolls(1)
                                .entry(entry()
                                        .type("minecraft:item")
                                        .name(Main.MOD_ID + ":" + blockId))
                                .condition(predicate("minecraft:survives_explosion")))
        );
    }

    private static JState blockstate(String blockId, BlockstateType type) {
        JBlockModel model = JState.model(Main.MOD_ID + ":block/" + blockId);
        switch (type) {
            case RANDOM_X:
                return JState.state(JState.multipart(
                        model, model.x(90), model.x(180), model.x(270)
                ));
            case RANDOM_Y:
                return JState.state(JState.multipart(
                        model, model.y(90), model.y(180), model.y(270)
                ));
            default:
                return JState.state(JState.variant(model));
        }
    }

    private static JModel model(String blockId, ItemModelType type) {
        switch (type) {
            case TEXTURE:
                return JModel.model().parent("minecraft:item/generated").textures(JModel.textures().layer0(Main.MOD_ID + ":item/" + blockId));
            default:
                return JModel.model().parent(Main.MOD_ID + ":block/" + blockId).display(JModel.display()
                        .setGui(new JPosition().rotation(30, 45, 0).scale(0.625f, 0.625f, 0.625f))
                        .setGround(new JPosition().translation(0, 3, 0).scale(0.25f, 0.25f, 0.25f))
                        .setHead(new JPosition().rotation(0, 180, 0).scale(1, 1, 1))
                        .setFixed(new JPosition().rotation(0, 180, 0).scale(0.5f, 0.5f, 0.5f))
                        .setThirdperson_righthand(new JPosition().rotation(75, 315, 0).translation(0, 2.5f, 0).scale(0.375f, 0.375f, 0.375f))
                        .setFirstperson_righthand(new JPosition().rotation(0, 315, 0).scale(0.4f, 0.4f, 0.4f))
                );
        }
    }












    public static Block registerBlock(String id, Boolean blockItem, BlockstateType stateType, ItemModelType itemType, Block block) {
        return registerBlock(id, "", blockItem, stateType, itemType, block);
    }

    public static Block registerBlock(String id, Boolean blockItem, BlockstateType stateType, ItemModelType itemType, Block block, ItemGroup group) {
        return registerBlock(id, "", blockItem, stateType, itemType, block, group);
    }

    public static Block registerBlock(String id, String path, Boolean blockItem, BlockstateType stateType, ItemModelType itemType, Block block, ItemGroup group) {
        // Identifiers
        Identifier identifier = new Identifier(Main.MOD_ID, id);
        Identifier rp_identifier = new Identifier(Main.MOD_ID, "item/" + id);

        // Registers
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(group)));

        // Resource Packs
        //Main.RESOURCE_PACK.addBlockState(blockstate(path + id, stateType), identifier);
        //Main.RESOURCE_PACK.addModel(JModel.model(Main.MOD_ID + ":block/" + id), rp_identifier);
        /*if (itemType == ItemModelType.NORMAL) Main.RESOURCE_PACK.addModel(JModel.model(Main.MOD_ID + ":block/" + id), rp_identifier);
        else if (itemType == ItemModelType.TEXTURE) Main.RESOURCE_PACK.addModel(JModel.model().textures(JModel.textures().layer0(Main.MOD_ID + ":block/" + id)), rp_identifier);*/
        return block;
    }

    public static Block registerBlock(String id, String path, Boolean blockItem, BlockstateType stateType, ItemModelType itemType, Block block) {
        // Identifiers
        Identifier identifier = new Identifier(Main.MOD_ID, id);
        Identifier rp_identifier = new Identifier(Main.MOD_ID, "item/" + id);

        // Registers
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings()));

        // Resource Packs
        //Main.RESOURCE_PACK.addBlockState(blockstate(path + id, stateType), identifier);
        //Main.RESOURCE_PACK.addModel(JModel.model(Main.MOD_ID + ":block/" + id), rp_identifier);
        /*if (itemType == ItemModelType.NORMAL) Main.RESOURCE_PACK.addModel(JModel.model(Main.MOD_ID + ":block/" + id), rp_identifier);
        else if (itemType == ItemModelType.TEXTURE) Main.RESOURCE_PACK.addModel(JModel.model().textures(JModel.textures().layer0(Main.MOD_ID + ":block/" + id)), rp_identifier);*/
        return block;
    }

    private static int smoothConcreteColor(BlockState state, BlockRenderView view, BlockPos pos, int tintIndex) {
        /*IntProperty[] RGB = SmoothConcreteBlock.getColor();
        int[] RGB_int = {state.get(RGB[0]), state.get(RGB[1]), state.get(RGB[2])};
        Color color = new Color(RGB_int[0], RGB_int[1], RGB_int[2]);
        String hex = Integer.toHexString(color.getRGB()).substring(2); //SmoothConcreteBlock.getColor()
        return Integer.parseInt(hex);*/
        return 0xfffff;
    }
}
