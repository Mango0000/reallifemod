package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityDayNightLight;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class BlockDayNightLight extends BlockStreetLightBlock implements ITileEntityProvider {
    public BlockDayNightLight(String name, Material materialIn, boolean isOn) {
        super(materialIn, name, isOn);
        GameRegistry.registerTileEntity(TileEntityDayNightLight.class,
                new ResourceLocation(RealLifeAdaptation.MODID, "day_night_block"));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDayNightLight();
    }

    public void setState(boolean stateType, World worldIn, BlockPos pos) {
        if (stateType) {
            worldIn.setBlockState(pos, CommonProxy.lit_day_night_block.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
        } else {
            worldIn.setBlockState(pos, CommonProxy.day_night_block.getDefaultState().withProperty(HALF, BlockStreetLightBlock.EnumBlockHalf.UPPER), 2);
        }
    }
}
