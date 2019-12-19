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
    private int whichState = 0;
    private int max = 10;

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    public TileEntity doMethod() {
        IBlockState ourState = world.getBlockState(pos);
        EnumFacing facing = ourState.getValue(BlockRafenerie.FACING);
        BlockPos destinationPos = pos.offset(facing);

        TileEntity tileent = world.getTileEntity(destinationPos);
        return tileent;
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        //System.out.println(counter + " ");
//        TileEntity tileent = doMethod();
//        if (tileent == null) {
//            return;
//        }
        if (counter < max) {
            counter++;
            return;
        } else {
            whichState++;
            if (whichState == 3) {
                whichState = 0;
            }
            System.out.println(whichState);
        }
        counter = 0;
        if (!RedsonteUtil.isPowered(world, pos)) {
            return;
        }

        switch (whichState) {
            case 0:
                world.setBlockState(pos, CommonProxy.red_traffic_light_lamp.getDefaultState());
                max = 10;
                System.out.println("red");
                break;
            case 1:
                System.out.println("orange");
                world.setBlockState(pos, CommonProxy.orange_traffic_light_lamp.getDefaultState());
                max = 1;
                break;
            case 2:
                System.out.println("green");
                world.setBlockState(pos, CommonProxy.traffic_light_block.getDefaultState());
                max = 10;
                break;
        }
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
