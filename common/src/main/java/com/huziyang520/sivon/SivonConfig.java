package com.huziyang520.sivon;

import com.huziyang520.keenlib.config.KeenConfig;
import com.huziyang520.keenlib.config.KeenConfigApi;
import com.huziyang520.keenlib.text.KeenText;

/**
 * Sivon options, rendered by the KeenLib config screen when Cloth Config is installed.
 */
public final class SivonConfig {

    public static final String ICE_SOUNDS = "ice_sounds";

    private SivonConfig() {
    }

    public static void register() {
        KeenConfigApi.business(Constants.MOD_ID, KeenText.trans("gui.sivon.title", "Sivon"))
                .booleanToggle(ICE_SOUNDS, true,
                        KeenText.trans("gui.sivon.ice_sounds", "Replace ice block sounds"),
                        KeenText.trans("gui.sivon.ice_sounds.tooltip",
                                "Play the Sivon ice sound for ice, packed ice, blue ice and frosted ice."))
                .submit();
    }

    /**
     * Read live on every query so the toggle applies without restarting the game.
     */
    public static boolean iceSoundsEnabled() {
        return KeenConfig.create(Constants.MOD_ID).getBoolean(ICE_SOUNDS, true);
    }
}
