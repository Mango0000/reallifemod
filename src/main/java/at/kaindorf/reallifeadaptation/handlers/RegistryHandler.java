package at.kaindorf.reallifeadaptation.handlers;

import at.kaindorf.reallifeadaptation.init.EntityInit;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RegistryHandler {
    public static void preInitRegistries(){
        EntityInit.registerEntities();
        RenderHandler.registerEntityRenders();
    }
}
