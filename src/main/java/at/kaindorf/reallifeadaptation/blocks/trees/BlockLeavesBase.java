package at.kaindorf.reallifeadaptation.blocks.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.util.interfaces.IHasModel;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeavesBase extends BlockLeaves implements IHasModel {
    public static String type;

    public BlockLeavesBase(String name)
    {
        type = name.replaceAll("_leaves", "").trim();
        System.out.println(type);

        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
       return Item.getItemFromBlock(CommonProxy.COPPER_SAPLING);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) i |= 2;
        if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) i|= 4;
        return i;
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(this);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}

    @Override
    protected int getSaplingDropChance(IBlockState state) {return 10;}

    @Override
    public EnumType getWoodType(int meta) {return null;}

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return NonNullList.withSize(1, new ItemStack(this));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void registerModels() {

    }
}
