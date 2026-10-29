package com.huziyang520.sivon.mixin;

import com.huziyang520.sivon.Constants;
import com.huziyang520.sivon.SivonConfig;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSoundInstance.class)
public abstract class MixinAbstractSoundInstance {

    @Shadow
    @Final
    protected Identifier identifier;

    @Unique
    private float sivon$rate;
    @Unique
    private boolean sivon$rated;

    @Inject(method = "getPitch", at = @At("RETURN"), cancellable = true)
    private void sivon$randomIcePitch(CallbackInfoReturnable<Float> cir) {
        if (!Constants.MOD_ID.equals(identifier.getNamespace()) || !"block.ice".equals(identifier.getPath())) {
            return;
        }
        if (!SivonConfig.iceSoundsEnabled()) {
            return;
        }
        if (!sivon$rated) {
            sivon$rated = true;
            float up = SivonConfig.rateUp();
            float down = SivonConfig.rateDown();
            float spread = up + down;
            float offset = spread <= 0.0F ? 0.0F : RandomSource.create().nextFloat() * spread - down;
            sivon$rate = SivonConfig.baseRate() + offset;
        }
        cir.setReturnValue(Math.max(0.05F, cir.getReturnValue() * sivon$rate));
    }
}
