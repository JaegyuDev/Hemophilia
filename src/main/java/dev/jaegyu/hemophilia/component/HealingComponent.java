package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingType;
import dev.jaegyu.hemophilia.PlayerData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.PriorityQueue;

import static dev.jaegyu.hemophilia.HealSettings.HealingItems;
import static dev.jaegyu.hemophilia.HealSettings.HealingSources;


public class HealingComponent {
    private final PriorityQueue<HealingStack> healingStacks = new PriorityQueue<>();

    public void addHealingStack(HealingType source) {
        HealingStack newStack = new HealingStack(source, System.currentTimeMillis() + HealingSources.get(source).duration());
        healingStacks.offer(newStack);
    }

    public void addHealingStack(ItemStack source) {
        if (HealingItems.containsKey(source)) {
            HealingStack newStack = new HealingStack(HealingItems.get(source), System.currentTimeMillis() + HealingSources.get(HealingItems.get(source)).duration());
            healingStacks.offer(newStack);
        }

    }

    private void removeExpiredStacks() {
        long currentTime = System.currentTimeMillis();

        for (HealingStack stack : healingStacks) {
            // this will remove only the stacks that expire. since this is sorted by duration we
            // can just break the loop once we hit a duration that hasn't expired
            if (currentTime < stack.expirationTimestamp()) {
                break;
            }

            healingStacks.remove(stack);
        }
    }

    public static float getHealingModifier(PlayerData data) {
        if (data.healingComponent == null) {
            data.healingComponent = new HealingComponent();
        }
        var healingStacks = data.healingComponent.getHealingStacks();

        float modifier = 0.0F;

        for (HealingStack stack : healingStacks) {
            modifier += HealingSources.get(stack.sourceType()).healCutGrowthRate();
            if (modifier >= 1.0F) break;
        }

        return modifier;
    }

    public PriorityQueue<HealingStack> getHealingStacks() {
        return healingStacks;
    }

    // not sure what the conventional way to do this is
    public static HealingComponent fromNbt(NbtCompound compound) {
        var component = new HealingComponent();

        for (NbtElement stackElement : compound.getList("HealingStacks", 10)) {
            component.healingStacks.add(HealingStack.fromNbt((NbtCompound) stackElement));
        }

        return component;
    }

    public static NbtCompound toNbt(HealingComponent component) {
        var nbt = new NbtCompound();
        var stackList = new NbtList();

        for (HealingStack stack : component.getHealingStacks()) {
            stackList.add(HealingStack.toNbt(stack));
        }

        nbt.put("HealingStacks", stackList);

        return nbt;
    }
}
