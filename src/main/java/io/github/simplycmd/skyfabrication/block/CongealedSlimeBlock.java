package io.github.simplycmd.skyfabrication.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

import static io.github.simplycmd.skyfabrication.Main.RandomNumber;

public class CongealedSlimeBlock extends Block {
    private static final double BOUNCINESS_THRESHOLD = 0.25;
    double bounciness_multiplier;

    public CongealedSlimeBlock(AbstractBlock.Settings settings, double bounciness) {
        super(settings);
        bounciness_multiplier = bounciness;
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, pos, entity, distance);
        } else {
            entity.handleFallDamage(distance, 0.0F, DamageSource.FALL);
        }

    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) entity).applyStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 2, false, false));
        }
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0D) {
            entity.setVelocity(vec3d.x, vec3d.y > -BOUNCINESS_THRESHOLD ? 0 : -vec3d.y * 1.2D * bounciness_multiplier, vec3d.z);
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1D && !entity.bypassesSteppingEffects()) {
            double e = 0.4D + d * 0.2D;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0D, e));
        }

        super.onSteppedOn(world, pos, entity);
    }
}
