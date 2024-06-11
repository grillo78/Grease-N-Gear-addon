package anonimousmc.greasengears.client.vehicle.render;

import anonimousmc.greasengears.GreaseNGears;
import com.mrcrayfish.vehicle.client.ISpecialModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;

public enum VehicleModels implements ISpecialModel {
    AE86_BODY("ae86_body"),
    AE86_HOOD("ae86_hood"),
    AE86_LIGHT1("ae86_light1"),
    AE86_LIGHT2("ae86_light2"),
    AE86_DOOR1("ae86_door1"),
    AE86_DOOR2("ae86_door2");
    private ResourceLocation modelLocation;
    private boolean specialModel;
    VehicleModels(String modelName) {
        this(new ResourceLocation(GreaseNGears.MOD_ID, "vehicle/" + modelName), true);
    }

    VehicleModels(ResourceLocation resource, boolean specialModel) {
        this.modelLocation = resource;
        this.specialModel = specialModel;
    }

    public boolean isSpecialModel() {
        return specialModel;
    }

    public ResourceLocation getModelLocation() {
        return modelLocation;
    }

    @Override
    public IBakedModel getModel() {
        return Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
    }
}
