package anonimousmc.greasengears.mixin.client;

import com.google.gson.JsonObject;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockPart.Deserializer.class)
public abstract class BlockPartDeserializerMixin {

    @Shadow protected abstract Vector3f getVector3f(JsonObject pJson, String pName);

    @Inject(method = "getAngle", at = @At(value = "HEAD"), cancellable = true)
    public void getAngle(JsonObject pObject, CallbackInfoReturnable ci){
        ci.setReturnValue(JSONUtils.getAsFloat(pObject, "angle"));
    }

    @Inject(method = "getFrom", at = @At(value = "HEAD"), cancellable = true)
    public void getFrom(JsonObject pObject, CallbackInfoReturnable ci){
        ci.setReturnValue(this.getVector3f(pObject, "from"));
    }

    @Inject(method = "getTo", at = @At(value = "HEAD"), cancellable = true)
    public void getTo(JsonObject pObject, CallbackInfoReturnable ci){
        ci.setReturnValue(this.getVector3f(pObject, "to"));
    }
}
