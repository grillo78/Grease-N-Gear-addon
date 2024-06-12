package anonimousmc.greasengears.common.entity.vehicle;

import net.minecraft.entity.EntityType;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class PopUpLightCarEntity extends CarEntity {

    private static final DataParameter<Float> POP_UP_LIGHTS = EntityDataManager.defineId(PopUpLightCarEntity.class, DataSerializers.FLOAT);

    private float progressRate = 0.1F;

    public PopUpLightCarEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POP_UP_LIGHTS, 0f);
    }

    @Override
    public void tick() {
        super.tick();
        if (areLightsOn()) {
            if (getPopUpProgress() < 1) {
                setPopUpProgress(MathHelper.clamp(getPopUpProgress() + progressRate, 0, 1));
            }
        } else {
            if (getPopUpProgress() > 0) {
                setPopUpProgress(MathHelper.clamp(getPopUpProgress() - progressRate, 0, 1));
            }
        }
    }

    private void setPopUpProgress(float progress) {
        this.entityData.set(POP_UP_LIGHTS, progress);
    }

    public float getPopUpProgress() {
        return this.entityData.get(POP_UP_LIGHTS);
    }
}
