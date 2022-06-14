package ga.beycraft.greasengears.client;

import com.mrcrayfish.vehicle.client.model.ISpecialModel;
import ga.beycraft.greasengears.GreaseNGears;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ModelLoader;

public enum SpecialModels implements ISpecialModel {
    HELICOPTER_BODY("helicopter_body"), HELICOPTER_PROPELLER("helicopter_propeller");

    private ResourceLocation modelLocation;

    SpecialModels(String modelName) {
        this.modelLocation = new ResourceLocation(GreaseNGears.MOD_ID, "vehicle/" + modelName);
    }

    public void registerModel(){
            ModelLoader.addSpecialModel(modelLocation);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public IBakedModel getModel() {
        return Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
    }
}
