package com.huziyang520.sivon.mixin;

import com.huziyang520.sivon.SivonConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.sounds.BlockSoundSet;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/** 26.4 replaced SoundType with the block_sound_set registry, so the ice sound is swapped by key. */
@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour264 {

    @Unique
    private static final ResourceKey<BlockSoundSet> SIVON_ICE = ResourceKey.create(Registries.BLOCK_SOUND_SET,
            Identifier.fromNamespaceAndPath("sivon", "ice"));

    @Inject(method = "getSounds", at = @At("RETURN"), cancellable = true)
    private void sivon$iceSoundSet(BlockState state, CallbackInfoReturnable<Optional<ResourceKey<BlockSoundSet>>> info) {
        if (state.is(BlockTags.ICE) && SivonConfig.iceSoundsEnabled()) {
            info.setReturnValue(Optional.of(SIVON_ICE));
        }
    }
}
