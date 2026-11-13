package com.huziyang520.sivon;

import com.huziyang520.discapi.DiscApi;
import com.huziyang520.keenlib.loot.KeenLoot;
import com.huziyang520.keenlib.registry.KeenRegistry;
import com.huziyang520.keenlib.text.KeenText;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public final class Sivon {

    private static final KeenRegistry REGISTRY = KeenRegistry.of(Constants.MOD_ID);

    public static final ResourceKey<JukeboxSong> RAIN_LOVE_SONG = DiscApi.songKey(Constants.MOD_ID, "rain_love");
    public static final ResourceKey<JukeboxSong> ICE_LOVE_SONG = DiscApi.songKey(Constants.MOD_ID, "ice_love");
    public static final ResourceKey<JukeboxSong> LAVA_CHICKEN_SONG = DiscApi.songKey(Constants.MOD_ID, "lava_chicken");
    public static final ResourceKey<JukeboxSong> ZOMBIES_SONG = DiscApi.songKey(Constants.MOD_ID, "zombies_on_your_lawn");

    private static Holder<SoundEvent> iceSound;
    private static Holder<Item> rainLove;
    private static Holder<Item> iceLove;
    private static Holder<Item> lavaChicken;
    private static Holder<Item> zombies;
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
        REGISTRY.sound("music_disc.rain_love");
        REGISTRY.sound("music_disc.ice_love");
        REGISTRY.sound("music_disc.lava_chicken");
        REGISTRY.sound("music_disc.zombies_on_your_lawn");
        SivonConfig.register();
        rainLove = REGISTRY.item("rain_love", DiscApi.discFactory(RAIN_LOVE_SONG));
        iceLove = REGISTRY.item("ice_love", DiscApi.discFactory(ICE_LOVE_SONG));
        lavaChicken = REGISTRY.item("lava_chicken", DiscApi.discFactory(LAVA_CHICKEN_SONG));
        zombies = REGISTRY.item("zombies_on_your_lawn", DiscApi.discFactory(ZOMBIES_SONG));
        REGISTRY.tab("main",
                KeenText.trans("itemGroup.sivon.main", "Sivon"),
                () -> new ItemStack(rainLove.value()),
                output -> {
                    output.accept(new ItemStack(rainLove.value()));
                    output.accept(new ItemStack(iceLove.value()));
                    output.accept(new ItemStack(lavaChicken.value()));
                    output.accept(new ItemStack(zombies.value()));
                });

        KeenLoot.addChance(BuiltInLootTables.ABANDONED_MINESHAFT, iceLove, 9.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.SIMPLE_DUNGEON, iceLove, 6.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.WOODLAND_MANSION, iceLove, 6.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.STRONGHOLD_CORRIDOR, rainLove, 6.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.SHIPWRECK_TREASURE, rainLove, 3.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.ANCIENT_CITY, rainLove, 3.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.NETHER_BRIDGE, lavaChicken, 6.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.BASTION_TREASURE, lavaChicken, 3.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.PILLAGER_OUTPOST, zombies, 6.0F, 1, 1);
        KeenLoot.addChance(BuiltInLootTables.BURIED_TREASURE, zombies, 3.0F, 1, 1);
    }

    public static SoundEvent iceSound() {
        return iceSound.value();
    }
}
