package dev.jaegyu.hemophilia.mixin;

import dev.jaegyu.hemophilia.event.ItemUsedCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;


@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Redirect(method = "finishUsing",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;finishUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack redirectFinishUsingItem(Item instance, ItemStack itemStack, World level, LivingEntity livingEntity) {

        if (livingEntity instanceof ServerPlayerEntity) {
            ItemUsedCallback.ITEM_USED.invoker().onItemUsed((ServerPlayerEntity) livingEntity, itemStack);
        }

        return instance.finishUsing(itemStack, level, livingEntity);
    }
}





