package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.blocks.BlockDayNightLight;
import at.kaindorf.reallifeadaptation.blocks.BlockElectricStreetLight;
import at.kaindorf.reallifeadaptation.blocks.BlockStreetLightBlock;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityDayNightLight extends TileEntity implements ITickable {
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        if (world.getBlockState(pos).getValue(((BlockDayNightLight) world.getBlockState(pos).getBlock()).HALF) == BlockStreetLightBlock.EnumBlockHalf.LOWER) {
            if (!world.isDaytime()) {
                ((BlockDayNightLight) world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock()).setState(true, world, new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()));
            } else {
                ((BlockDayNightLight) world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock()).setState(false, world, new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()));
            }
        }
    }
}
