package at.kaindorf.reallifeadaptation.Machines;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MachineBase extends BlockFurnace {

    public MachineBase(boolean isBurning, String name) {
        super(false);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(RealLifeAdaptation.MACHINE_TAB);
    }


}
