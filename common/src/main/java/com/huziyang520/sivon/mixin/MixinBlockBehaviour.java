package com.huziyang520.sivon.mixin;

import com.huziyang520.sivon.Sivon;
import com.huziyang520.sivon.SivonConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour {

    @Unique
    private static SoundType sivon$iceSoundType;

    @Inject(method = "getSoundType", at = @At("RETURN"), cancellable = true)
    private void sivon$replaceIceSound(BlockState state, CallbackInfoReturnable<SoundType> info) {
        if (!state.is(BlockTags.ICE) || !SivonConfig.iceSoundsEnabled()) {
            return;
        }
        if (sivon$iceSoundType == null) {
            SoundEvent event = Sivon.iceSound();
            sivon$iceSoundType = new SoundType(1.0F, 1.0F, event, event, event, event, event);
        }
        info.setReturnValue(sivon$iceSoundType);
    }
}
