package dev.jaegyu.hemophilia.mixin;

import net.minecraft.world.food.Foods;
import net.minecraft.world.food.FoodProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Foods.class)
public class FoodsMixin {
    @Final
    @Shadow
    @Mutable
    public static FoodProperties GOLDEN_CARROT;

    static {
        GOLDEN_CARROT = new FoodProperties.Builder()
                .nutrition(6)
                .saturationModifier(1.2F)
                .alwaysEdible()
                .build();
    }
}
