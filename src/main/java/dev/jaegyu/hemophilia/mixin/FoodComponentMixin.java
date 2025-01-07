package dev.jaegyu.hemophilia.mixin;

import dev.jaegyu.hemophilia.PlayerData;
import dev.jaegyu.hemophilia.StateSaverAndLoader;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponent.class)
public abstract class FoodComponentMixin {
    @Inject(method = "onConsume", at = @At("HEAD"))
    private void applyHealcut(World world, LivingEntity player, ItemStack stack, ConsumableComponent consumable, CallbackInfo ci) {
        if (player instanceof PlayerEntity) {
            PlayerData playerState = StateSaverAndLoader.getPlayerState(player);
            playerState.healingComponent.addHealingStack(stack);
        }
    }
}
