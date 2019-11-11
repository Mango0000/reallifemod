package at.kaindorf.reallifeadaptation.blocks;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityDayNightLight;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

public class BlockDayNightLight extends BlockBase implements ITileEntityProvider {
    public BlockDayNightLight(String name, Material materialIn, boolean isOn) {
        super(name, materialIn);
        if(isOn){
            setLightLevel(1.0f);
        }
        GameRegistry.registerTileEntity(TileEntityDayNightLight.class,
                new ResourceLocation(RealLifeAdaptation.MODID, "day_night_block"));
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDayNightLight();
    }

}
