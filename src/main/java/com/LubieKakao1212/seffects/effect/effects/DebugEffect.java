package com.LubieKakao1212.seffects.effect.effects;

import com.LubieKakao1212.seffects.StatusEffectsMod;
import com.LubieKakao1212.seffects.effect.StatusEffect;
import com.LubieKakao1212.seffects.effect.StatusEffectInstance;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.lang.annotation.Target;

public class DebugEffect extends StatusEffect {

    public DebugEffect() {
        super(10, 1);
    }

    @Override
    public boolean tick(LivingEntity target, StatusEffectInstance instance) {
        if(target instanceof Player) {
            Player player = (Player) target;

            player.sendMessage(new TextComponent(Integer.toString(instance.getTime())), player.getUUID());
        }else
        {
            StatusEffectsMod.LOGGER.info(Integer.toString(instance.getTime()));
        }
        return super.tick(target, instance);
    }
}
