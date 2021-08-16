

    /*protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        super.appendProperties(stateManager);
        stateManager.add(INFESTED);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(INFESTED, state.get(INFESTED) + 1));
    }

    public int infested(BlockState state, BlockRenderView view, BlockPos pos, int tintIndex) {
        Integer infested = state.get(INFESTED);
        String hex = "0x" + Integer.toHexString(Color.HSBtoRGB(100, 100, infested + 50)).substring(2);
        //System.out.println(0xffffff57);
        return Integer.parseInt(hex);
    }
}*/

package io.github.simplycmd.skyfabrication.block;

import io.github.simplycmd.skyfabrication.registry.BlockRegistry;
import lombok.Getter;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import java.awt.*;
import java.util.Random;

public class InfestedLeavesBlock extends LeavesBlock {
    @Getter
    private final Block normalVariant;
    public static final IntProperty INFESTED = IntProperty.of("infested", 0, 10);

    public InfestedLeavesBlock(Block normalVariant, BlockSoundGroup soundGroup) {
        super(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(soundGroup).nonOpaque().allowsSpawning((state, world, pos, type) -> false).suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false));
        setDefaultState(getStateManager().getDefaultState().with(INFESTED, 0));
        this.normalVariant = normalVariant;
        ColorProviderRegistry.BLOCK.register(this::infested, this);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        super.appendProperties(stateManager);
        stateManager.add(INFESTED);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!(state.get(INFESTED) >= 10)) world.setBlockState(pos, state.with(INFESTED, state.get(INFESTED) + 1));
    }

    public int infested(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) {
        // Frightening color changing code... stay away
        Color originalColorRGB = Color.decode("#" + Integer.toHexString(world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()));
        float[] originalColorHSB = Color.RGBtoHSB(originalColorRGB.getRed(), originalColorRGB.getGreen(), originalColorRGB.getBlue(), null);
        Color color = Color.getHSBColor(originalColorHSB[0], clampPercent(originalColorHSB[1] - (state.get(INFESTED) / 10F)), clampPercent(originalColorHSB[2] + (state.get(INFESTED) / 20F)));
        String hex = "0x" + Integer.toHexString(color.getRGB()).substring(2);
        return Integer.decode(hex);
    }

    private static float clampPercent(float current) {
        if (current < 0) return 0;
        if (current > 1) return 1;
        return current;
    }
}

