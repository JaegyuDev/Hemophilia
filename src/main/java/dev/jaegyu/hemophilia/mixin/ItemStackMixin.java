package dev.jaegyu.hemophilia.mixin;

import dev.jaegyu.hemophilia.event.ItemUsedCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;


@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Redirect(method = "finishUsingItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack redirectFinishUsingItem(Item instance, ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer) {
            ItemUsedCallback.ITEM_USED.invoker().onItemUsed((ServerPlayer) livingEntity, itemStack);
        }

        return instance.finishUsingItem(itemStack, level, livingEntity);
    }
}





