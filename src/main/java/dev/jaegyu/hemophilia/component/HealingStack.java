package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingType;
import net.minecraft.nbt.NbtCompound;

public record HealingStack(HealingType sourceType, long expirationTimestamp) implements Comparable<HealingStack> {

    public static NbtCompound toNbt(HealingStack stack) {
        var nbt = new NbtCompound();

        nbt.putInt("type", stack.sourceType().ordinal());
        nbt.putLong("timestamp", stack.expirationTimestamp());

        return nbt;
    }

    public static HealingStack fromNbt(NbtCompound stackCompound) {
        var sourceType = HealingType.valueOf(stackCompound.getString("type"));
        var timestamp = stackCompound.getLong("timestamp");
        return new HealingStack(sourceType, timestamp);
    }

    @Override
    public int compareTo(HealingStack other) {
        return Long.compare(this.expirationTimestamp(), other.expirationTimestamp());
    }
}

