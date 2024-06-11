package anonimousmc.greasengears.client.vehicle.render;

import anonimousmc.greasengears.common.entity.vehicle.AE86Entity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.vehicle.client.SpecialModels;
import com.mrcrayfish.vehicle.client.render.AbstractRenderVehicle;
import com.mrcrayfish.vehicle.client.render.Axis;
import com.mrcrayfish.vehicle.entity.vehicle.ATVEntity;
import com.mrcrayfish.vehicle.util.RenderUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.vector.Quaternion;

public class RenderAE86 extends AbstractRenderVehicle<AE86Entity> {

    private float angle = 0;

    @Override
    public void render(AE86Entity entity, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, float partialTicks, int light) {
        this.renderDamagedPart(entity, VehicleModels.AE86_BODY.getModel(), matrixStack, renderTypeBuffer, light);
        this.renderDamagedPart(entity, VehicleModels.AE86_DOOR1.getModel(), matrixStack, renderTypeBuffer, light);
        this.renderDamagedPart(entity, VehicleModels.AE86_DOOR2.getModel(), matrixStack, renderTypeBuffer, light);
        this.renderDamagedPart(entity, VehicleModels.AE86_HOOD.getModel(), matrixStack, renderTypeBuffer, light);
        matrixStack.pushPose();
        matrixStack.translate(-0.25D, -0.1D, 0.45D);
        matrixStack.mulPose(Axis.POSITIVE_X.rotationDegrees(-80));
        matrixStack.translate(0.0D, -0.025D, 0.0D);
        float wheelAngle = entity.prevRenderWheelAngle + (entity.renderWheelAngle - entity.prevRenderWheelAngle) * partialTicks;
        float wheelAngleNormal = wheelAngle / 45.0F;
        float turnRotation = wheelAngleNormal * 15.0F;
        matrixStack.mulPose(Axis.POSITIVE_Y.rotationDegrees(turnRotation));
        matrixStack.scale(0.75F,0.75F,0.75F);
        RenderUtil.renderColoredModel(SpecialModels.GO_KART_STEERING_WHEEL.getModel(), ItemCameraTransforms.TransformType.NONE, false, matrixStack, renderTypeBuffer, entity.getColor(), light, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
        matrixStack.pushPose();
        matrixStack.translate(0, -0.15D, 0.9D);
        matrixStack.scale(0.35F,0.35F,0.35F);
        RenderUtil.renderColoredModel(RenderUtil.getEngineModel(entity), ItemCameraTransforms.TransformType.NONE, false, matrixStack, renderTypeBuffer, entity.getColor(), light, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
        matrixStack.pushPose();
        matrixStack.translate(0,-0.089,1.1805);
        matrixStack.mulPose(new Quaternion(-45,0,0,true));
        matrixStack.translate(0,0.5,0.5);
        matrixStack.pushPose();
        matrixStack.translate(0.4375,0,0);
        this.renderDamagedPart(entity, VehicleModels.AE86_LIGHT1.getModel(), matrixStack, renderTypeBuffer, light);
        matrixStack.popPose();
        matrixStack.pushPose();
        matrixStack.translate(-0.4375,0,0);
        this.renderDamagedPart(entity, VehicleModels.AE86_LIGHT2.getModel(), matrixStack, renderTypeBuffer, light);
        matrixStack.popPose();
        matrixStack.popPose();
        angle -= 25F;
    }
}
