package com.LubieKakao1212.seffects.effect.effects;

import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class ModifierEffect extends StatusEffect {

    public ModifierEffect() {
        super(10, 1);
    }

    @Override
    public boolean start(LivingEntity target, StatusEffectInstance instance) {
        AttributeModifier modifier = new AttributeModifier("effect", instance.getIntensity(), AttributeModifier.Operation.MULTIPLY_BASE);
        target.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).addTransientModifier(modifier);
        instance.getTag().putUUID("modifier", modifier.getId());
        return super.start(target, instance);
    }

    @Override
    public void end(LivingEntity target, StatusEffectInstance instance) {
        UUID modifierId = instance.getTag().getUUID("modifier");
        target.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).removeModifier(modifierId);
    }
}
