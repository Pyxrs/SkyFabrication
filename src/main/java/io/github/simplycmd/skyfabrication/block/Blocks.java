package io.github.simplycmd.skyfabrication.block;

import io.github.simplycmd.skyfabrication.item.Items;
import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.blockentity.SmoothConcreteBlockEntity;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

public class Blocks {
    enum BlockstateType {
        NORMAL,
        RANDOM_X,
        RANDOM_Y
    }
    enum ItemModelType {
        NORMAL,
        TEXTURE
    }

    public static Block cowBait;
    public static Block pigBait;
    public static Block sheepBait;
    public static Block chickenBait;

    public static Block infiniteEnergyBlock;

    //public static Block oakBarrel;

    public static Block smoothConcreteBlock;
    public static BlockEntityType<SmoothConcreteBlockEntity> smoothConcreteBlockEntity;

    public static void RegisterBlocks() {
        cowBait = registerBlock("cow_bait", "bait/", BlockstateType.RANDOM_Y, ItemModelType.NORMAL, new BaitBlock(EntityType.COW), ItemGroup.MISC);
        pigBait = registerBlock("pig_bait", "bait/", BlockstateType.RANDOM_Y, ItemModelType.NORMAL, new BaitBlock(EntityType.PIG), ItemGroup.MISC);
        sheepBait = registerBlock("sheep_bait","bait/", BlockstateType.RANDOM_Y, ItemModelType.NORMAL, new BaitBlock(EntityType.SHEEP), ItemGroup.MISC);
        chickenBait = registerBlock("chicken_bait","bait/", BlockstateType.RANDOM_Y, ItemModelType.NORMAL, new BaitBlock(EntityType.CHICKEN), ItemGroup.MISC);

        registerBlock("concern_block", BlockstateType.NORMAL, ItemModelType.NORMAL, new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.HONEY)), ItemGroup.DECORATIONS);

        registerBlock("milk_block", BlockstateType.NORMAL, ItemModelType.NORMAL, new MilkBlock(FabricBlockSettings.of(Material.ICE).strength(0.5F).sounds(BlockSoundGroup.WET_GRASS).noCollision()), ItemGroup.DECORATIONS);

        registerBlock("snad","", BlockstateType.NORMAL, ItemModelType.NORMAL, new SnadBlock(0xc1bba3, FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND)), ItemGroup.BUILDING_BLOCKS);
        registerBlock("red_snad", BlockstateType.NORMAL, ItemModelType.NORMAL, new SnadBlock(0xc1bba4, FabricBlockSettings.of(Material.AGGREGATE).strength(0.5F).sounds(BlockSoundGroup.SAND)), ItemGroup.BUILDING_BLOCKS);

        registerBlock("congealed_green_slime_block", BlockstateType.NORMAL, ItemModelType.NORMAL, new CongealedSlimeBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F).sounds(BlockSoundGroup.SLIME), 1.0D), ItemGroup.BUILDING_BLOCKS);

        infiniteEnergyBlock = registerBlock("infinite_energy_block", BlockstateType.NORMAL, ItemModelType.TEXTURE, new Block(FabricBlockSettings.of(Material.STONE).strength(5F).sounds(BlockSoundGroup.LODESTONE)), Items.ENERGY);

        smoothConcreteBlock = registerBlock("smooth_concrete", BlockstateType.NORMAL, ItemModelType.NORMAL, new SmoothConcreteBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.8F)), ItemGroup.BUILDING_BLOCKS);
        ColorProviderRegistry.BLOCK.register(Blocks::smoothConcreteColor, smoothConcreteBlock);

        //OAK_BARREL = RegisterBlock("oak_barrel", new BarrelBlock(FabricBlockSettings.of(Material.WOOD).strength(1F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);

        registerBlock("compressed_dust", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_cobblestone", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_gravel", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_sand", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_dirt", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_flint", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_soul_sand", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_netherrack", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
        registerBlock("compressed_end_stone", "compressed/", BlockstateType.NORMAL, ItemModelType.NORMAL, new CompressedBlock(), ItemGroup.DECORATIONS);
    }

    public static Block registerBlock(String id, BlockstateType stateType, ItemModelType itemType, Block block, ItemGroup group) {
        return registerBlock(id, "", stateType, itemType, block, group);
    }

    public static Block registerBlock(String id, String path, BlockstateType stateType, ItemModelType itemType, Block block, ItemGroup group) {
        // Identifiers
        Identifier identifier = new Identifier(Main.MOD_ID, id);
        Identifier rp_identifier = new Identifier(Main.MOD_ID, "item/" + id);

        // Registers
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(group)));

        // Resource Packs
        Main.RESOURCE_PACK.addBlockState(blockstate(path + id, stateType), identifier);
        Main.RESOURCE_PACK.addModel(JModel.model(Main.MOD_ID + ":block/" + id), rp_identifier);
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

    private static JState blockstate(String modelPath, BlockstateType type) {
        switch (type) {
            case RANDOM_X:
                return JState.state(JState.multipart(
                        JState.model(Main.MOD_ID + ":block/" + modelPath),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).x(90),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).x(180),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).x(270)
                ));
            case RANDOM_Y:
                return JState.state(JState.multipart(
                        JState.model(Main.MOD_ID + ":block/" + modelPath),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).y(90),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).y(180),
                        JState.model(Main.MOD_ID + ":block/" + modelPath).y(270)
                ));
            default:
                return JState.state(JState.variant(JState.model(Main.MOD_ID + ":block/" + modelPath)));
        }
    }
}
