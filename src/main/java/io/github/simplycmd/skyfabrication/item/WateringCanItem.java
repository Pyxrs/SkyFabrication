package io.github.simplycmd.skyfabrication.item;

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

import static net.minecraft.item.BoneMealItem.useOnFertilizable;

public class WateringCanItem extends Item {
    int radius;

    public WateringCanItem(int radius, Settings settings) {
        super(settings);
        this.radius = radius;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (isFull(context.getStack())) {
            boolean used = false;
            for (int dx = -radius; dx <= radius; dx++)
                for (int dz = -radius; dz <= radius; dz++) {
                    int x = context.getBlockPos().getX() + dx;
                    int y = context.getBlockPos().getY();
                    int z = context.getBlockPos().getZ() + dz;
                    if (context.getWorld().getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.FARMLAND)
                        if (useOnFertilizable(context.getStack(), context.getWorld(), new BlockPos(x, y, z)))
                            used = true;
                }
            /*for (int x = context.getBlockPos().getX() - radius - 1; x < context.getBlockPos().getX() + radius; x++)
                for (int z = context.getBlockPos().getZ() - radius - 1; z < context.getBlockPos().getZ() + radius; z++)
                    if (context.getWorld().getBlockState(new BlockPos(x, context.getBlockPos().getY() - 1, z)).getBlock() == Blocks.FARMLAND)
                        if (useOnFertilizable(context.getStack(), context.getWorld(), new BlockPos(x, context.getBlockPos().getY(), z)))
                            used = true;*/
            if (used) {
                setFull(context.getStack(), false);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!isFull(user.getStackInHand(hand))) {
            BlockHitResult hit = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
            if (world.getBlockState(hit.getBlockPos()).getBlock().equals(Blocks.WATER)) {
                setFull(user.getStackInHand(hand), true);
                world.setBlockState(hit.getBlockPos(), Blocks.AIR.getDefaultState());
                world.playSound(user, user.getBlockPos(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1, 1);
                return TypedActionResult.consume(user.getStackInHand(hand));
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public static boolean isFull(ItemStack stack) {
        NbtCompound nbtCompound = stack.getTag();
        return nbtCompound != null && nbtCompound.getBoolean("Full");
    }

    public static void setFull(ItemStack stack, boolean full) {
        NbtCompound nbtCompound = stack.getOrCreateTag();
        nbtCompound.putBoolean("Full", full);
    }

    protected static BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        float f = player.getPitch();
        float g = player.getYaw();
        Vec3d vec3d = player.getEyePos();
        float h = MathHelper.cos(-g * 0.017453292F - 3.1415927F);
        float i = MathHelper.sin(-g * 0.017453292F - 3.1415927F);
        float j = -MathHelper.cos(-f * 0.017453292F);
        float k = MathHelper.sin(-f * 0.017453292F);
        float l = i * j;
        float n = h * j;
        Vec3d vec3d2 = vec3d.add((double)l * 5.0D, (double)k * 5.0D, (double)n * 5.0D);
        return world.raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.OUTLINE, fluidHandling, player));
    }
}
