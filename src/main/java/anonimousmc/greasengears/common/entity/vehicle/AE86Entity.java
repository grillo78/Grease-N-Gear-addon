package anonimousmc.greasengears.common.entity.vehicle;

import anonimousmc.greasengears.client.vehicle.render.VehicleModels;
import anonimousmc.greasengears.common.entity.ModEntities;
import com.mrcrayfish.vehicle.client.EntityRayTracer;
import com.mrcrayfish.vehicle.client.ISpecialModel;
import com.mrcrayfish.vehicle.client.SpecialModels;
import com.mrcrayfish.vehicle.client.render.Wheel;
import com.mrcrayfish.vehicle.common.Seat;
import com.mrcrayfish.vehicle.common.entity.PartPosition;
import com.mrcrayfish.vehicle.entity.VehicleEntity;
import com.mrcrayfish.vehicle.entity.VehicleProperties;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class AE86Entity extends PopUpLightCarEntity {
    public AE86Entity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.setMaxSpeed(15.0F);
        this.setTurnSensitivity(50);
        this.setDriftingMaxTurnAngle(90);
        this.setDismountOffset(1.5F);
        this.maxUpStep = 1.0F;
    }

    @Override
    public void tick() {
        super.tick();
        registerProperties();
    }

    public static void registerRayTraceConstructors() {
        EntityRayTracer.instance().registerTransforms((EntityType<? extends VehicleEntity>) ModEntities.AE86, (entityRayTracer, transforms, parts) ->
        {
            EntityRayTracer.createTransformListForPart(VehicleModels.AE86_BODY, parts, transforms,
                    EntityRayTracer.MatrixTransformation.createScale(1F),
                    EntityRayTracer.MatrixTransformation.createTranslation(0F, -0,0));
            EntityRayTracer.createFuelPartTransforms((EntityType<? extends VehicleEntity>) ModEntities.AE86, SpecialModels.FUEL_DOOR_CLOSED, parts, transforms);
            EntityRayTracer.createKeyPortTransforms((EntityType<? extends VehicleEntity>) ModEntities.AE86, parts, transforms);
        });
    }

    public static void registerProperties() {
        VehicleProperties properties = VehicleProperties.getProperties(ModEntities.AE86) == null ? new VehicleProperties() : VehicleProperties.getProperties(ModEntities.AE86);
        properties.setFrontAxelVec(0.0D, 14.5D);
        properties.setRearAxelVec(0.0D, -14.5D);
        properties.setWheelOffset(4.5F);
        properties.setBodyPosition(new PartPosition(1.7D));
        properties.setFuelPortPosition(new PartPosition(-9D, 3.5D, -17D, 0.0D, -90.0D, 0.0D, 0.2D));
        properties.setKeyPortPosition(new PartPosition(-2, 1, 8, -67.5D, 0.0D, -90.0D, 0.5D));
        if (properties.getWheels().size() == 0) {
            properties.addSeat(new Seat(new Vector3d(4.0D, -1.0D, 1.0D), true));
            properties.addSeat(new Seat(new Vector3d(-5.0D, -1.0D, 1.0D)));
            properties.addWheel(Wheel.Side.LEFT, Wheel.Position.FRONT, 6.0F, 15.5F, 1.5F, true, true);
            properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.FRONT, 6.0F, 15.5F, 1.5F, true, true);
            properties.addWheel(Wheel.Side.LEFT, Wheel.Position.REAR, 6.0F, -12.5F, 1.5F, true, true);
            properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.REAR, 6.0F, -12.5F, 1.5F, true, true);
        }

        VehicleProperties.setProperties(ModEntities.AE86, properties);
    }
}
