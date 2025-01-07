package dev.jaegyu.hemophilia;

import dev.jaegyu.hemophilia.event.ItemUsedCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.world.level.Level;

public class Hemophilia implements ModInitializer {

    @Override
    public void onInitialize() {
        // Register the callback for the custom item used event
        ItemUsedCallback.ITEM_USED.register((player, itemStack) -> {
            // This will be called after using the Golden Carrot
            if (itemStack.getItem() == Items.GOLDEN_CARROT) {
                if (player instanceof ServerPlayer) {
                    // registry impl would cut this down
                    player.heal(HealSettings.HealingSources.get(HealingSource.GOLDEN_CARROT).healStrength());
                }
            }
        });

        ServerEntityEvents.ENTITY_UNLOAD((ServerEntity serverEntity, ServerLevel level) -> {
            serverEntity.
        });
    }
}
