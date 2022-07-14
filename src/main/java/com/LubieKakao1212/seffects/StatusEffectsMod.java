package com.LubieKakao1212.seffects;

import com.LubieKakao1212.seffects.registries.Register;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(StatusEffectsMod.MODID)
public class StatusEffectsMod
{
    public static final String MODID = "seffects";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public StatusEffectsMod() {
        Register.register();
    }
}
