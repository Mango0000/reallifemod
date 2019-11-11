package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.proxy.ClientProxy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StreetLightBlock extends BlockBase {//implements ITileEntityProvider{

    private final boolean isOn;

    public StreetLightBlock(String name, Material materialIn, boolean isOn) {
        super(name, materialIn);
        this.isOn = isOn;

        if(this.isOn){
            this.setLightLevel(1.0f);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        {
            if (this.isOn)
            {
                //macht neue Lampe nicht scheined
                worldIn.setBlockState(pos, ClientProxy.streetlightblock.getDefaultState(), 2);
            }
            else
            {
                worldIn.setBlockState(pos, ClientProxy.lit_streetlightblock.getDefaultState(), 2);
            }
        }
        return true;
    }
}
