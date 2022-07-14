package com.LubieKakao1212.seffects.effect;

import com.LubieKakao1212.seffects.StatusEffectsMod;
import com.LubieKakao1212.seffects.registries.Register;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class StatusEffectInstance {

    private static final String ID_KEY = "id";
    private static final String TIME_KEY = "time";
    private static final String INTENSITY_KEY = "intensity";
    private static final String TAG_KEY = "tag";

    public static CompoundTag serialize(StatusEffectInstance effectInstance) {
        CompoundTag output = new CompoundTag();

        output.putString(ID_KEY, effectInstance.getEffect().getRegistryName().toString());
        output.put(TAG_KEY, effectInstance.getTag());
        output.putInt(TIME_KEY, effectInstance.getTime());
        output.putFloat(INTENSITY_KEY, effectInstance.getIntensity());


        return output;
    }

    public static StatusEffectInstance deserialize(CompoundTag tag) {
        return new StatusEffectInstance(
                Register.getEffectRegistry().getValue(new ResourceLocation(tag.getString(ID_KEY))),
                tag.getCompound(TAG_KEY),
                tag.getFloat(INTENSITY_KEY),
                tag.getInt(TIME_KEY)
        );
    }

    private final StatusEffect effect;
    private final CompoundTag tag;
    private int time;
    private float intensity;

    public StatusEffectInstance(StatusEffect effect, CompoundTag initTag) {
        this(effect, initTag, effect.getDefaultIntensity());
    }

    public StatusEffectInstance(StatusEffect effect, CompoundTag initTag, float intensity) {
        this(effect, initTag, intensity, effect.getDefaultTime());
    }

    public StatusEffectInstance(StatusEffect effect, CompoundTag initTag, float intensity, int time) {
        this.effect = effect;
        this.tag = initTag;
        this.time = time;
        this.intensity = intensity;
    }

    public CompoundTag getTag() {
        return tag;
    }

    public StatusEffect getEffect() {
        return effect;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int tickDown() { return time--; }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public StatusEffectInstance copy() {
        return new StatusEffectInstance(effect, tag.copy(), intensity, time);
    }

}
