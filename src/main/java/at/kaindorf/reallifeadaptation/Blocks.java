package at.kaindorf.reallifeadaptation;


import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class Blocks {
    public static final Block LIT_ELECTRIC_STREET_LIGHT_BLOCK;
    public static final Block ELECTRIC_STREET_LIGHT_BLOCK;

    @Nullable
    private static Block getRegisteredBlock(String blockName)
    {
        Block block = Block.REGISTRY.getObject(new ResourceLocation(blockName));
        return block;
    }

    static{
        LIT_ELECTRIC_STREET_LIGHT_BLOCK = getRegisteredBlock("lit_electric_street_light_block");
        ELECTRIC_STREET_LIGHT_BLOCK = getRegisteredBlock("electric_street_light_block");
    }
}
