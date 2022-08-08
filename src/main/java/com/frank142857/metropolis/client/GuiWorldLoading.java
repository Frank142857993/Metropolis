package com.frank142857.metropolis.client;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWorldLoading extends GuiScreen {
    private boolean state;

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(0);
        String title = I18n.format("title.loading." + (state ? "enter" : "leave"));
        int left = this.width / 2;
        int top = this.height / 2;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawCenteredString(this.fontRenderer, title, left, top - 50, 0xffffff);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
