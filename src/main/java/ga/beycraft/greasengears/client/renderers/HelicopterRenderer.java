package ga.beycraft.greasengears.client.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.vehicle.client.EntityRayTracer;
import com.mrcrayfish.vehicle.client.render.AbstractHelicopterRenderer;
import com.mrcrayfish.vehicle.entity.VehicleProperties;
import ga.beycraft.greasengears.client.SpecialModels;
import ga.beycraft.greasengears.common.entities.HelicopterEntity;
import ga.beycraft.greasengears.common.entities.ModEntities;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

import javax.annotation.Nullable;

public class HelicopterRenderer extends AbstractHelicopterRenderer<HelicopterEntity> {

    public HelicopterRenderer(VehicleProperties defaultProperties) {
        super(defaultProperties);
    }

    @Nullable
    @Override
    public EntityRayTracer.IRayTraceTransforms getRayTraceTransforms() {
        return (entityRayTracer, transforms, parts) ->
        {
            EntityRayTracer.createTransformListForPart(ga.beycraft.greasengears.client.SpecialModels.HELICOPTER_BODY, parts, transforms);
            EntityRayTracer.createFuelPartTransforms(ModEntities.HELICOPTER.get(), com.mrcrayfish.vehicle.client.model.SpecialModels.SMALL_FUEL_DOOR_CLOSED, parts, transforms);
            EntityRayTracer.createKeyPortTransforms(ModEntities.HELICOPTER.get(), parts, transforms);
        };
    }

    @Override
    protected void render(@Nullable HelicopterEntity vehicle, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, float partialTicks, int light) {

        matrixStack.pushPose();
        this.renderDamagedPart(vehicle, SpecialModels.HELICOPTER_BODY.getModel(), matrixStack, renderTypeBuffer, light);
        matrixStack.popPose();

        matrixStack.pushPose();
        matrixStack.translate(0.1,0,0);
        if(vehicle != null)
        {
            float bladeRotation = vehicle.prevBladeRotation + (vehicle.bladeRotation - vehicle.prevBladeRotation) * partialTicks;
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(bladeRotation));
        }
        this.renderDamagedPart(vehicle, SpecialModels.HELICOPTER_PROPELLER.getModel(), matrixStack, renderTypeBuffer, light);
        matrixStack.popPose();
    }

    @Override
    protected void renderFuelPort(@Nullable HelicopterEntity vehicle, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light) {
        matrixStack.scale(0.6F,0.6F,0.6F);
        matrixStack.translate(-0.10,-0.117,0.27);
        super.renderFuelPort(vehicle, matrixStack, renderTypeBuffer, light);
    }
}
