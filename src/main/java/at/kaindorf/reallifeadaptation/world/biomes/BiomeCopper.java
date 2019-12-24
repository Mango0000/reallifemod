package at.kaindorf.reallifeadaptation.world.biomes;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import at.kaindorf.reallifeadaptation.world.generation.generators.WorldGenRubberTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

import java.util.Random;

public class BiomeCopper extends Biome
{
    private static final WorldGenRubberTree RUBBER_TREE = new WorldGenRubberTree();
    public BiomeCopper()
    {
        super(new BiomeProperties("Copper").setBaseHeight(1.0F).setHeightVariation(1.0F).setRainDisabled().setTemperature(1.0F));

        topBlock = CommonProxy.COPPER_DIRT.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator.treesPerChunk = 2;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree)(rand.nextInt(5) > 0 ? RUBBER_TREE : TREE_FEATURE);
    }
}