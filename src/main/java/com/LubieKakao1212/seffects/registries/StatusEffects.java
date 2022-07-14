package com.LubieKakao1212.seffects.registries;

import com.LubieKakao1212.seffects.StatusEffectsMod;
import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = StatusEffectsMod.MODID)
public class StatusEffects {

    static final String DEBUG_KEY = "debug";
    static final String SPEED_KEY = "speed";

    @ObjectHolder(value = DEBUG_KEY)
    public static final StatusEffect DEBUG = null;

    @ObjectHolder(value = SPEED_KEY)
    public static final StatusEffect SPEED = null;

    public static StatusEffectInstance debug(int time) {
        return new StatusEffectInstance(DEBUG, new CompoundTag(), 0f, time);
    }

    public static StatusEffectInstance speed(int time, float intensity) {
        return new StatusEffectInstance(SPEED, new CompoundTag(), intensity, time);
    }



}
