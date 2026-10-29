package com.huziyang520.sivon.neoforge;

import com.huziyang520.sivon.Constants;
import com.huziyang520.sivon.Sivon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/**
 * NeoForge entry: registration is deferred, so the mod event bus is required.
 */
@Mod(Constants.MOD_ID)
public class SivonNeoForge {

    public SivonNeoForge(IEventBus eventBus, ModContainer container) {
        Sivon.bind(eventBus);
        Sivon.init();
    }
}
