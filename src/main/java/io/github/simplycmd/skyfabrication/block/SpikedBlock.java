package io.github.simplycmd.skyfabrication.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpikedBlock extends TransparentBlock {
    int damage;

    public SpikedBlock(int damage, Settings settings) {
        super(settings);
        this.damage = damage;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.slowMovement(state, new Vec3d(1, 0, 1));
        entity.damage(DamageSource.GENERIC, damage);
        if (entity instanceof LivingEntity)
            if (((LivingEntity) entity).hurtTime == 0 && !((PlayerEntity)entity).isCreative())
                entity.setPosition(entity.getPos().add(0, -0.1, 0));
    }
}
