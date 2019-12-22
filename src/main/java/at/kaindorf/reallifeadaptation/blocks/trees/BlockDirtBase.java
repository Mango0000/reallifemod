package at.kaindorf.reallifeadaptation.blocks.trees;

import at.kaindorf.reallifeadaptation.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class BlockDirtBase extends BlockBase
{
    public BlockDirtBase(String name)
    {
        super(name, Material.GROUND);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        return true;
    }

}