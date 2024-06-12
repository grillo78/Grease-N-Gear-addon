package anonimousmc.greasengears;

import anonimousmc.greasengears.client.util.KeyBinds;
import anonimousmc.greasengears.client.vehicle.render.RenderAE86;
import anonimousmc.greasengears.client.vehicle.render.VehicleModels;
import anonimousmc.greasengears.common.entity.ModEntities;
import anonimousmc.greasengears.common.entity.vehicle.AE86Entity;
import anonimousmc.greasengears.network.PacketHandler;
import anonimousmc.greasengears.network.message.ToggleLights;
import com.mrcrayfish.vehicle.client.EntityRayTracer;
import com.mrcrayfish.vehicle.client.render.*;
import com.mrcrayfish.vehicle.client.render.vehicle.RenderATV;
import com.mrcrayfish.vehicle.entity.VehicleEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreaseNGears.MOD_ID)
public class GreaseNGears
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "greasengears";

    public GreaseNGears() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(eventBus);
        eventBus.addListener(this::onCommonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->{
            eventBus.addListener(this::onClientSetup);
            eventBus.addListener(this::register);
            MinecraftForge.EVENT_BUS.addListener(this::onKeyPressed);
        });
    }
    @OnlyIn(Dist.CLIENT)
    private void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (KeyBinds.TOGGLE_LIGHTS.isDown() && event.getAction() == GLFW.GLFW_PRESS) {
            PacketHandler.INSTANCE.sendToServer(new ToggleLights());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public  void register(ModelRegistryEvent event)
    {
        for (VehicleModels model: VehicleModels.values()) {
            if(model.isSpecialModel())
                ModelLoader.addSpecialModel(model.getModelLocation());
        }
    }

    @OnlyIn(Dist.CLIENT)
    private <T extends VehicleEntity & EntityRayTracer.IEntityRayTraceable, R extends AbstractRenderVehicle<T>> void registerVehicleRender(EntityType<T> type, RenderVehicleWrapper<T, R> wrapper) {
        RenderingRegistry.registerEntityRenderingHandler(type, (manager) -> {
            return new RenderEntityVehicle(manager, wrapper);
        });
        VehicleRenderRegistry.registerRenderWrapper(type, wrapper);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        PacketHandler.init();
        AE86Entity.registerProperties();
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetup(FMLClientSetupEvent event){
        KeyBinds.registerKeys();
        this.registerVehicleRender((EntityType) ModEntities.AE86, new RenderLandVehicleWrapper(new RenderAE86()));
        AE86Entity.registerRayTraceConstructors();
    }
}
