package com.LubieKakao1212.seffects.event;

import com.LubieKakao1212.seffects.StatusEffectsMod;
import com.LubieKakao1212.seffects.capability.StatusEffectHandler;
import com.LubieKakao1212.seffects.capability.StatusEffectHandlerProvider;
import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;
import com.LubieKakao1212.seffects.registries.Register;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;


@Mod.EventBusSubscriber
public class CapabilityEventHandler {

    private static final Random random = new Random();

    public static final ResourceLocation EFFECT_HANDLER_PROVIDER_ID = new ResourceLocation(StatusEffectsMod.MODID, "effect_handler");

    @SubscribeEvent
    public static void attachEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if(entity instanceof LivingEntity) {
            event.addCapability(EFFECT_HANDLER_PROVIDER_ID, new StatusEffectHandlerProvider((LivingEntity) entity));
        }
    }

    @SubscribeEvent
    public static void addedEffectEvent(PotionEvent.PotionAddedEvent event) {
        event.getEntityLiving().getCapability(StatusEffectHandler.STATUS_EFFECT_HANDLER).ifPresent(
                (handler) -> handler.AddEffect(new StatusEffectInstance(Register.getEffectRegistry().getValue(new ResourceLocation(StatusEffectsMod.MODID, "debug")), new CompoundTag(), 1, random.nextInt(8) * 8))
        );
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingUpdateEvent event) {
        event.getEntityLiving().getCapability(StatusEffectHandler.STATUS_EFFECT_HANDLER).ifPresent(
                (handler) -> handler.tick());
    }

}
