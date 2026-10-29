package com.huziyang520.sivon.mixin;

import com.huziyang520.sivon.Sivon;
import com.huziyang520.sivon.SivonConfig;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Replaces the sound type of every block in #minecraft:ice (ice, packed ice, blue ice, frosted ice).
 */
@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour {

    @Inject(method = "getSoundType", at = @At("RETURN"), cancellable = true)
    private void sivon$iceSoundType(BlockState state, CallbackInfoReturnable<SoundType> info) {
        if (state.is(BlockTags.ICE) && SivonConfig.iceSoundsEnabled()) {
            info.setReturnValue(Sivon.iceSoundType());
        }
    }
}
