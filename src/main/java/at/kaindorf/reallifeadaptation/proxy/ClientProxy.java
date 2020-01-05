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
        keyBindings[0] = new KeyBinding("key.forward.desc", Keyboard.KEY_UP, "key.f1.category");
        keyBindings[1] = new KeyBinding("key.backward.desc", Keyboard.KEY_DOWN, "key.f1.category");
        keyBindings[2] = new KeyBinding("key.left.desc", Keyboard.KEY_LEFT, "key.f1.category");
        keyBindings[3] = new KeyBinding("key.right.desc", Keyboard.KEY_RIGHT, "key.f1.category");

// register all the key bindings
        for (int i = 0; i < keyBindings.length; ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }
}
