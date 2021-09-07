package com.mystic.oceanworld.client.dimensionutil;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class DimensionUtil {
    public RegistryKey<DimensionOptions> getOverworldKey(){
        return RegistryKey.of(Registry.DIMENSION_KEY, getOverworldID());
    }

    public RegistryKey<DimensionType> getOverworldDimensionType(){
        return RegistryKey.of(Registry.DIMENSION_TYPE_KEY, getOverworldID());
    }

    public Identifier getOverworldID(){
        return DimensionOptions.OVERWORLD.getValue();
    }
}
