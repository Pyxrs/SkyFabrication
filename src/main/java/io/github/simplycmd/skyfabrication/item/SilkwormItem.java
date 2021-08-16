package io.github.simplycmd.skyfabrication.item;

import io.github.simplycmd.skyfabrication.block.InfestedLeavesBlock;
import io.github.simplycmd.skyfabrication.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

import static io.github.simplycmd.skyfabrication.block.InfestedLeavesBlock.INFESTED;
import static net.minecraft.item.BoneMealItem.useOnFertilizable;

public class SilkwormItem extends Item {
    public static final InfestedLeavesBlock[] infested = {
            (InfestedLeavesBlock) BlockRegistry.get("infested_oak_leaves")
    };

    public SilkwormItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
        for (InfestedLeavesBlock block: infested) {
            if (blockState.getBlock() == block && blockState.get(INFESTED) < 10) {
                context.getWorld().setBlockState(context.getBlockPos(), blockState.with(INFESTED, blockState.get(INFESTED) + 1));
                return ActionResult.SUCCESS;
            }
            if (blockState.getBlock() == block.getNormalVariant()) {
                context.getWorld().setBlockState(context.getBlockPos(), block.getDefaultState());
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
