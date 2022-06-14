package ga.beycraft.greasengears.client;

import com.mrcrayfish.vehicle.client.EntityRayTracer;
import com.mrcrayfish.vehicle.client.render.AbstractVehicleRenderer;
import com.mrcrayfish.vehicle.client.render.EntityVehicleRenderer;
import com.mrcrayfish.vehicle.client.render.VehicleRenderRegistry;
import com.mrcrayfish.vehicle.entity.VehicleEntity;
import com.mrcrayfish.vehicle.entity.VehicleProperties;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Function;

public class ClientUtils {

    public static <T extends VehicleEntity & EntityRayTracer.IEntityRayTraceable> void registerVehicleRenderer(EntityType<T> type, Function<VehicleProperties, AbstractVehicleRenderer<T>> rendererFunction) {
        VehicleProperties properties = VehicleProperties.get(type);
        AbstractVehicleRenderer<T> renderer = (AbstractVehicleRenderer)rendererFunction.apply(properties);
        RenderingRegistry.registerEntityRenderingHandler(type, (manager) -> {
            return new EntityVehicleRenderer(manager, renderer);
        });
        VehicleRenderRegistry.registerVehicleRendererFunction(type, rendererFunction, renderer);
        EntityRayTracer.IRayTraceTransforms transforms = renderer.getRayTraceTransforms();
        if (transforms != null) {
            EntityRayTracer.instance().registerTransforms(type, transforms);
        }

    }
}
