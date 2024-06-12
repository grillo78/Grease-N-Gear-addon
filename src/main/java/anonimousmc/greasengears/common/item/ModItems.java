package anonimousmc.greasengears.common.item;

import anonimousmc.greasengears.GreaseNGears;
import com.mrcrayfish.vehicle.VehicleMod;
import com.mrcrayfish.vehicle.entity.EngineTier;
import com.mrcrayfish.vehicle.entity.EngineType;
import com.mrcrayfish.vehicle.item.EngineItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GreaseNGears.MOD_ID);

    public static final EngineItem TEST_ENGINE = register("test_engine", new EngineItem(EngineType.LARGE_MOTOR, EngineTier.DIAMOND, new Item.Properties().tab(VehicleMod.CREATIVE_TAB)));

    private static <T extends Item> T register(String name, T item) {
        ITEMS.register(name, () -> item);
        return item;
    }
}
