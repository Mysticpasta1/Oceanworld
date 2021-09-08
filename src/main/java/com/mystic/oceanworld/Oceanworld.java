package com.mystic.oceanworld;

import com.mystic.oceanworld.client.OceanworldClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("oceanworld")
public class Oceanworld  {
    public Oceanworld(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(OceanworldClient::init);
     }
}
