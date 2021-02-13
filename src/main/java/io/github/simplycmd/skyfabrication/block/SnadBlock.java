package io.github.simplycmd.skyfabrication.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;

public class SnadBlock extends SandBlock {
    // Define heights
    public static IntProperty MAX_HEIGHT = IntProperty.of("max_height", 1, 3);

    // Create the snad, standard custom class
    public SnadBlock(int color, Settings settings) {
        super(color, settings);
    }

    // Enables random ticks for snad
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    // What to do when random tick occurs
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        // Get state of block above snad
        BlockState topBlock = world.getBlockState(pos.up());
        int height = 1;

        // Get total height of sugarcane/cactus
        while (true) {
            BlockState nextTopBlockState = world.getBlockState(pos.up(height));
            Block nextTopBlock = nextTopBlockState.getBlock();
            if (!(nextTopBlock instanceof SugarCaneBlock || nextTopBlock instanceof CactusBlock)) {
                --height;
                break;
            } else {
                topBlock = nextTopBlockState;
            }
            ++height;
        }

        // Once the while loop correctly identifies the height of the plant, tick the top block to grow it
        BlockPos topBlockPos = pos.up(height);
        topBlock.getBlock().randomTick(topBlock, world, topBlockPos, random);
    }
}
