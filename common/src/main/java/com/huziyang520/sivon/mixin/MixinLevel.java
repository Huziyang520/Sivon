package com.huziyang520.sivon.mixin;

import com.huziyang520.sivon.sound.SivonSound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Level.class)
public abstract class MixinLevel {

    @ModifyVariable(method = "playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",
            at = @At("HEAD"), argsOnly = true)
    private SoundEvent sivon$replaceIceBallSound(SoundEvent event) {
        return SivonSound.replace(event);
    }

    @ModifyVariable(method = "playSound(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",
            at = @At("HEAD"), argsOnly = true)
    private SoundEvent sivon$replaceIceBlockAmbient(SoundEvent event) {
        return SivonSound.replace(event);
    }
}
