package io.github.simplycmd.skyfabrication.registry;

import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.block.*;
import io.github.simplycmd.skyfabrication.registry.util.CustomSettings;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JPosition;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

import java.util.HashMap;
import java.util.Map;

import static net.devtech.arrp.api.RuntimeResourcePack.id;
import static net.devtech.arrp.json.loot.JLootTable.*;

public class BlockRegistry {
    private static final HashMap<CustomSettings, Block> BLOCKS = new HashMap<>() {{
        // Oddities
        put(new CustomSettings("concern_block", "oddities"), new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.HONEY)));
        put(new CustomSettings("milk_block", "oddities"), new MilkBlock(FabricBlockSettings.of(Material.ICE).strength(0.5F).sounds(BlockSoundGroup.WET_GRASS).noCollision()));
        put(new CustomSettings("congealed_green_slime_block", "oddities"), new CongealedSlimeBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.SLIME), 1.0D));

        // Power/Redstone
        put(new CustomSettings("infinity_reactor", "energy"), new Block(FabricBlockSettings.of(Material.METAL).strength(5F).sounds(BlockSoundGroup.LODESTONE)));
        put(new CustomSettings("redstone_clock", "energy"), new RedstoneClockBlock(FabricBlockSettings.of(Material.STONE).strength(5F).sounds(BlockSoundGroup.STONE)));

        // Metalworks
        put(new CustomSettings("tank", "smelting"), new TankBlock(FabricBlockSettings.of(Material.METAL)));

        // Actually Useful lol
        put(new CustomSettings("cloud_block", "useful", CustomSettings.LootType.NONE), new Block(FabricBlockSettings.of(Material.GLASS).strength(0.1F).sounds(BlockSoundGroup.WOOL).noCollision()));
        put(new CustomSettings("snad", "useful"), new SnadBlock(0xc1bba3));
        put(new CustomSettings("red_snad", "useful"), new SnadBlock(0xc1bba3));
        put(new CustomSettings("infested_oak_leaves", "useful"), new InfestedLeavesBlock(Blocks.OAK_LEAVES, BlockSoundGroup.GRASS));

        // Automation
        put(new CustomSettings("iron_spikes", "automation"), new SpikedBlock(2, FabricBlockSettings.of(Material.METAL).strength(1F).sounds(BlockSoundGroup.METAL).noCollision()));

        // Decoration

        // Bait
        put(new CustomSettings("cow_bait", "bait", CustomSettings.BlockstateType.RANDOM_Y), new BaitBlock(EntityType.COW));
        put(new CustomSettings("pig_bait", "bait", CustomSettings.BlockstateType.RANDOM_Y), new BaitBlock(EntityType.PIG));
        put(new CustomSettings("sheep_bait", "bait", CustomSettings.BlockstateType.RANDOM_Y), new BaitBlock(EntityType.SHEEP));
        put(new CustomSettings("chicken_bait", "bait", CustomSettings.BlockstateType.RANDOM_Y), new BaitBlock(EntityType.CHICKEN));

        // Compressed
        put(new CustomSettings("compressed_dust", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_cobblestone", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_gravel", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_sand", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_dirt", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_flint", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_soul_sand", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_netherrack", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
        put(new CustomSettings("compressed_end_stone", "compressed", CustomSettings.BlockstateType.NORMAL), new CompressedBlock());
    }};

    private static final HashMap<String, BlockItem> BLOCK_ITEMS = new HashMap<>() {{
        // Oddities
        put("concern_block", new BlockItem(BlockRegistry.get("concern_block"), new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        put("milk_block", new BlockItem(BlockRegistry.get("milk_block"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("congealed_green_slime_block", new BlockItem(BlockRegistry.get("congealed_green_slime_block"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        // Power/Redstone
        put("infinity_reactor", new BlockItem(BlockRegistry.get("infinity_reactor"), new FabricItemSettings().group(ItemRegistry.ENERGY)));
        put("redstone_clock", new BlockItem(BlockRegistry.get("redstone_clock"), new FabricItemSettings().group(ItemGroup.REDSTONE)));

        // Metalworks
        put("tank", new BlockItem(BlockRegistry.get("tank"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        // Actually Useful lol
        put("snad", new BlockItem(BlockRegistry.get("snad"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("red_snad", new BlockItem(BlockRegistry.get("red_snad"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("infested_oak_leaves", new BlockItem(BlockRegistry.get("infested_oak_leaves"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        // Automation
        put("iron_spikes", new BlockItem(BlockRegistry.get("iron_spikes"), new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        // Decoration

        // Bait
        put("cow_bait", new BlockItem(BlockRegistry.get("cow_bait"), new FabricItemSettings().group(ItemGroup.MISC)));
        put("pig_bait", new BlockItem(BlockRegistry.get("pig_bait"), new FabricItemSettings().group(ItemGroup.MISC)));
        put("sheep_bait", new BlockItem(BlockRegistry.get("sheep_bait"), new FabricItemSettings().group(ItemGroup.MISC)));
        put("chicken_bait", new BlockItem(BlockRegistry.get("chicken_bait"), new FabricItemSettings().group(ItemGroup.MISC)));

        // Compressed
        put("compressed_dust", new BlockItem(BlockRegistry.get("compressed_dust"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_cobblestone", new BlockItem(BlockRegistry.get("compressed_cobblestone"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_gravel", new BlockItem(BlockRegistry.get("compressed_gravel"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_sand", new BlockItem(BlockRegistry.get("compressed_sand"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_dirt", new BlockItem(BlockRegistry.get("compressed_dirt"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_flint", new BlockItem(BlockRegistry.get("compressed_flint"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_soul_sand", new BlockItem(BlockRegistry.get("compressed_soul_sand"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_netherrack", new BlockItem(BlockRegistry.get("compressed_netherrack"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        put("compressed_end_stone", new BlockItem(BlockRegistry.get("compressed_end_stone"), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }};

    public static void register() {
        // Scary code do not touch
        for (Map.Entry<CustomSettings, Block> block : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, Main.ID(block.getKey().getId()), block.getValue());
            Main.RESOURCE_PACK.addBlockState(blockstate(block.getKey().getId(), block.getKey()), Main.ID(block.getKey().getId()));
            Main.RESOURCE_PACK.addModel(new JModel().parent("block/cube_all").textures(JModel.textures().layer0("minecraft:block/" + block.getKey().getPath() + "/" + block.getKey().getId())), Main.ID("block/" + block.getKey().getId()));
            Main.RESOURCE_PACK.addModel(model(block.getKey().getId(), block.getKey().getItemModelType(), block.getKey().getPath()), Main.ID("item/" + block.getKey().getId()));
            lootTable(block.getKey().getId(), block.getKey().getLootType());
        }
        for (Map.Entry<String, BlockItem> item : BLOCK_ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, Main.ID(item.getKey()), item.getValue());
        }
    }

    public static Block get(String blockId) {
        if (BLOCKS != null) {
            for (Map.Entry<CustomSettings, Block> block : BLOCKS.entrySet()) {
                if (block.getKey().getId().matches(blockId)) {
                    return block.getValue();
                }
            }
            throw new IllegalArgumentException("Block not valid!");
        } else {
            return Blocks.AIR;
        }
    }

    public static void lootTable(String blockId, CustomSettings.LootType type) {
        switch (type) {
            case NORMAL: {
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
            case NONE: {

            }
        }
    }

    private static JState blockstate(String blockId, CustomSettings settings) {
        CustomSettings.BlockstateType type = settings.getBlockstateType();
        JBlockModel model = JState.model(Main.MOD_ID + ":block/" + settings.getPath() + "/" + blockId);
        return switch (type) {
            case RANDOM_X -> JState.state(JState.multipart(
                    model, model.x(90), model.x(180), model.x(270)
            ));
            case RANDOM_Y -> JState.state(JState.multipart(
                    model, model.y(90), model.y(180), model.y(270)
            ));
            case RANDOM -> JState.state(JState.multipart(
                    model, model.x(90), model.x(180), model.x(270), model.y(90), model.y(180), model.y(270)
            ));
            default -> JState.state(JState.variant(model));
        };
    }

    private static JModel model(String blockId, CustomSettings.ItemModelType type, String path) {
        switch (type) {
            case TEXTURE:
                return JModel.model().parent("minecraft:item/generated").textures(JModel.textures().layer0(Main.MOD_ID + ":item/" + blockId));
            default:
                return JModel.model().parent(Main.MOD_ID + ":block/" + path + "/" + blockId).display(JModel.display()
                        .setGui(new JPosition().rotation(30, 45, 0).scale(0.625f, 0.625f, 0.625f))
                        .setGround(new JPosition().translation(0, 3, 0).scale(0.25f, 0.25f, 0.25f))
                        .setHead(new JPosition().rotation(0, 180, 0).scale(1, 1, 1))
                        .setFixed(new JPosition().rotation(0, 180, 0).scale(0.5f, 0.5f, 0.5f))
                        .setThirdperson_righthand(new JPosition().rotation(75, 315, 0).translation(0, 2.5f, 0).scale(0.375f, 0.375f, 0.375f))
                        .setFirstperson_righthand(new JPosition().rotation(0, 315, 0).scale(0.4f, 0.4f, 0.4f))
                );
        }
    }


}
