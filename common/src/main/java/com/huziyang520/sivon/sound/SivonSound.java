package com.huziyang520.sivon.sound;

import com.huziyang520.sivon.Sivon;
import com.huziyang520.sivon.SivonConfig;
import net.minecraft.sounds.SoundEvent;

import java.util.Set;

public final class SivonSound {

    private static final Set<String> REPLACED = Set.of(
            "minecraft:entity.ice_ball.throw",
            "minecraft:entity.ice_ball.break",
            "minecraft:block.ice.ambient");

    private SivonSound() {
    }

    public static SoundEvent replace(SoundEvent event) {
        if (event == null || !SivonConfig.iceSoundsEnabled()) {
            return event;
        }
        return REPLACED.contains(event.location().toString()) ? Sivon.iceSound() : event;
    }
}
