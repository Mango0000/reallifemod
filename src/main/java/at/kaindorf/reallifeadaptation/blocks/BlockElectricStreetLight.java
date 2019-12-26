package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityElectricStreetLight;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class BlockElectricStreetLight extends BlockStreetLightBlock implements ITileEntityProvider {
    public BlockElectricStreetLight(String name, Material material, boolean isOn) {
        super(material, name ,isOn);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {

    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityElectricStreetLight();
    }

    public void setState(boolean stateType, World worldIn, BlockPos pos){
        if(stateType){
            worldIn.setBlockState(pos, CommonProxy.lit_electricstreetlight.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
        }else{
            worldIn.setBlockState(pos, CommonProxy.electricstreetlight.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
        }
    }
}
