package io.github.simplycmd.skyfabrication.item;

import io.github.simplycmd.skyfabrication.registry.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CloudBucketItem extends Item {
    public CloudBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockPos target_pos = user.getBlockPos().offset(user.getHorizontalFacing()).offset(Direction.Axis.Y, 1);

        if (world.getBlockState(target_pos).getBlock() == Blocks.AIR) {
            world.setBlockState(target_pos, BlockRegistry.get("cloud_block").getDefaultState());

            if (!user.isCreative()) {
                itemStack.decrement(1);
                user.giveItemStack(Items.BUCKET.getDefaultStack());
            }

            world.playSound(null, target_pos, BlockRegistry.get("cloud_block").getSoundGroup(BlockRegistry.get("cloud_block").getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1, 1);
            return TypedActionResult.success(itemStack);
        } else {
            return TypedActionResult.consume(itemStack);
        }
    }
}
