package at.kaindorf.reallifeadaptation.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDouble extends Block {
    public static final PropertyEnum<BlockDouble.EnumBlockHalf> HALF = PropertyEnum.<BlockDouble.EnumBlockHalf>create("half", BlockDouble.EnumBlockHalf.class);

    public BlockDouble(Material materialIn, String name) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockDouble.EnumBlockHalf.LOWER));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(HALF) == BlockDouble.EnumBlockHalf.UPPER)
        {
            return Items.AIR;
        }
        return Items.APPLE;

    }

    public void placeAt(World worldIn, BlockPos lowerPos, BlockDoublePlant.EnumPlantType variant, int flags)
    {
        worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, BlockDouble.EnumBlockHalf.LOWER), flags);
        worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, BlockDouble.EnumBlockHalf.UPPER), flags);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockDouble.EnumBlockHalf.UPPER), 2);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BlockDouble.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, BlockDouble.EnumBlockHalf.LOWER);
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HALF) == BlockDouble.EnumBlockHalf.UPPER)
        {
            i = i | 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF});
    }

    public static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }

    @Override
    public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, Predicate<IBlockState> target) {
        return true;
    }
}
