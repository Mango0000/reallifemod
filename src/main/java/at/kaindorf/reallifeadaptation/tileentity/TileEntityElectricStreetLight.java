package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.blocks.BlockStreetLightBlock;
import at.kaindorf.reallifeadaptation.blocks.BlockElectricStreetLight;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.util.RedsonteUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityElectricStreetLight extends TileEntity implements ITickable {
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        if (world.getBlockState(pos).getValue(((BlockElectricStreetLight) world.getBlockState(pos).getBlock()).HALF) == BlockStreetLightBlock.EnumBlockHalf.LOWER) {
            if (!RedsonteUtil.isPowered(world, pos)) {
                ((BlockElectricStreetLight) world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock()).setState(false, world, new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()));
            } else if (RedsonteUtil.isPowered(world, pos)  && world.getBlockState(pos) == CommonProxy.electricstreetlight.getDefaultState()) {
                ((BlockElectricStreetLight) world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock()).setState(true, world, new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()));
            }
        }
    }
}