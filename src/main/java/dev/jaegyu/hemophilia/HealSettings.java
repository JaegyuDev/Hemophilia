package dev.jaegyu.hemophilia;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

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
     *
     *  @param duration
     */
    public record HealSettingInstance(float healCutGrowthRate, float healStrength, long duration) { }

    public static Map<HealingType, HealSettingInstance> HealingSources = new HashMap<>() {
        {
            put(HealingType.GOLDEN_CARROT, new HealSettingInstance(0.25f, 0.5f, Time.Second * 15));
        }
    };

    public static Map<ItemStack, HealingType> HealingItems = new HashMap<>() {
        {
            put(new ItemStack(Items.GOLDEN_CARROT), HealingType.GOLDEN_CARROT);
        }
    };



    static class Time {
        static long Millisecond = 1;
        static long Second = Millisecond * 1000;
        static long Minute = Second * 60;
        static long Hour = Minute * 60;
    }
}
