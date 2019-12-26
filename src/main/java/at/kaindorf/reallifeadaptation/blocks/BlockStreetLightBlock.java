package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockStreetLightBlock extends Block {
    public static final PropertyEnum<BlockStreetLightBlock.EnumBlockHalf> HALF = PropertyEnum.<BlockStreetLightBlock.EnumBlockHalf>create("half", BlockStreetLightBlock.EnumBlockHalf.class);
    public final boolean isOn;

    public BlockStreetLightBlock(Material materialIn, String name, boolean isOn) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        if (!name.contains("lit")) {
            setCreativeTab(RealLifeAdaptation.BLOCK_TAB);
        }
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.LOWER));
        this.isOn = isOn;
        if (isOn) {
            this.setLightLevel(1.0f);
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2));
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state.getValue(HALF) == BlockStreetLightBlock.EnumBlockHalf.UPPER) {
            return Items.AIR;
        }
        return Items.APPLE;

    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (state.getValue(HALF) == BlockStreetLightBlock.EnumBlockHalf.UPPER) {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this) {
                worldIn.setBlockToAir(pos);
            } else if (blockIn != this) {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        }/*else if(state.getValue(HALF) == EnumBlockHalf.MIDDLE){
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
            else if (blockIn != this)
            {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
            blockpos = pos.up();
            iblockstate = worldIn.getBlockState(blockpos);

            if (iblockstate.getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
            else if (blockIn != this)
            {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        }*/ else {
            boolean flag1 = false;
            BlockPos blockpos1 = pos.up();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this) {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate1.getBlock() == this) {
                    worldIn.setBlockToAir(blockpos1);
                }
            }
        }
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, EnumBlockHalf.MIDDLE), 2);
        worldIn.setBlockState(pos.up(2), this.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (this.isOn) {
                //macht neue Lampe nicht scheined
                if (worldIn.getBlockState(pos).getValue(HALF) == EnumBlockHalf.UPPER) {
                    worldIn.setBlockState(pos, CommonProxy.BLOCK_DOUBLE.getDefaultState().withProperty(HALF, EnumBlockHalf.UPPER), 2);
                }
            } else {
                if (worldIn.getBlockState(pos).getValue(HALF) == EnumBlockHalf.UPPER) {
                    worldIn.setBlockState(pos, CommonProxy.LIT_BLOCK_DOUBLE.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
                }

            }
        }
        return true;
    }

    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.LOWER);
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (state.getValue(HALF) == BlockStreetLightBlock.EnumBlockHalf.UPPER) {
            i = i | 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{HALF});
    }

    public static enum EnumBlockHalf implements IStringSerializable {
        UPPER,
        MIDDLE,
        LOWER;

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : this == LOWER ? "lower" : "middle";
        }
    }

    @Override
    public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
}
