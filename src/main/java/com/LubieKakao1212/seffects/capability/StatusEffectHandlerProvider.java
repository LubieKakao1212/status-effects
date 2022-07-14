package com.LubieKakao1212.seffects.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StatusEffectHandlerProvider implements ICapabilitySerializable<ListTag> {

    private LazyOptional<StatusEffectHandler> effectHandler;

    public StatusEffectHandlerProvider(LivingEntity parentEntity) {
        effectHandler = LazyOptional.of(() -> new StatusEffectHandler(parentEntity));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == StatusEffectHandler.STATUS_EFFECT_HANDLER) {
            return (LazyOptional<T>) effectHandler;
        }
        return null;
    }

    @Override
    public ListTag serializeNBT() {
        return effectHandler.resolve().get().serializeNBT();
    }

    @Override
    public void deserializeNBT(ListTag nbt) {
        effectHandler.resolve().get().deserializeNBT(nbt);
    }
}
