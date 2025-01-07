package dev.jaegyu.hemophilia.event;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemUsedEvent {
    private final ServerPlayerEntity player;
    private final ItemStack itemStack;

    public ItemUsedEvent(ServerPlayerEntity player, ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
