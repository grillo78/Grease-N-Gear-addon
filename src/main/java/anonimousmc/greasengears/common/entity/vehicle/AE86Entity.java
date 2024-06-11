package anonimousmc.greasengears.common.entity.vehicle;

import anonimousmc.greasengears.common.entity.ModEntities;
import com.mrcrayfish.vehicle.client.render.Wheel;
import com.mrcrayfish.vehicle.common.Seat;
import com.mrcrayfish.vehicle.common.entity.PartPosition;
import com.mrcrayfish.vehicle.entity.EngineType;
import com.mrcrayfish.vehicle.entity.LandVehicleEntity;
import com.mrcrayfish.vehicle.entity.VehicleProperties;
import com.mrcrayfish.vehicle.init.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class AE86Entity extends LandVehicleEntity {
    public AE86Entity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.setMaxSpeed(15.0F);
        this.setTurnSensitivity(12);
        this.maxUpStep = 1.0F;
    }

    public SoundEvent getMovingSound() {
        return (SoundEvent) ModSounds.GO_KART_ENGINE_MONO.get();
    }

    @Override
    public Vector3d getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vector3d position = position();

        position = position.add(getViewVector(0).multiply(1.5, 1.5, 1.5).yRot((float) (getProperties().getSeats().get(seatTracker.getSeatIndex(pLivingEntity.getUUID())).isDriverSeat() ? -Math.PI / 2 : Math.PI / 2))).add(0, 0.5, 0);

        return position;
    }

    public SoundEvent getRidingSound() {
        return (SoundEvent) ModSounds.GO_KART_ENGINE_STEREO.get();
    }

    public EngineType getEngineType() {
        return EngineType.LARGE_MOTOR;
    }

    public float getMinEnginePitch() {
        return 0.8F;
    }

    public float getMaxEnginePitch() {
        return 1.6F;
    }

    public boolean canBeColored() {
        return true;
    }

    public static void registerProperties() {
        VehicleProperties properties = new VehicleProperties();
        properties.setFrontAxelVec(0.0D, 14.5D);
        properties.setRearAxelVec(0.0D, -14.5D);
        properties.setWheelOffset(4.5F);
        properties.setBodyPosition(new PartPosition(1.7D));
        properties.setFuelPortPosition(new PartPosition(-9D, 3.5D, -17D, 0.0D, -90.0D, 0.0D, 0.2D));
        properties.setKeyPortPosition(new PartPosition(5, 15, 23.5, -67.5D, 0.0D, 0.0D, 0.5D));
        properties.addSeat(new Seat(new Vector3d(4.0D, -1.0D, 2.0D), true));
        properties.addSeat(new Seat(new Vector3d(-5.0D, -1.0D, 2.0D)));
        properties.addWheel(Wheel.Side.LEFT, Wheel.Position.FRONT, 6.0F, 15.5F, 1.5F, true, true);
        properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.FRONT, 6.0F, 15.5F, 1.5F, true, true);
        properties.addWheel(Wheel.Side.LEFT, Wheel.Position.REAR, 6.0F, -12.5F, 1.5F, true, true);
        properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.REAR, 6.0F, -12.5F, 1.5F, true, true);

        VehicleProperties.setProperties(ModEntities.AE86, properties);
    }
}
