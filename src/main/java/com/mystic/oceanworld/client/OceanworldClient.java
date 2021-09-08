package com.mystic.oceanworld.client;

import com.mystic.oceanworld.client.dimensionutil.DimensionUtil;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class OceanworldClient{
    public static void init(final FMLClientSetupEvent event) {

        DimensionRenderInfo overworld = new DimensionRenderInfo(255.0F, true, DimensionRenderInfo.FogType.NORMAL, false, false) {
            @Override
            public Vector3d func_230494_a_(Vector3d vector3d, float v) {
                return vector3d;
            }

            @Override
            public boolean func_230493_a_(int i, int i1) {
                return false;
            }
        };
        DimensionRenderInfo.field_239208_a_.put(new DimensionUtil().getOverworldID(), overworld);
    }
}
