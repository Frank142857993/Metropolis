package com.frank142857.metropolis.util.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleMtrPortal extends Particle {
    private final float portalParticleScale;
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    protected ParticleMtrPortal(World p_i46351_1_, double p_i46351_2_, double p_i46351_4_, double p_i46351_6_, double p_i46351_8_, double p_i46351_10_, double p_i46351_12_) {
        super(p_i46351_1_, p_i46351_2_, p_i46351_4_, p_i46351_6_, p_i46351_8_, p_i46351_10_, p_i46351_12_);
        this.motionX = p_i46351_8_;
        this.motionY = p_i46351_10_;
        this.motionZ = p_i46351_12_;
        this.posX = p_i46351_2_;
        this.posY = p_i46351_4_;
        this.posZ = p_i46351_6_;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        float lvt_14_1_ = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.portalParticleScale = this.particleScale;
        this.particleRed = lvt_14_1_ * 0.9F;
        this.particleGreen = lvt_14_1_ * 0.9F;
        this.particleBlue = lvt_14_1_ * 0.9F;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }

    public void move(double p_move_1_, double p_move_3_, double p_move_5_) {
        this.setBoundingBox(this.getBoundingBox().offset(p_move_1_, p_move_3_, p_move_5_));
        this.resetPositionToBB();
    }

    public void renderParticle(BufferBuilder p_renderParticle_1_, Entity p_renderParticle_2_, float p_renderParticle_3_, float p_renderParticle_4_, float p_renderParticle_5_, float p_renderParticle_6_, float p_renderParticle_7_, float p_renderParticle_8_) {
        float lvt_9_1_ = ((float)this.particleAge + p_renderParticle_3_) / (float)this.particleMaxAge;
        lvt_9_1_ = 1.0F - lvt_9_1_;
        lvt_9_1_ *= lvt_9_1_;
        lvt_9_1_ = 1.0F - lvt_9_1_;
        this.particleScale = this.portalParticleScale * lvt_9_1_;
        super.renderParticle(p_renderParticle_1_, p_renderParticle_2_, p_renderParticle_3_, p_renderParticle_4_, p_renderParticle_5_, p_renderParticle_6_, p_renderParticle_7_, p_renderParticle_8_);
    }

    public int getBrightnessForRender(float p_getBrightnessForRender_1_) {
        int lvt_2_1_ = super.getBrightnessForRender(p_getBrightnessForRender_1_);
        float lvt_3_1_ = (float)this.particleAge / (float)this.particleMaxAge;
        lvt_3_1_ *= lvt_3_1_;
        lvt_3_1_ *= lvt_3_1_;
        int lvt_4_1_ = lvt_2_1_ & 255;
        int lvt_5_1_ = lvt_2_1_ >> 16 & 255;
        lvt_5_1_ += (int)(lvt_3_1_ * 15.0F * 16.0F);
        if (lvt_5_1_ > 240) {
            lvt_5_1_ = 240;
        }

        return lvt_4_1_ | lvt_5_1_ << 16;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float lvt_1_1_ = (float)this.particleAge / (float)this.particleMaxAge;
        float lvt_2_1_ = lvt_1_1_;
        lvt_1_1_ = -lvt_1_1_ + lvt_1_1_ * lvt_1_1_ * 2.0F;
        lvt_1_1_ = 1.0F - lvt_1_1_;
        this.posX = this.portalPosX + this.motionX * (double)lvt_1_1_;
        this.posY = this.portalPosY + this.motionY * (double)lvt_1_1_ + (double)(1.0F - lvt_2_1_);
        this.posZ = this.portalPosZ + this.motionZ * (double)lvt_1_1_;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }

    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        public Factory() {
        }

        @Override
        public Particle createParticle(int p_createParticle_1_, World p_createParticle_2_, double p_createParticle_3_, double p_createParticle_5_, double p_createParticle_7_, double p_createParticle_9_, double p_createParticle_11_, double p_createParticle_13_, int... p_createParticle_15_) {
            return new ParticleMtrPortal(p_createParticle_2_, p_createParticle_3_, p_createParticle_5_, p_createParticle_7_, p_createParticle_9_, p_createParticle_11_, p_createParticle_13_);
        }
    }
}
