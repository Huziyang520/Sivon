package com.huziyang520.sivon;

import com.huziyang520.keenlib.config.KeenConfig;
import com.huziyang520.keenlib.config.KeenConfigApi;
import com.huziyang520.keenlib.text.KeenText;
import net.minecraft.network.chat.Component;

public final class SivonConfig {

    public static final String ICE_SOUNDS = "ice_sounds";
    public static final String SOUND_BASE_RATE = "sound_base_rate";
    public static final String SOUND_RATE_UP = "sound_rate_up";
    public static final String SOUND_RATE_DOWN = "sound_rate_down";

    private SivonConfig() {
    }

    public static void register() {
        KeenConfigApi.business(Constants.MOD_ID, KeenText.trans("gui.sivon.title", "Sivon"))
                .booleanToggle(ICE_SOUNDS, true,
                        KeenText.trans("gui.sivon.ice_sounds", "Replace ice block sounds"),
                        KeenText.trans("gui.sivon.ice_sounds.tooltip",
                                "Play the Sivon ice sound for ice, packed ice, blue ice and frosted ice."))
                .doubleSlider(SOUND_BASE_RATE, 1.0D, 0.5D, 2.0D,
                        label("gui.sivon.sound_base_rate", "Base pitch"),
                        KeenText.trans("gui.sivon.sound_base_rate.tooltip",
                                "Playback speed the ice sound starts from."))
                .dependsOn(ICE_SOUNDS)
                .doubleSlider(SOUND_RATE_UP, 0.25D, 0.0D, 1.0D,
                        label("gui.sivon.sound_rate_up", "Pitch offset up"),
                        KeenText.trans("gui.sivon.sound_rate_up.tooltip",
                                "Upper bound of the random pitch offset."))
                .dependsOn(ICE_SOUNDS)
                .doubleSlider(SOUND_RATE_DOWN, 0.0D, 0.0D, 1.0D,
                        label("gui.sivon.sound_rate_down", "Pitch offset down"),
                        KeenText.trans("gui.sivon.sound_rate_down.tooltip",
                                "Lower bound of the random pitch offset."))
                .dependsOn(ICE_SOUNDS)
                .submit();
    }

    public static boolean iceSoundsEnabled() {
        return KeenConfig.create(Constants.MOD_ID).getBoolean(ICE_SOUNDS, true);
    }

    public static float baseRate() {
        return read(SOUND_BASE_RATE, 1.0D);
    }

    public static float rateUp() {
        return read(SOUND_RATE_UP, 0.25D);
    }

    public static float rateDown() {
        return read(SOUND_RATE_DOWN, 0.0D);
    }

    private static float read(String key, double fallback) {
        return (float) KeenConfig.create(Constants.MOD_ID).getDouble(key, fallback);
    }

    private static Component label(String key, String fallback) {
        return KeenText.trans("gui.sivon.ice_name", "冰冰冰")
                .append(" ").append(KeenText.trans(key, fallback));
    }
}
