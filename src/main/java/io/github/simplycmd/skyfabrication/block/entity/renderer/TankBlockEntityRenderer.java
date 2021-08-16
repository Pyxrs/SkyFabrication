package io.github.simplycmd.skyfabrication.block.entity.renderer;

import io.github.simplycmd.skyfabrication.block.entity.TankBlockEntity;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;

import java.util.Objects;

public class TankBlockEntityRenderer implements BlockEntityRenderer<TankBlockEntity> {
    public TankBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(TankBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        assert renderer != null;
        QuadEmitter emitter = renderer.meshBuilder().getEmitter();

        Sprite sprite = MinecraftClient.getInstance().getBakedModelManager().getBlockModels().getModel(Blocks.FURNACE.getDefaultState()).getSprite();
        litBlock(emitter, entity, 1, sprite, matrices, vertexConsumers);

        sprite = MinecraftClient.getInstance().getBakedModelManager().getBlockModels().getModel(Blocks.LAVA.getDefaultState()).getSprite();
        matrices.translate(0, 0, 1);
        unlitBlock(emitter, 0.25F, sprite, matrices, vertexConsumers);
        matrices.translate(0, 0, 1);
        unlitBlock(emitter, 0.5F, sprite, matrices, vertexConsumers);
        matrices.translate(0, 0, 1);
        unlitBlock(emitter, 0.75F, sprite, matrices, vertexConsumers);
        matrices.translate(0, 0, 1);
        unlitBlock(emitter, 1F, sprite, matrices, vertexConsumers);

        matrices.pop();
    }

    private static void unlitBlock(QuadEmitter emitter, float level, Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        for(Direction direction : Direction.values()) {
            if (direction == Direction.UP)
                emitter.square(direction, 0, 0, 1, 1, 1 - level);
            else
                emitter.square(direction, 0, 0, 1, level, 0);

            emitter.spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.spriteColor(0, -1, -1, -1, -1);
            vertexConsumers.getBuffer(RenderLayer.getTranslucent()).quad(matrices.peek(), emitter.toBakedQuad(0, sprite, false), 1, 1, 1, 0x00F0_00F0, OverlayTexture.DEFAULT_UV);
        }
    }

    private static void litBlock(QuadEmitter emitter, BlockEntity entity, float level, Sprite sprite, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        for(Direction direction : Direction.values()) {
            if (direction == Direction.UP)
                emitter.square(direction, 0, 0, 1, 1, 1 - level);
            else
                emitter.square(direction, 0, 0, 1, level, 0);

            emitter.spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.spriteColor(0, -1, -1, -1, -1);

            int skyLight = switch (direction) {
                case UP -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().up());
                case DOWN -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().down());
                case NORTH -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().north());
                case WEST -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().west());
                case SOUTH -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().south());
                case EAST -> WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().east());
            };
            vertexConsumers.getBuffer(RenderLayer.getTranslucent()).quad(matrices.peek(), emitter.toBakedQuad(0, sprite, false), 1, 1, 1, skyLight, OverlayTexture.DEFAULT_UV);
        }
    }
}
