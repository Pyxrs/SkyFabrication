package io.github.simplycmd.skyfabrication.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class CompressedBlock extends Block {
    private static final Settings SETTINGS = AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(4.0F, 6.0F).sounds(BlockSoundGroup.STONE);

    public CompressedBlock() {
        super(SETTINGS);
    }
}
