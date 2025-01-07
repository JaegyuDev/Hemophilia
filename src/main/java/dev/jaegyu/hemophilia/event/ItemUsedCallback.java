package dev.jaegyu.hemophilia.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemUsedCallback {
    public static final Event<ItemUsed> ITEM_USED = EventFactory.createArrayBacked(ItemUsed.class, (listeners) -> (player, itemStack) -> {
        for (ItemUsed listener : listeners) {
            listener.onItemUsed(player, itemStack);
        }
    });

    @FunctionalInterface
    public interface ItemUsed {
        void onItemUsed(ServerPlayerEntity player, ItemStack itemStack);
    }
}
