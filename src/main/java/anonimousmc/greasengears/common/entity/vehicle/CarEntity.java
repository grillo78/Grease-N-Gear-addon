package anonimousmc.greasengears.common.entity.vehicle;

import com.mrcrayfish.vehicle.entity.EngineType;
import com.mrcrayfish.vehicle.entity.LandVehicleEntity;
import com.mrcrayfish.vehicle.init.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class CarEntity extends LandVehicleEntity {
    private float dismountOffset = 1.5F;
    private int defaultMaxTurnAngle = 45;
    private int driftingMaxTurnAngle = 90;
    private static final DataParameter<Boolean> TOGGLE_ON_LIGHTS = EntityDataManager.defineId(CarEntity.class, DataSerializers.BOOLEAN);

    public CarEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
    }

    public void setDriftingMaxTurnAngle(int driftingMaxTurnAngle) {
        this.driftingMaxTurnAngle = driftingMaxTurnAngle;
    }

    public void setDefaultMaxTurnAngle(int defaultMaxTurnAngle) {
        this.defaultMaxTurnAngle = defaultMaxTurnAngle;
    }

    @Override
    public void tick() {
        super.tick();
        setMaxTurnAngle(isDrifting()? driftingMaxTurnAngle : defaultMaxTurnAngle);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TOGGLE_ON_LIGHTS, false);
    }

    public boolean areLightsOn(){
        return this.entityData.get(TOGGLE_ON_LIGHTS);
    }

    public void toggleLights(){
        this.entityData.set(TOGGLE_ON_LIGHTS, !areLightsOn());
    }

    public void setDismountOffset(float dismountOffset) {
        this.dismountOffset = dismountOffset;
    }

    public SoundEvent getMovingSound() {
        return ModSounds.GO_KART_ENGINE_MONO.get();
    }

    @Override
    public Vector3d getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vector3d position = position();

        position = position.add(getViewVector(0).multiply(dismountOffset, dismountOffset, dismountOffset).yRot((float) (getProperties().getSeats().get(seatTracker.getSeatIndex(pLivingEntity.getUUID())).isDriverSeat() ? -Math.PI / 2 : Math.PI / 2))).add(0, 0.5, 0);

        return position;
    }

    public SoundEvent getRidingSound() {
        return ModSounds.GO_KART_ENGINE_STEREO.get();
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
}
