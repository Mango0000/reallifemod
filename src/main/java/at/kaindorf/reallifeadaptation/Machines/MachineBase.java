package at.kaindorf.reallifeadaptation.Machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MachineBase extends BlockFurnace {

    public MachineBase(boolean isBurning, String name) {
        super(false);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }


}
