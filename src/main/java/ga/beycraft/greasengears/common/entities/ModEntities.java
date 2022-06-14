package ga.beycraft.greasengears.common.entities;

import com.mrcrayfish.vehicle.Reference;
import com.mrcrayfish.vehicle.util.VehicleUtil;
import ga.beycraft.greasengears.GreaseNGears;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, GreaseNGears.MOD_ID);

    public static final RegistryObject<EntityType<HelicopterEntity>> HELICOPTER = VehicleUtil.createEntityType(REGISTER, "helicopter", HelicopterEntity::new, 1.5F, 1.0F);

    private static <T extends Entity> EntityType<T> registerEntity(String id, BiFunction<EntityType<T>, World, T> function, float width, float height)
    {
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(width, height).setTrackingRange(256).setUpdateInterval(1).noSummon().fireImmune().setShouldReceiveVelocityUpdates(true).build(id);
        return REGISTER.register(id, () -> type).get();
    }
}
