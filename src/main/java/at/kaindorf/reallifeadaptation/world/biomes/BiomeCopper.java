package at.kaindorf.reallifeadaptation.world.biomes;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.world.biome.Biome;

public class BiomeCopper extends Biome
{
    public BiomeCopper()
    {
        super(new BiomeProperties("Copper").setBaseHeight(1.0F).setHeightVariation(1.0F).setRainDisabled().setTemperature(1.0F));

        topBlock = CommonProxy.COPPER_DIRT.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator.treesPerChunk = 10;
    }
}