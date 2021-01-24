package user11681.scale.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.scale.Scale;

@Environment(EnvType.CLIENT)
@Mixin(Option.class)
 abstract class OptionMixin {
    @Inject(method = "method_18548", at = @At("RETURN"))
    private static void fixGuiScale(final GameOptions options, final Integer change, final CallbackInfo info) {
        if (options.guiScale > Scale.instance.maximum) {
            options.guiScale = Scale.instance.minimum;
        }
    }
}
