package user11681.scale.mixin;

import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.scale.Scale;

@Mixin(GameOptions.class)
abstract class GameOptionsMixin {
    @Inject(method = "load", at = @At("RETURN"))
    public void changeDefaultScale(CallbackInfo info) {
        Scale.instance.reload((GameOptions) (Object) this);
    }
}
