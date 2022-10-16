package com.frank142857.metropolis.util.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;

public class ParticleSpawner {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static Particle spawnParticle(MtrEnumParticleTypes type, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed)
    {
        if (mc != null && mc.getRenderViewEntity() != null && mc.effectRenderer != null)
        {
            int f = mc.gameSettings.particleSetting;

            if (f == 1 && mc.world.rand.nextInt(3) == 0)
            {
                f = 2;
            }

            double x = mc.getRenderViewEntity().posX - xCoord;
            double y = mc.getRenderViewEntity().posY - yCoord;
            double z = mc.getRenderViewEntity().posZ - zCoord;
            Particle particle = null;

            if (x * x + y * y + z * z > 16.0D * 16.0D)
            {
                return null;
            }
            else if (f > 1)
            {
                return null;
            }
            else
            {
                if (type == MtrEnumParticleTypes.MTR_PORTAL)
                {
                    particle = new ParticleMtrPortal(mc.world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
                }

                mc.effectRenderer.addEffect(particle);
                return particle;
            }
        }
        return null;
    }
}
