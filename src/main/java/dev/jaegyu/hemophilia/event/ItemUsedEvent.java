package dev.jaegyu.hemophilia.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ItemUsedEvent {
    private final ServerPlayer player;
    private final ItemStack itemStack;

    public ItemUsedEvent(ServerPlayer player, ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
