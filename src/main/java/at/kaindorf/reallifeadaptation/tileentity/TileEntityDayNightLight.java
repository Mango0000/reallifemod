package at.kaindorf.reallifeadaptation.tileentity;

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
        if (!world.isDaytime()) {
            world.setBlockState(pos, CommonProxy.lit_day_night_block.getDefaultState());
        } else {
            if (world.getBlockState(pos) != CommonProxy.day_night_block.getDefaultState()) {
                world.setBlockState(pos, CommonProxy.day_night_block.getDefaultState());
            }
        }
    }
}
