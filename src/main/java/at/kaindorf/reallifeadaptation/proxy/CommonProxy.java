package at.kaindorf.reallifeadaptation.proxy;


import at.kaindorf.reallifeadaptation.Machines.MachineCustomFurnace;
import at.kaindorf.reallifeadaptation.Machines.MachineIronFurnace;
import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.blocks.*;
import at.kaindorf.reallifeadaptation.items.*;
import at.kaindorf.reallifeadaptation.potions.BeerPotion;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityCustomFurnace;
import at.kaindorf.reallifeadaptation.util.handlers.GuiHandler;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = RealLifeAdaptation.MODID)
public class CommonProxy {

    @Mod.Instance
    public static CommonProxy instance;

    //    public static Item KEY_GRAY = new KeyItem("graykey");
    public static final Block streetlightblock =
            new StreetLightBlock("street_light_block", Material.ROCK, false);
    public static final Block lit_streetlightblock =
            new StreetLightBlock("lit_street_light_block", Material.ROCK, true);
    public static final Block electricstreetlight = new ElectricStreetLight(false);
    public static final Block lit_electricstreetlight = new ElectricStreetLight(true);
    public static final Item fuelContainer = new FuelItem("fuelcontainer");
    public static final Item rubber = new ItemRubber("rubber");
    public static final Item rubberbucket = new ItemRubberBucket("rubberbucket");
    public static final Block compressedcoal = new BlockCompressedCoal(Material.ROCK, "compressedcoal");
    public static final Block rafenerie_block = new BlockRafenerie("rafenerie_block", Material.ROCK);
    public static final Item oilbucket = new ItemOilBucket("oilbucket");
    public static final Block lit_day_night_block = new BlockDayNightLight("lit_day_night_block", Material.ROCK, true);
    public static final Block day_night_block = new BlockDayNightLight("day_night_block", Material.ROCK, false);
    public static final Potion BEER_POTION_EFFECT = new BeerPotion("beerpotion", false, 13791173,0, 0);

    public static final PotionType BEER_POTION = new PotionType("beerpotion", new PotionEffect[]{new PotionEffect(BEER_POTION_EFFECT, 2400)}).setRegistryName("beerpotion");
    public static final PotionType LONG_BEER_POTION = new PotionType("beerpotion", new PotionEffect[]{new PotionEffect(BEER_POTION_EFFECT, 4000)}).setRegistryName("long_beerpotion");

    public static final Block traffic_light_block =
            new BlockTrafficLight("traffic_light_block", Material.ROCK);
    public static final Block orange_traffic_light_lamp = new BlockTrafficLight("orange_traffic_light_block",Material.ROCK);
    public static final Block red_traffic_light_lamp = new BlockTrafficLight("red_traffic_light_block",Material.ROCK);
    public static final Block machine_iron_furnace = new MachineIronFurnace(false, "machine_iron_furnace");
    public static final Block CUSTOM_FURNACE = new MachineCustomFurnace("custom_furnace");
    public static final TileEntity te = new TileEntityCustomFurnace();
    public static final Item SAWMIL_BLADE = new ItemBlade("sawmil_blade", 4);
    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        IRecipeFactory factory = new IRecipeFactory() {
            @Override
            public IRecipe parse(JsonContext context, JsonObject json) {
                return CraftingHelper.getRecipe(json, context);
            }
        };
        CraftingHelper.register(new ResourceLocation(RealLifeAdaptation.MODID + ":fuelcontainer"),factory);
        CraftingHelper.register(new ResourceLocation(RealLifeAdaptation.MODID + ":compressedcoal"),factory);
        CraftingHelper.register(new ResourceLocation(RealLifeAdaptation.MODID + ":rubberbucket"),factory);
        CraftingHelper.register(new ResourceLocation(RealLifeAdaptation.MODID + ":sawmil_blade"),factory);
        registerPostion();
        registerTileEntities();
        NetworkRegistry.INSTANCE.registerGuiHandler(RealLifeAdaptation.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(electricstreetlight, streetlightblock, compressedcoal,
                rafenerie_block, day_night_block, traffic_light_block, lit_streetlightblock, machine_iron_furnace, CUSTOM_FURNACE);
    }


    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemBlock(electricstreetlight).setRegistryName(electricstreetlight.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(streetlightblock).setRegistryName(streetlightblock.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(compressedcoal).setRegistryName(compressedcoal.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(rafenerie_block).setRegistryName(rafenerie_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(day_night_block).setRegistryName(day_night_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(traffic_light_block).setRegistryName(traffic_light_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(lit_streetlightblock).setRegistryName(lit_streetlightblock.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(machine_iron_furnace).setRegistryName(machine_iron_furnace.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(CUSTOM_FURNACE).setRegistryName(CUSTOM_FURNACE.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(fuelContainer, rubber, rubberbucket, oilbucket, SAWMIL_BLADE);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(fuelContainer);
        registerRender(rubber);
        registerRender(rubberbucket);
        registerRender(oilbucket);
        registerRender(SAWMIL_BLADE);
        registerRender(Item.getItemFromBlock(lit_streetlightblock));
        registerRender(Item.getItemFromBlock(electricstreetlight));
        registerRender(Item.getItemFromBlock(streetlightblock));
        registerRender(Item.getItemFromBlock(compressedcoal));
        registerRender(Item.getItemFromBlock(rafenerie_block));
        registerRender(Item.getItemFromBlock(day_night_block));
        registerRender(Item.getItemFromBlock(traffic_light_block));
        registerRender(Item.getItemFromBlock(machine_iron_furnace));
        registerRender(Item.getItemFromBlock(CUSTOM_FURNACE));


    }


    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerPostion(){
        registerPotion(BEER_POTION, LONG_BEER_POTION, BEER_POTION_EFFECT);
        registerPotionMixes();
    }

    public static void registerPotion(PotionType defualtPotion, PotionType longPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defualtPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCustomFurnace.class, new ResourceLocation(RealLifeAdaptation.MODID + ":custom_furnace"));
    }

    private static void registerPotionMixes(){
        PotionHelper.addMix(BEER_POTION, Items.REDSTONE, LONG_BEER_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.APPLE, BEER_POTION);
    }


}
