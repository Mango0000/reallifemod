package at.kaindorf.reallifeadaptation.tileentity;

import at.kaindorf.reallifeadaptation.Machines.MachineCustomFurnace;
import at.kaindorf.reallifeadaptation.blocks.BlockRafenerie;
import at.kaindorf.reallifeadaptation.items.ItemBlade;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.recipes.CustormFurnaceRecipes;

import at.kaindorf.reallifeadaptation.util.RedsonteUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
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

public class TileEntityCustomFurnace extends TileEntity implements ITickable {
    private ItemStackHandler handler = new ItemStackHandler(4);
    private String customName;
    private ItemStack smelting = ItemStack.EMPTY;

    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int totalCookTime = 200;

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
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentBurnTime = getItemBurnTime((ItemStack) this.handler.getStackInSlot(2));

        if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short) this.burnTime);
        compound.setInteger("CookTime", (short) this.cookTime);
        compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
        compound.setTag("Inventory", this.handler.serializeNBT());

        if (this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityCustomFurnace te) {
        return te.getField(0) > 0;
    }

    public void update() {
        IBlockState ourState = world.getBlockState(pos);
        EnumFacing facing = ourState.getValue(MachineCustomFurnace.FACING);
        BlockPos destinationPos = pos.offset(facing);

        TileEntity tileent = world.getTileEntity(destinationPos);
        if (tileent == null) {
            return;
        }
        if(!RedsonteUtil.isPowered(world, pos)){
            return;
        }

        if (!tileent.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
            return;
        }
        IItemHandler handler2 = tileent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
        if (this.isBurning()) {
            --this.burnTime;
            MachineCustomFurnace.setState(true, world, pos);
        }

        ItemStack[] inputs = new ItemStack[]{handler.getStackInSlot(0), handler.getStackInSlot(1)};
        ItemStack fuel = this.handler.getStackInSlot(2);

        if (this.isBurning() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty()) {
            if (!this.isBurning() && this.canSmelt()) {
                this.burnTime = getItemBurnTime(fuel);
                this.currentBurnTime = burnTime;

                if (this.isBurning() && !fuel.isEmpty()) {
                    Item item = fuel.getItem();
                    fuel.shrink(1);

                    if (fuel.isEmpty()) {
                        ItemStack item1 = item.getContainerItem(fuel);
                        this.handler.setStackInSlot(2, item1);
                    }
                }
            }
        }

        if (this.isBurning() && this.canSmelt() && cookTime > 0) {
            cookTime++;
            if (cookTime == totalCookTime) {
//                if(handler.getStackInSlot(3).getCount() > 0)
//                {
//                    ItemStack output = CustormFurnaceRecipes.getInstance().getSinteringResult(inputs[0], inputs[1]);
//                    handler.getStackInSlot(3).grow(output.getCount());
//                }
//                else
//                {
//                    handler.insertItem(3, smelting, false);
//                }
                ItemHandlerHelper.insertItemStacked(handler2, new ItemStack(Blocks.PLANKS, 16), false);

                smelting = ItemStack.EMPTY;
                cookTime = 0;
                return;
            }
        } else {
            if (this.canSmelt() && this.isBurning()) {
                ItemStack output = CustormFurnaceRecipes.getInstance().getSinteringResult(inputs[0], inputs[1]);
                if (!output.isEmpty()) {
                    smelting = output;
                    cookTime++;
                    Block block1 = Block.getBlockFromItem(inputs[0].getItem());
                    //Wogg
                    if (block1 == Blocks.LOG) {
                        inputs[0].shrink(1);
                        if (((ItemBlade) inputs[1].getItem()).onUse()) {
                            ((ItemBlade) inputs[1].getItem()).setUsed(((0)));
                            inputs[1].setCount(0);

                        } else
                            ((ItemBlade) inputs[1].getItem()).setUsed(((ItemBlade) inputs[1].getItem()).getUsed() + 1);
                    }
                }
                handler.setStackInSlot(0, inputs[0]);
                handler.setStackInSlot(1, inputs[1]);
            }
        }
    }

    private boolean canSmelt() {
        if (((ItemStack) this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack) this.handler.getStackInSlot(1)).isEmpty())
            return false;
        else {
            ItemStack result = CustormFurnaceRecipes.getInstance().getSinteringResult((ItemStack) this.handler.getStackInSlot(0), (ItemStack) this.handler.getStackInSlot(1));
            if (result.isEmpty()) return false;
            else {
                ItemStack output = (ItemStack) this.handler.getStackInSlot(3);
                if (output.isEmpty()) return true;
                if (!output.isItemEqual(result)) return false;
                int res = output.getCount() + result.getCount();
                return res <= 64 && res <= output.getMaxStackSize();
            }
        }
    }

    public static int getItemBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) return 0;
        else {
            Item item = fuel.getItem();

//            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
//            {
//                Block block = Block.getBlockFromItem(item);
//
//                if (block == Blocks.WOODEN_SLAB) return 150;
//                if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
//                if (block == Blocks.COAL_BLOCK) return 16000;
//            }

//            if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
//            if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
//            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
//            if (item == Items.STICK) return 100;
//            if (item == Items.COAL) return 1600;
//            if (item == Items.LAVA_BUCKET) return 20000;
//            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
//            if (item == Items.BLAZE_ROD) return 2400;
            if (item == CommonProxy.fuelContainer) return 1500;
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
                return this.totalCookTime;
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
                this.totalCookTime = value;
        }
    }
}