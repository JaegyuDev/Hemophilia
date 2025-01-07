package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.LinkedList;


public class HealingComponent {
    private final LinkedList<HealingStack> healingStacks = new LinkedList<>();
    // honestly we probably dont need to de/serialize this
    private final long stackExpirationTimeMillis = 15000; // currently stacks fall off ever 15 seconds

    public void addHealingStack(HealingSource source) {
        HealingStack newStack = new HealingStack(source, System.currentTimeMillis());
        healingStacks.addFirst(newStack);
    }

    private void removeExpiredStacks() {
        long currentTime = System.currentTimeMillis();
        healingStacks.removeIf(stack -> currentTime - stack.timestamp() > stackExpirationTimeMillis);
    }

    public LinkedList<HealingStack> getHealingStacks() {
        // this returns a shallow copy. Honestly we can probably just return the original list, but until this becomes a
        // problem we'll keep it like this.

        return (LinkedList<HealingStack>) healingStacks.clone();
    }

    public long getStackDuration() {
        return stackExpirationTimeMillis;
    }

    // not sure what the conventional way to do this is
    public static HealingComponent fromNbt(NbtCompound compound) {
        var component = new HealingComponent();

        for (NbtElement stackElement : compound.getList("stacks", 10)) {
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
        nbt.putLong("StackExpiration", component.getStackDuration());

        return nbt;
    }
}
