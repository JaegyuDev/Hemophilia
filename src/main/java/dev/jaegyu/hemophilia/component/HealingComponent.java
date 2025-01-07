package dev.jaegyu.hemophilia.component;

import dev.jaegyu.hemophilia.HealingSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.LinkedList;


public class HealingComponent {
    private final LinkedList<HealingStack> healingStacks = new LinkedList<>();
    private final long stackExpirationTimeMillis = 15000; // currently stacks fall off ever 15 seconds

    public void addHealingStack(HealingSource source) {
        HealingStack newStack = new HealingStack(source, System.currentTimeMillis());
        healingStacks.addFirst(newStack);
    }

    private void removeExpiredStacks() {
        long currentTime = System.currentTimeMillis();
        // Remove expired stack
        healingStacks.removeIf(stack -> currentTime - stack.getTimestamp() > stackExpirationTimeMillis);
    }


    public void save(CompoundTag compound) {
        // Create a list tag for the healing stacks
        ListTag stackList = new ListTag();

        // Iterate over the healing stacks and save each stack
        for (HealingStack stack : healingStacks) {
            CompoundTag stackTag = new CompoundTag();

            // Save stack timestamp and sourceType (assuming you have a way to store the HealingSource type)
            stackTag.putLong("timestamp", stack.getTimestamp());
            stackTag.putString("sourceType", stack.getSourceType().name());

            // Add the stack tag to the list
            stackList.add(stackTag);
        }

        // Put the list of stacks in the compound tag
        compound.put("healingStacks", stackList);
    }

    public void load(CompoundTag compound) {
        healingStacks.clear(); // Clear existing stacks
        ListTag stackList = compound.getList("healingStacks", 10); // 10 is for CompoundTag type

        for (int i = 0; i < stackList.size(); i++) {
            CompoundTag stackTag = stackList.getCompound(i);
            long timestamp = stackTag.getLong("timestamp");
            HealingSource sourceType = HealingSource.valueOf(stackTag.getString("sourceType")); // assuming enum

            HealingStack stack = new HealingStack(sourceType, timestamp);
            healingStacks.add(stack);
        }
    }

}
