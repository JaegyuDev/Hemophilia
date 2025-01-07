package dev.jaegyu.hemophilia.mixin;

import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.FoodComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoodComponents.class)
public class FoodsMixin {
    @Final
    @Shadow
    @Mutable
    public static FoodComponent GOLDEN_CARROT;

    static {
        GOLDEN_CARROT = new FoodComponent.Builder()
                .nutrition(6)
                .saturationModifier(1.2F)
                .alwaysEdible()
                .build();
    }
}
