package com.mystic.oceanworld.client.dimensionutil;


import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;

public class DimensionUtil {
    public RegistryKey<Dimension> getOverworldKey(){
        return RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY, getOverworldID());
    }

    public RegistryKey<DimensionType> getOverworldDimensionType(){
        return RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, getOverworldID());
    }

    public ResourceLocation getOverworldID(){
        return Dimension.OVERWORLD.getLocation();
    }
}
