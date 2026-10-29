package com.huziyang520.sivon;

import com.huziyang520.discapi.DiscApi;
import com.huziyang520.keenlib.registry.KeenRegistry;
import com.huziyang520.keenlib.text.KeenText;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.SoundType;

public final class Sivon {

    private static final KeenRegistry REGISTRY = KeenRegistry.of(Constants.MOD_ID);

    public static final ResourceKey<JukeboxSong> RAIN_LOVE_SONG = DiscApi.songKey(Constants.MOD_ID, "rain_love");
    public static final ResourceKey<JukeboxSong> ICE_LOVE_SONG = DiscApi.songKey(Constants.MOD_ID, "ice_love");

    private static Holder<SoundEvent> iceSound;
    private static Holder<Item> rainLove;
    private static Holder<Item> iceLove;
    private static SoundType iceSoundType;
    private static boolean initialized;

    private Sivon() {
    }

    public static void bind(Object eventBus) {
        REGISTRY.bind(eventBus);
    }

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        iceSound = REGISTRY.sound("block.ice");
        SivonConfig.register();
        rainLove = REGISTRY.item("rain_love", DiscApi.discFactory(RAIN_LOVE_SONG));
        iceLove = REGISTRY.item("ice_love", DiscApi.discFactory(ICE_LOVE_SONG));
        REGISTRY.tab("main",
                KeenText.trans("itemGroup.sivon.main", "Sivon"),
                () -> new ItemStack(rainLove.value()),
                output -> {
                    output.accept(new ItemStack(rainLove.value()));
                    output.accept(new ItemStack(iceLove.value()));
                });

        Constants.LOG.info("Sivon registered ice sound and 2 discs");
    }

    public static SoundType iceSoundType() {
        if (iceSoundType == null) {
            SoundEvent event = iceSound.value();
            iceSoundType = new SoundType(1.0F, 1.0F, event, event, event, event, event);
        }
        return iceSoundType;
    }
}
