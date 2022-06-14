package ga.beycraft.greasengears.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class HelicopterEntity extends com.mrcrayfish.vehicle.entity.HelicopterEntity {
    protected HelicopterEntity(EntityType<? extends HelicopterEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        this.setFuelCapacity(40000F);
        this.setFuelConsumption(0.5F);
        this.entityData.set(COLOR, 11546150);
    }

    @Override
    public boolean canBeColored()
    {
        return false;
    }
}
