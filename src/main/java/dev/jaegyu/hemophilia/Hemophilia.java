package dev.jaegyu.hemophilia;

import dev.jaegyu.hemophilia.event.ItemUsedCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;

import static dev.jaegyu.hemophilia.HealSettings.HealingSources;

public class Hemophilia implements ModInitializer {

    public static final String MOD_ID = "hemophilia";

    @Override
    public void onInitialize() {
        ItemUsedCallback.ITEM_USED.register((player, itemStack) -> {
            if (itemStack.getItem() == Items.GOLDEN_CARROT) {
                if (player != null) {
                    player.heal(HealingSources.get(HealingType.GOLDEN_CARROT).healStrength());
                }
            }
        });
    }
}
