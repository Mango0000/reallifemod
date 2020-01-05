package at.kaindorf.reallifeadaptation.handlers;

import at.kaindorf.reallifeadaptation.entity.EntityF1;
import at.kaindorf.reallifeadaptation.entity.render.RenderF1;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityF1.class, new IRenderFactory<EntityF1>() {
            @Override
            public Render<? super EntityF1> createRenderFor(RenderManager manager) {
                return new RenderF1(manager);
            }
        });
    }
}
