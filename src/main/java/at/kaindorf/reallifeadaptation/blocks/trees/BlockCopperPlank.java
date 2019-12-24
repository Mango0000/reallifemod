package at.kaindorf.reallifeadaptation.blocks.trees;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.blocks.BlockBase;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.block.material.Material;

public class BlockCopperPlank extends BlockBase {

    public BlockCopperPlank(String name, Material materialIn) {
        super(name, materialIn);
        setCreativeTab(RealLifeAdaptation.TREE_TAB);
    }
}
