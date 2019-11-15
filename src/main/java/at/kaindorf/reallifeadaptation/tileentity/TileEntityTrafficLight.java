package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.blocks.BlockRafenerie;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.util.RedsonteUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityTrafficLight extends TileEntity implements ITickable {
    private int counter = 0;
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        if(world.isRemote){
            return;
        }

        if(counter < 100){
            counter ++;
            return;
        }
        counter = 0;
        if(!RedsonteUtil.isPowered(world, pos)){
            return;
        }

        IBlockState ourState = world.getBlockState(pos);
        EnumFacing facing = ourState.getValue(BlockRafenerie.FACING);
        BlockPos destinationPos = pos.offset(facing);

        TileEntity tileent = world.getTileEntity(destinationPos);
        if(tileent == null){
            return;
        }
//        if (!world.isDaytime()) {
//            world.setBlockState(pos, CommonProxy.lit_day_night_block.getDefaultState());
//        } else {
//            if (world.getBlockState(pos) != CommonProxy.day_night_block.getDefaultState()) {
//                world.setBlockState(pos, CommonProxy.day_night_block.getDefaultState());
//            }
//        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        counter = compound.getInteger("counter");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("counter", counter);
        return compound;
    }
}
