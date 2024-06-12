package anonimousmc.greasengears.client.util;

import anonimousmc.greasengears.GreaseNGears;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyBinds {

    public static final KeyBinding TOGGLE_LIGHTS = new KeyBinding("key." + GreaseNGears.MOD_ID + ".toggle_lights", GLFW.GLFW_KEY_L, "key." + GreaseNGears.MOD_ID + ".category");

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(TOGGLE_LIGHTS);
    }
}
