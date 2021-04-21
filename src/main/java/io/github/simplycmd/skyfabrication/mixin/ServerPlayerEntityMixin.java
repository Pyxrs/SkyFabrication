package io.github.simplycmd.skyfabrication.mixin;

import java.util.ArrayList;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    Boolean sneak = false;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo info) {
        if (((ServerPlayerEntity) (Object) this).isSneaking() && sneak == false) {
            ArrayList<BlockPos> get_sapling_pos = getSaplingPos();
            World world = ((ServerPlayerEntity) (Object) this).world;
            sneak = true;
            int current_pos = 0;
            if (get_sapling_pos.size() > 0) {
                while (current_pos < get_sapling_pos.size()) {
                    particles(world, get_sapling_pos.get(current_pos), get_sapling_pos, current_pos);
                    growFertilizable(world, get_sapling_pos.get(current_pos));
                    if (Math.random() > 0.5) {
                        growFertilizable(world, get_sapling_pos.get(current_pos));
                    }
                    current_pos++;
                }
            }
        } else if (!((ServerPlayerEntity) (Object) this).isSneaking()) {
            sneak = false;
        }
    }

    private void particles(World world, BlockPos pos, ArrayList<BlockPos> get_sapling_pos,
            int current_pos) {

        Random RANDOM = new Random();
        double d = 0.5D;
        double g = 1.0D;
        for (int i = 0; i < 15; ++i) {
            float h = (float) ((float) RANDOM.nextGaussian() * 0.02D);
            float j = (float) ((float) RANDOM.nextGaussian() * 0.02D);
            float k = (float) ((float) RANDOM.nextGaussian() * 0.02D);
            double l = 0.5D - d;
            double m = (double) pos.getX() + l + RANDOM.nextDouble() * d * 2.0D;
            double n = (double) pos.getY() + RANDOM.nextDouble() * g;
            double o = (double) pos.getZ() + l + RANDOM.nextDouble() * d * 2.0D;
            if (!world.getBlockState((new BlockPos(m, n, o)).down()).isAir()) {
                ((ServerWorld) world).getServer().getPlayerManager()
                .sendToAll(new ParticleS2CPacket(ParticleTypes.HAPPY_VILLAGER, true, m, n, o, h, j, k, 0.0F, 1));
            }
        }
    }

    private ArrayList<BlockPos> getSaplingPos() {
        ArrayList<BlockPos> saplings = new ArrayList<BlockPos>();
        BlockPos pos = ((ServerPlayerEntity) (Object) this).getBlockPos();
        World world = ((ServerPlayerEntity) (Object) this).world;

        final int dist = 4;
        int x = pos.getX() - dist;
        while (x < pos.getX() + dist) {
            x++;
            int z = pos.getZ() - dist;
            while (z < pos.getZ() + dist) {
                z++;
                int y = pos.getY() - dist;
                while (y < pos.getY() + dist) {
                    y++;
                    if (BlockTags.SAPLINGS.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock())) {
                        saplings.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return saplings;
    }

    private static boolean growFertilizable(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof Fertilizable) {
            Fertilizable fertilizable = (Fertilizable) blockState.getBlock();
            if (fertilizable.isFertilizable(world, pos, blockState, world.isClient)) {
                if (world instanceof ServerWorld) {
                    if (fertilizable.canGrow(world, world.random, pos, blockState)) {
                        fertilizable.grow((ServerWorld) world, world.random, pos, blockState);
                    }
                }

                return true;
            }
        }

        return false;
    }

}