package dev.jaegyu.hemophilia.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ItemUsedCallback {
    // This creates an event that can be fired to listeners
    public static final Event<ItemUsed> ITEM_USED = EventFactory.createArrayBacked(ItemUsed.class, (listeners) -> (player, itemStack) -> {
        for (ItemUsed listener : listeners) {
            listener.onItemUsed(player, itemStack);
        }
    });

    // Define the functional interface for the event listener
    @FunctionalInterface
    public interface ItemUsed {
        void onItemUsed(ServerPlayer player, ItemStack itemStack);
    }
}
