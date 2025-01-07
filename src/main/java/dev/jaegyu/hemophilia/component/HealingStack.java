package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingSource;
import net.minecraft.nbt.NbtCompound;

import static dev.jaegyu.hemophilia.HealSettings.HealingSources;

public record HealingStack(HealingSource sourceType, long timestamp) {
    public float getHealcutModifier() {
        return HealingSources.get(sourceType).healCutGrowthRate();
    }

    public static NbtCompound toNbt(HealingStack stack) {
        var nbt = new NbtCompound();

        nbt.putInt("sourceType", stack.sourceType().ordinal());
        nbt.putLong("timestamp", stack.timestamp());

        return nbt;
    }

    public static HealingStack fromNbt(NbtCompound stackCompound) {
        var sourceType = HealingSource.valueOf(stackCompound.getString("sourceType"));
        var timestamp = stackCompound.getLong("timestamp");
        return new HealingStack(sourceType, timestamp);
    }
}

