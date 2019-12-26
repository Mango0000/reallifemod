package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.Machines.MachineRafenerie;
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

public class TileEntityRafenerie extends TileEntity implements ITickable {

    int counter = 0;
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void update() {
        if(world.isRemote){
            return;
        }

        if(counter < 200){
            counter ++;
            return;
        }
        counter = 0;
        if(!RedsonteUtil.isPowered(world, pos)){
            return;
        }

        IBlockState ourState = world.getBlockState(pos);
        EnumFacing facing = ourState.getValue(MachineRafenerie.FACING);
        BlockPos destinationPos = pos.offset(facing);

        TileEntity tileent = world.getTileEntity(destinationPos);
        if(tileent == null){
            return;
        }
        if(!tileent.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())){
            return;
        }
        IItemHandler handler = tileent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
        ItemHandlerHelper.insertItemStacked(handler, new ItemStack(CommonProxy.oilbucket, 1), false);
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
