package com.huziyang520.sivon.fabric;

import com.huziyang520.sivon.Sivon;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric entry: binds the registry helper and registers content on both sides.
 */
public class SivonFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Sivon.bind(null);
        Sivon.init();
    }
}
