package dev.jaegyu.hemophilia.mixin;

import dev.jaegyu.hemophilia.PlayerData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static dev.jaegyu.hemophilia.StateSaverAndLoader.getPlayerState;
import static dev.jaegyu.hemophilia.component.HealingComponent.getHealingModifier;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void heal(float f) {
        float g = this.getHealth();
        if (g > 0.0F) {
            PlayerData playerData = getPlayerState(this);

            g = g *  (1 - getHealingModifier(playerData));

            this.setHealth(Math.clamp(g + f, 0.0F,this.getMaxHealth()));
        }
    }
}
