package anonimousmc.greasengears.common.entity;


import anonimousmc.greasengears.GreaseNGears;
import anonimousmc.greasengears.common.entity.vehicle.AE86Entity;
import com.mrcrayfish.vehicle.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, GreaseNGears.MOD_ID);

    public static final EntityType<AE86Entity> AE86 = registerVehicle("ae86",AE86Entity::new, 2.0F, 2.0F);

    private static  <E extends Entity, T extends EntityType<?>> T registerVehicle(String name, BiFunction<EntityType<E>, World, E> function, float width, float height) {
        T type = (T) EntityUtil.buildVehicleType(new ResourceLocation(GreaseNGears.MOD_ID, name), function, width, height, true);
        ENTITIES.register(name, () -> type);
        return type;
    }
}