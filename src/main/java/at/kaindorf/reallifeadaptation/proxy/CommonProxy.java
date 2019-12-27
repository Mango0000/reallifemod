package at.kaindorf.reallifeadaptation.proxy;


import at.kaindorf.reallifeadaptation.Machines.*;
import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.blocks.*;
import at.kaindorf.reallifeadaptation.blocks.trees.*;
import at.kaindorf.reallifeadaptation.items.*;
import at.kaindorf.reallifeadaptation.items.armor.ArmorBase;
import at.kaindorf.reallifeadaptation.potions.BeerPotion;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityCustomFurnace;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityGenerator;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityGeneratorUpgrade;
import at.kaindorf.reallifeadaptation.tileentity.TileEntityHochofen;
import at.kaindorf.reallifeadaptation.util.handlers.GuiHandler;
import at.kaindorf.reallifeadaptation.world.biomes.BiomeCopper;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

@Mod.EventBusSubscriber(modid = RealLifeAdaptation.MODID)
public class CommonProxy {

    @Mod.Instance
    public static CommonProxy instance;

    public static final Block electricstreetlight = new BlockElectricStreetLight("electric_street_light", Material.ROCK, false);
    public static final Block lit_electricstreetlight = new BlockElectricStreetLight("lit_electric_street_light", Material.ROCK, true);
    public static final Item fuelContainer = new FuelItem("fuelcontainer");
    public static final Item rubber = new ItemRubber("rubber");
    public static final Item rubberbucket = new ItemRubberBucket("rubberbucket");
    public static final Block compressedcoal = new BlockCompressedCoal(Material.ROCK, "compressedcoal");
    public static final Block rafenerie_block = new MachineRafenerie("rafenerie_block", Material.ROCK);
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
    public static final Block CUSTOM_FURNACE = new MachineSawmill("custom_furnace");
    public static final Block GENERATOR = new MachineGenerator("generator");
    public static final Block GENERATOR_UPGRADE = new MachineGeneratorUpgrade("generator_upgrade");
    public static final Item STEEL_INGOT = new ItemSteelIngot("steel_ingot");
    //tileentity
    public static final TileEntity tileEntityGenerator = new TileEntityGenerator();
    public static final TileEntity tileEntityGeneratorupgrade = new TileEntityGeneratorUpgrade();
    public static final TileEntity te = new TileEntityCustomFurnace();
    public static final TileEntity tileEntityHochofen = new TileEntityHochofen();
    public static final Item SAWMIL_BLADE = new ItemBlade("sawmil_blade", 4);
    public static final Block BLOCK_DOUBLE = new BlockStreetLightBlock(Material.ROCK, "street_light_block", false);
    public static final Block LIT_BLOCK_DOUBLE = new BlockStreetLightBlock(Material.ROCK, "lit_street_light_block", true);
    public static final Block MACHINE_HOCHOFEN = new MachineHochofen("machine_hochofen");
    //tree
    public static final Block COPPER_DIRT = new BlockDirtBase("copper_dirt");
    public static final Block COPPER_LEAVES = new BlockLeavesBase("copper_leaves");
    public static final Block COPPER_LOG = new BlockLogBase("copper_log");
    public static final Block COPPER_PLANK = new BlockCopperPlank("copper_planks", Material.WOOD);
    public static final Block COPPER_SAPLING = new BlockSaplingBase("copper_sapling");
    public static final Biome COPPER = new BiomeCopper();
    //Armor
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_RUBBER = EnumHelper.addArmorMaterial("armor_material_rubber", RealLifeAdaptation.MODID + ":" + "rubber", 10,
            new int[] {2,5,7,3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f);
    public static final Item RUBBER_HELMET = new ArmorBase("rubber_helmet", ARMOR_MATERIAL_RUBBER, 1, EntityEquipmentSlot.HEAD);
    public static final Item RUBBER_CHEST = new ArmorBase("rubber_chestplate", ARMOR_MATERIAL_RUBBER, 1, EntityEquipmentSlot.CHEST);
    public static final Item RUBBER_LEGGINGS = new ArmorBase("rubber_leggings", ARMOR_MATERIAL_RUBBER, 2, EntityEquipmentSlot.LEGS);
    public static final Item RUBBER_BOOTS = new ArmorBase("rubber_boots",ARMOR_MATERIAL_RUBBER, 1, EntityEquipmentSlot.FEET);
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
        registerBiomes();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(electricstreetlight, compressedcoal,
                rafenerie_block, day_night_block, traffic_light_block, GENERATOR, CUSTOM_FURNACE, BLOCK_DOUBLE,
                orange_traffic_light_lamp, red_traffic_light_lamp, COPPER_DIRT,COPPER_LEAVES, COPPER_LOG, COPPER_PLANK,
                COPPER_SAPLING, COPPER_SAPLING, LIT_BLOCK_DOUBLE, lit_electricstreetlight, lit_day_night_block,
                GENERATOR_UPGRADE, MACHINE_HOCHOFEN);
    }


    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemBlock(electricstreetlight).setRegistryName(electricstreetlight.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(compressedcoal).setRegistryName(compressedcoal.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(rafenerie_block).setRegistryName(rafenerie_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(day_night_block).setRegistryName(day_night_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(traffic_light_block).setRegistryName(traffic_light_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(CUSTOM_FURNACE).setRegistryName(CUSTOM_FURNACE.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(GENERATOR).setRegistryName(GENERATOR.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(BLOCK_DOUBLE).setRegistryName(BLOCK_DOUBLE.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(orange_traffic_light_lamp).setRegistryName(orange_traffic_light_lamp.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(red_traffic_light_lamp).setRegistryName(red_traffic_light_lamp.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(COPPER_DIRT).setRegistryName(COPPER_DIRT.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(COPPER_LEAVES).setRegistryName(COPPER_LEAVES.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(COPPER_LOG).setRegistryName(COPPER_LOG.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(COPPER_PLANK).setRegistryName(COPPER_PLANK.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(COPPER_SAPLING).setRegistryName(COPPER_SAPLING.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(LIT_BLOCK_DOUBLE).setRegistryName(LIT_BLOCK_DOUBLE.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(lit_electricstreetlight).setRegistryName(lit_electricstreetlight.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(lit_day_night_block).setRegistryName(lit_day_night_block.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(GENERATOR_UPGRADE).setRegistryName(GENERATOR_UPGRADE.getRegistryName()));
        event.getRegistry().registerAll(new ItemBlock(MACHINE_HOCHOFEN).setRegistryName(MACHINE_HOCHOFEN.getRegistryName()));

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(fuelContainer, rubber, rubberbucket, oilbucket, SAWMIL_BLADE, RUBBER_BOOTS,
                RUBBER_CHEST, RUBBER_HELMET, RUBBER_LEGGINGS, STEEL_INGOT);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(fuelContainer);
        registerRender(rubber);
        registerRender(rubberbucket);
        registerRender(oilbucket);
        registerRender(RUBBER_BOOTS);
        registerRender(RUBBER_CHEST);
        registerRender(RUBBER_HELMET);
        registerRender(RUBBER_LEGGINGS);
        registerRender(SAWMIL_BLADE);
        registerRender(STEEL_INGOT);
        registerRender(Item.getItemFromBlock(electricstreetlight));
        registerRender(Item.getItemFromBlock(compressedcoal));
        registerRender(Item.getItemFromBlock(rafenerie_block));
        registerRender(Item.getItemFromBlock(day_night_block));
        registerRender(Item.getItemFromBlock(traffic_light_block));
        registerRender(Item.getItemFromBlock(CUSTOM_FURNACE));
        registerRender(Item.getItemFromBlock(GENERATOR));
        registerRender(Item.getItemFromBlock(BLOCK_DOUBLE));
        registerRender(Item.getItemFromBlock(orange_traffic_light_lamp));
        registerRender(Item.getItemFromBlock(red_traffic_light_lamp));
        registerRender(Item.getItemFromBlock(COPPER_DIRT));
        registerRender(Item.getItemFromBlock(COPPER_LEAVES));
        registerRender(Item.getItemFromBlock(COPPER_LOG));
        registerRender(Item.getItemFromBlock(COPPER_PLANK));
        registerRender(Item.getItemFromBlock(COPPER_SAPLING));
        registerRender(Item.getItemFromBlock(LIT_BLOCK_DOUBLE));
        registerRender(Item.getItemFromBlock(lit_electricstreetlight));
        registerRender(Item.getItemFromBlock(lit_day_night_block));
        registerRender(Item.getItemFromBlock(GENERATOR_UPGRADE));
        registerRender(Item.getItemFromBlock(MACHINE_HOCHOFEN));

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
        GameRegistry.registerTileEntity(TileEntityGenerator.class, new ResourceLocation(RealLifeAdaptation.MODID + ":generator"));
        GameRegistry.registerTileEntity(TileEntityGeneratorUpgrade.class, new ResourceLocation(RealLifeAdaptation.MODID + ":generator_upgrade"));
        GameRegistry.registerTileEntity(TileEntityHochofen.class, new ResourceLocation(RealLifeAdaptation.MODID + ":machine_hochofen"));
    }

    private static void registerPotionMixes(){
        PotionHelper.addMix(BEER_POTION, Items.REDSTONE, LONG_BEER_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.APPLE, BEER_POTION);
    }

    public static void registerBiomes()
    {
        initBiome(COPPER, "Copper", BiomeType.WARM, Type.HILLS, Type.MOUNTAIN, Type.DRY);
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }


}
