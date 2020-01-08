package at.kaindorf.reallifeadaptation.init;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.entity.EntityF1;
import at.kaindorf.reallifeadaptation.entity.EntityF2;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void registerEntities(){

        registerEntity("car", EntityF1.class, 130, 50, 3093151, 000000);
        registerEntity("car2", EntityF2.class, 130, 50, 3093151, 111111);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(RealLifeAdaptation.MODID + ":" + name), entity, name, id, RealLifeAdaptation.instance, range, 1, true, color1, color2);
    }
}
