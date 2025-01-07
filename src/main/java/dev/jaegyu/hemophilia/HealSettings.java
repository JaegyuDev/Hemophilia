package dev.jaegyu.hemophilia;

import java.util.HashMap;
import java.util.Map;

// TODO: refactor this into a mod config file or something
public final class HealSettings {

    /**
     * The type Heal setting instance.
     *
     *  @param healCutGrowthRate the rate at which healcut increases. 1.0 cut rate will negate all healing.
     *
     *  @param healStrength the strength of the incoming healing. 0.5 is half a heart.
     */
    public record HealSettingInstance(float healCutGrowthRate, float healStrength) { }

    public static Map<HealingSource, HealSettingInstance> HealingSources = new HashMap<>() {
        {
            put(HealingSource.GOLDEN_CARROT, new HealSettingInstance(0.25f, 0.5f));
        }
    };

}
