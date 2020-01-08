package at.kaindorf.reallifeadaptation.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
public static KeyBinding[] keyBindings;;
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        //OBJLoader.INSTANCE.addDomain(RealLifeAdaptation.MODID);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        keyBindings = new KeyBinding[4];

// instantiate the key bindings
        keyBindings[0] = new KeyBinding("forward", Keyboard.KEY_UP, "f1 car");
        keyBindings[1] = new KeyBinding("backward", Keyboard.KEY_DOWN, "f1 car");
        keyBindings[2] = new KeyBinding("left", Keyboard.KEY_LEFT, "f1 car");
        keyBindings[3] = new KeyBinding("right", Keyboard.KEY_RIGHT, "f1 car");

// register all the key bindings
        for (int i = 0; i < keyBindings.length; ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }
}
