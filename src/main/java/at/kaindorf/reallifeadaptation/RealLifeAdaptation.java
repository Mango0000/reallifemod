package at.kaindorf.reallifeadaptation;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.tabs.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = RealLifeAdaptation.MODID, name = RealLifeAdaptation.NAME, version = RealLifeAdaptation.VERSION)
public class RealLifeAdaptation {
    @Mod.Instance
    public static RealLifeAdaptation instance;

    public static final String MODID = "reallifeadaptation";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";
    @SidedProxy(clientSide = "at.kaindorf.reallifeadaptation.proxy.ClientProxy",
    serverSide = "at.kaindorf.reallifeadaptation.proxy.ServerProxy")
    private static CommonProxy proxy;
    private static Logger logger;

    public static final CreativeTabs MACHINE_TAB = new TabMachine("machine_tab");
    public static final CreativeTabs TREE_TAB = new TabTrees("tree_tab");
    public static final CreativeTabs BLOCK_TAB = new TabBlocks("block_tab");
    public static final CreativeTabs ITEM_TAB = new TabItems("item_tab");
    public static final CreativeTabs ARMOR_TAB = new TabArmor("armor_tab");


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        proxy.init(event);
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
