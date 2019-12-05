package at.kaindorf.reallifeadaptation.util.handlers;

import at.kaindorf.reallifeadaptation.container.ContainerCustomFurnace;
import at.kaindorf.reallifeadaptation.guis.GuiSinteringFurnace;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityCustomFurnace;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1) return new ContainerCustomFurnace(player.inventory, (TileEntityCustomFurnace) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1) return new GuiSinteringFurnace(player.inventory, (TileEntityCustomFurnace) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
