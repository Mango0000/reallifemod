package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.Machines.MachineHochofen;
import at.kaindorf.reallifeadaptation.Machines.MachineSawmill;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Random;

public class TileEntityHochofen extends TileEntity implements ITickable {
    private ItemStackHandler handler = new ItemStackHandler(4);
    private String customName;
    private ItemStack smelting = ItemStack.EMPTY;
    private boolean flag;
    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int tempreture;
    private boolean notificationMessage = true;

    private Random rand = new Random();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        else return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.sintering_furnace");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.burnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.tempreture = compound.getInteger("tempreture");
        this.currentBurnTime = getItemBurnTime((ItemStack) this.handler.getStackInSlot(0));

        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short) this.burnTime);
        compound.setInteger("CookTime", (short) this.cookTime);
        compound.setInteger("tempreture", (short) this.tempreture);
        compound.setTag("Inventory", this.handler.serializeNBT());

        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityHochofen te) {
        return te.getField(0) > 0;
    }

    public void update() {
        IBlockState ourState = world.getBlockState(pos);
        EnumFacing facing = ourState.getValue(MachineHochofen.FACING);
        BlockPos destinationPos = pos.offset(facing);

        TileEntity tileent = world.getTileEntity(destinationPos);
        if (tileent == null) {
            return;
        }

        if (!tileent.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
            return;
        }
        IItemHandler handler2 = tileent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());

        if (this.isBurning()) {
            --this.burnTime;
            if (burnTime % 4 == 0) {
                if (tempreture < 2000) {
                    tempreture += 10;
                }
            }
            //System.out.println(tempreture);
        } else if (tempreture > 0) {

            if (rand.nextInt(4) + 1 == 1) {
                tempreture -= 5;
            }
        }
        if (tempreture >= 1000 && notificationMessage) {
            notificationMessage = false;
            EntityPlayer ep = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 100, false);
            if (ep != null) {
                if(!world.isRemote){
                    return;
                }
                ep.sendMessage(new TextComponentString("Hochofen hat die passende Temperatur von 1000 Grad Celsius erreicht"));
            }
        }
        if (tempreture < 1000 && !notificationMessage) {
            notificationMessage = true;
        }

        //System.out.println(tempreture + " °C");

        ItemStack fuel = this.handler.getStackInSlot(0);

        if (isItemFuel(fuel) && !this.isBurning()) {
            this.burnTime = getItemBurnTime(fuel);
            this.currentBurnTime = burnTime;
            flag = false;
        }
        if (burnTime == currentBurnTime && isItemFuel(fuel)) {
            fuel.shrink(1);
            handler.setStackInSlot(0,fuel);
        }
        if(tempreture > 1000){
            ItemStack toSmelt = this.handler.getStackInSlot(1);
            if(toSmelt.getItem() != Items.IRON_INGOT){
                return;
            }
            cookTime ++;
            if(cookTime > 250){
                cookTime = 0;
                ItemHandlerHelper.insertItemStacked(handler2, new ItemStack(CommonProxy.STEEL_INGOT, 1), false);
                smelting = ItemStack.EMPTY;
                toSmelt.shrink(1);
                handler.setStackInSlot(1,toSmelt);
                return;
            }
        }

//        if (!(boolean) ourState.getValue(MachineHochofen.BURNING) && tempreture > 1000) {
//            MachineHochofen.setState(true, world, pos);
//        }else if((boolean) ourState.getValue(MachineHochofen.BURNING) && tempreture < 1000){
//            MachineHochofen.setState(false, world, pos);
//        }
    }


    public static int getItemBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) return 0;
        else {
            Item item = fuel.getItem();
            if (item == Items.LAVA_BUCKET) {
                return 500;
            }
            return GameRegistry.getFuelValue(fuel);
        }
    }

    public static boolean isItemFuel(ItemStack fuel) {
        return getItemBurnTime(fuel) > 0;
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.tempreture;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.tempreture = value;
        }
    }
}