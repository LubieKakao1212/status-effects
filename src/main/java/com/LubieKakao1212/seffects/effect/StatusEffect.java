package com.LubieKakao1212.seffects.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.swing.text.html.parser.Entity;

public abstract class StatusEffect extends ForgeRegistryEntry<StatusEffect> {

    private final int defaultTime;
    private final float defaultIntensity;

    public StatusEffect(int defaultTime, float defaultIntensity) {
        this.defaultTime = defaultTime;
        this.defaultIntensity = defaultIntensity;
    }

    /**
     * @return true if effect should continue
     */
    public boolean tick(LivingEntity target, StatusEffectInstance instance) {
        return instance.tickDown() > 0;
    }

    /**
     * @return true if effect should continue
     */
    public boolean start(LivingEntity target, StatusEffectInstance instance) {
        return true;
    }

    public void end(LivingEntity target, StatusEffectInstance instance) {

    }

    public int getDefaultTime() {
        return defaultTime;
    }

    public float getDefaultIntensity() {
        return defaultIntensity;
    }

}
