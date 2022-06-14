package ga.beycraft.greasengears;

import com.mrcrayfish.vehicle.entity.VehicleProperties;
import ga.beycraft.greasengears.client.ClientUtils;
import ga.beycraft.greasengears.client.SpecialModels;
import ga.beycraft.greasengears.client.renderers.HelicopterRenderer;
import ga.beycraft.greasengears.common.entities.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreaseNGears.MOD_ID)
public class GreaseNGears
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "greasengears";

    public GreaseNGears() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.REGISTER.register(eventBus);
        eventBus.addListener(this::onCommonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->{
            eventBus.addListener(this::onClientSetup);
            eventBus.addListener(this::register);
        });
    }

    @OnlyIn(Dist.CLIENT)
    public  void register(ModelRegistryEvent event)
    {
        for(SpecialModels model : SpecialModels.values())
        {
            model.registerModel();
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetup(FMLClientSetupEvent event){
        ClientUtils.registerVehicleRenderer(ModEntities.HELICOPTER.get(), HelicopterRenderer::new);
    }
}
