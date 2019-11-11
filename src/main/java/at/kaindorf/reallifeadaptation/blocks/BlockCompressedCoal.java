package at.kaindorf.reallifeadaptation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCompressedCoal extends Block{
    public BlockCompressedCoal(Material materialIn, String name) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
