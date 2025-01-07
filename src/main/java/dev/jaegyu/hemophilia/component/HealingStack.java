package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingSource;

import static dev.jaegyu.hemophilia.HealSettings.HealingSources;

public class HealingStack {
    private final long timestamp;
    private final HealingSource sourceType;

    public HealingStack(HealingSource sourceType, long timestamp) {
        this.sourceType = sourceType;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getHealcutModifier() {
        return HealingSources.get(sourceType).healCutGrowthRate();
    }

    public HealingSource getSourceType() {
        return sourceType;
    }
}
