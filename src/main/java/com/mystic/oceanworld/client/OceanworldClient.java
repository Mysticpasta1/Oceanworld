package com.mystic.oceanworld.client;

import com.mystic.oceanworld.client.dimensionutil.DimensionUtil;
import io.github.waterpicker.openworlds.OpenWorlds;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class OceanworldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SkyProperties overworld = new SkyProperties(300.0F, true, SkyProperties.SkyType.NORMAL, false, false) {
            @Override
            public Vec3d adjustFogColor(Vec3d vector3d, float v) {
                return vector3d;
            }

            @Override
            public boolean useThickFog(int i, int i1) {
                return false;
            }
        };
        
        OpenWorlds.registerSkyProperty(new DimensionUtil().getOverworldDimensionType(), overworld);
    }
}
