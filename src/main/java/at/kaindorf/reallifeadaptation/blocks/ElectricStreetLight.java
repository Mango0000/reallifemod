package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ElectricStreetLight extends BlockRedstoneLight {
    private final boolean isOn;

    public ElectricStreetLight(boolean isOn) {
        super(isOn);
        this.setUnlocalizedName("electric_light_block");
        this.setRegistryName("electric_light_block");
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.isOn = isOn;

        if (isOn) {
            this.setLightLevel(1.0F);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        //super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                //macht neue Lampe nicht scheined
                worldIn.setBlockState(pos, ClientProxy.electricstreetlight.getDefaultState(), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ClientProxy.lit_electricstreetlight.getDefaultState(), 2);
            }
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        //super.onBlockAdded(worldIn, pos, state);
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ClientProxy.electricstreetlight.getDefaultState(), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, ClientProxy.lit_electricstreetlight.getDefaultState(), 2);
            }
        }
    }

}
