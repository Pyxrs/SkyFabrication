package io.github.simplycmd.skyfabrication.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.*;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BaitBlock extends Block {
    public static final IntProperty FEED = IntProperty.of("feed", 0, 3);

    public static final Integer PLAYER_RADIUS = 10;

    private final EntityType<?> animalType;

    public BaitBlock(EntityType<?> animalType) {
        super(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP).ticksRandomly().nonOpaque());
        this.animalType = animalType;
        setDefaultState(getStateManager().getDefaultState().with(FEED, 3));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FEED);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Check if player is too close
        if (world.isPlayerInRange((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, (double)PLAYER_RADIUS)) {
            world.getServer().getPlayerManager().broadcastChatMessage(Text.of("Oh no! The animals are too scared to come closer! Make sure all players keep their distance! " + pos), MessageType.CHAT, Util.NIL_UUID);
        } else {

            // Pick position to spawn mob
            double x = (double)pos.getX() + (world.random.nextDouble() - world.random.nextDouble()) * 4.5D;
            double y = (double)pos.getY() + (world.random.nextInt(3) - 1);
            double z = (double)pos.getZ() + (world.random.nextDouble() - world.random.nextDouble()) * 4.5D;

            // Get animal
            AnimalEntity animal = checkAnimal(animalType, world);
            animal.updatePositionAndAngles(x, y, z, world.random.nextFloat() * 360.0F, 0.0F);

            // Check if valid spawn and do the spawning
            if (world.isSpaceEmpty(animalType.createSimpleBoundingBox(x, y, z)) && SpawnRestriction.canSpawn(animalType, world, SpawnReason.SPAWNER, new BlockPos(x, y, z), world.getRandom())) {
                if (state.get(FEED) > 0) world.setBlockState(pos, state.with(FEED, state.get(FEED) - 1));
                else world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.spawnEntity(animal);
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    private static AnimalEntity checkAnimal(EntityType<?> animalType, World world) {
        if (animalType == EntityType.COW) {
            return new CowEntity(EntityType.COW, world);
        } else if (animalType == EntityType.PIG) {
            return new PigEntity(EntityType.PIG, world);
        } else if (animalType == EntityType.SHEEP) {
            return new SheepEntity(EntityType.SHEEP, world);
        } else if (animalType == EntityType.CHICKEN) {
            return new ChickenEntity(EntityType.CHICKEN, world);
        } else {
            return new LlamaEntity(EntityType.TRADER_LLAMA, world);
        }
    }
}
