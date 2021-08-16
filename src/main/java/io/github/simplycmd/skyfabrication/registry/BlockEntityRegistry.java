package io.github.simplycmd.skyfabrication.registry;

import io.github.simplycmd.skyfabrication.Main;
import io.github.simplycmd.skyfabrication.block.entity.RedstoneClockBlockEntity;
import io.github.simplycmd.skyfabrication.block.entity.TankBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {
    public static final BlockEntityType<RedstoneClockBlockEntity> REDSTONE_CLOCK_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(RedstoneClockBlockEntity::new, BlockRegistry.get("redstone_clock")).build(null);
    public static final BlockEntityType<TankBlockEntity> TANK_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(TankBlockEntity::new, BlockRegistry.get("tank")).build(null);

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Main.ID("redstone_clock"), REDSTONE_CLOCK_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Main.ID("tank"), TANK_BLOCK_ENTITY);
    }
}