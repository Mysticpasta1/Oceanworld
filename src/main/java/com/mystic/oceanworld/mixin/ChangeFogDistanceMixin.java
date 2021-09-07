package com.mystic.oceanworld.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(BackgroundRenderer.class)
public class ChangeFogDistanceMixin {

    @Inject(method = "applyFog",at = @At("HEAD"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        ci.cancel();
        FluidState fluidState = camera.getSubmergedFluidState();
        Entity entity = camera.getFocusedEntity();
        float y;
        if (!(fluidState.isIn(FluidTags.WATER)) && !(fluidState.isIn(FluidTags.LAVA))) {
            y = 750F;
            RenderSystem.fogStart(10.0F);
            RenderSystem.fogEnd(y * 0.5F);
        }
        else if (fluidState.isIn(FluidTags.WATER)) {
            y = 750.0F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                y *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                if (biome.getCategory() == Biome.Category.SWAMP) {
                    y *= 0.50F;
                }
            }

            RenderSystem.fogStart(20.0F);
            RenderSystem.fogEnd(y * 0.5F);
        } else {
            float ab;
            if (fluidState.isIn(FluidTags.LAVA)) {
                if (entity.isSpectator()) {
                    y = -8.0F;
                    ab = viewDistance * 0.5F;
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    y = 0.0F;
                    ab = 3.0F;
                } else {
                    y = 0.25F;
                    ab = 1.0F;
                }
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                int m = Objects.requireNonNull(((LivingEntity) entity).getStatusEffect(StatusEffects.BLINDNESS)).getDuration();
                float n = MathHelper.lerp(Math.min(1.0F, (float)m / 20.0F), viewDistance, 5.0F);
                if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    y = 0.0F;
                    ab = n * 0.8F;
                } else {
                    y = n * 0.25F;
                    ab = n;
                }
            } else if (thickFog) {
                y = viewDistance * 0.05F;
                ab = Math.min(viewDistance, 192.0F) * 0.5F;
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                y = 0.0F;
                ab = viewDistance;
            } else {
                y = viewDistance * 0.75F;
                ab = viewDistance;
            }

            RenderSystem.fogStart(y);
            RenderSystem.fogEnd(ab);
        }

    }
}
