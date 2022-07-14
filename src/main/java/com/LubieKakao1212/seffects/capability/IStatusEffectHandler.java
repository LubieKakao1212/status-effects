package com.LubieKakao1212.seffects.capability;

import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;

import java.util.List;

public interface IStatusEffectHandler {

    void AddEffect(StatusEffectInstance effectInstance);

    void RemoveEffect(StatusEffectInstance effectInstance);

    List<StatusEffectInstance> GetActiveEffects(StatusEffect effect);

    void tick();
}
