package com.LubieKakao1212.seffects.capability;

import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.INBTSerializable;
import java.util.*;

public class StatusEffectHandler implements IStatusEffectHandler, INBTSerializable<ListTag> {

    public static final Capability<IStatusEffectHandler> STATUS_EFFECT_HANDLER = CapabilityManager.get(new CapabilityToken<>() {});

    public static void AddEffectTo(LivingEntity entity, StatusEffectInstance effect) {
        AddEffectTo(entity, effect, true);
    }

    public static void AddEffectTo(LivingEntity entity, StatusEffectInstance effect, boolean copy) {
        entity.getCapability(STATUS_EFFECT_HANDLER).ifPresent(
                (handler) -> handler.AddEffect(copy ? effect.copy() : effect)
        );
    }

    private final List<StatusEffectInstance> effects = new ArrayList<>();
    private final List<StatusEffectInstance> addedEffects = new ArrayList<>();

    private final Map<StatusEffect, List<StatusEffectInstance>> effectsByType = new HashMap<>();

    private final LivingEntity parent;

    public StatusEffectHandler(LivingEntity parent) {
        this.parent = parent;
    }

    @Override
    public void AddEffect(StatusEffectInstance effectInstance) {
        addedEffects.add(effectInstance);
    }

    @Override
    public void RemoveEffect(StatusEffectInstance effectInstance) {
        effects.remove(effectInstance);
    }

    @Override
    public List<StatusEffectInstance> GetActiveEffects(StatusEffect effect) {
        return effectsByType.getOrDefault(effect, new ArrayList<>());
    }

    @Override
    public void tick() {

        for (StatusEffectInstance effectInstance : addedEffects) {
            effectInstance.getEffect().start(parent, effectInstance);
            effects.add(effectInstance);
        }

        addedEffects.clear();

        Iterator<StatusEffectInstance> it = effects.iterator();

        while(it.hasNext()) {
            StatusEffectInstance effectInstance = it.next();
            if(!effectInstance.getEffect().tick(parent, effectInstance))
            {
                effectInstance.getEffect().end(parent, effectInstance);
                it.remove();
            }
        }
    }

    @Override
    public ListTag serializeNBT() {
        ListTag tag = new ListTag();
        for (StatusEffectInstance effectInstance : effects)
        {
            tag.add(StatusEffectInstance.serialize(effectInstance));
        }
        return tag;
    }

    @Override
    public void deserializeNBT(ListTag nbt) {
        effects.clear();
        effectsByType.clear();

        for(Tag tag : nbt) {
            CompoundTag compound = (CompoundTag) tag;

            AddEffect(StatusEffectInstance.deserialize(compound));
        }
    }
}
