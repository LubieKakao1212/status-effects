package com.LubieKakao1212.seffects.registries;

import com.LubieKakao1212.seffects.StatusEffectsMod;
import com.LubieKakao1212.seffects.capability.IStatusEffectHandler;
import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.effects.DebugEffect;
import com.LubieKakao1212.seffects.effect.effects.ModifierEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Register {

    public static final ResourceLocation EFFECT_REGISTRY_ID = new ResourceLocation(StatusEffectsMod.MODID, "status_effects");
    public static final DeferredRegister<StatusEffect> STATUS_EFFECTS = DeferredRegister.create(EFFECT_REGISTRY_ID, StatusEffectsMod.MODID);

    private static final Supplier<IForgeRegistry<StatusEffect>> EFFECTS_REGISTRY_GETTER;
    private static IForgeRegistry<StatusEffect> EFFECTS_REGISTRY;

    static {
        EFFECTS_REGISTRY_GETTER = STATUS_EFFECTS.makeRegistry(StatusEffect.class, () -> { return new RegistryBuilder<>(); });

        //STATUS_EFFECTS.register("pain_killer", () -> { return new StatusEffect(); });
        STATUS_EFFECTS.register(StatusEffects.DEBUG_KEY, () -> { return new DebugEffect(); });
        STATUS_EFFECTS.register(StatusEffects.SPEED_KEY, () -> { return new ModifierEffect(); });
    }

    public static void register() {
        STATUS_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static IForgeRegistry<StatusEffect> getEffectRegistry() {
        if(EFFECTS_REGISTRY == null) {
            EFFECTS_REGISTRY = EFFECTS_REGISTRY_GETTER.get();
        }
        return EFFECTS_REGISTRY;
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IStatusEffectHandler.class);
    }

}
