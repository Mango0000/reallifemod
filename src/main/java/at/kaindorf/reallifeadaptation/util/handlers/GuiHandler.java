package at.kaindorf.reallifeadaptation.util.handlers;

import at.kaindorf.reallifeadaptation.container.ContainerCustomFurnace;
import at.kaindorf.reallifeadaptation.container.ContainerGenerator;
import at.kaindorf.reallifeadaptation.container.ContainerGeneratorUpgrade;
import at.kaindorf.reallifeadaptation.guis.GuiGenerator;
import at.kaindorf.reallifeadaptation.guis.GuiGeneratorUpgrade;
import at.kaindorf.reallifeadaptation.guis.GuiSinteringFurnace;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityCustomFurnace;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityGenerator;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityGeneratorUpgrade;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1) return new ContainerCustomFurnace(player.inventory, (TileEntityCustomFurnace) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == 2) return new ContainerGenerator(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == 3) return new ContainerGeneratorUpgrade(player.inventory, (TileEntityGeneratorUpgrade) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 1) return new GuiSinteringFurnace(player.inventory, (TileEntityCustomFurnace) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == 2) return new GuiGenerator(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        if(ID == 3) return new GuiGeneratorUpgrade(player.inventory, (TileEntityGeneratorUpgrade) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
