package com.branders.wellfedmod.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.branders.wellfedmod.WellFedMod;
import com.branders.wellfedmod.container.MortarAndPestleContainer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * 	Handle GUI screen for Mortar and Pestle container	
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class MortarAndPestleScreen extends ContainerScreen<MortarAndPestleContainer>
{
	private ResourceLocation MORTAR_AND_PESTLE_CONTAINER_GUI = new ResourceLocation(
			WellFedMod.MODID, 
			"/textures/gui/mortar_and_pestle_container.png");
	
	private ResourceLocation HELP_GUI = new ResourceLocation(
			WellFedMod.MODID, 
			"/textures/gui/mortar_and_pestle_container_help.png");
	
	private int helpScreenX = 136;
	private int helpScreenY = 84;
	
	private boolean toggleHelpGui = false;
	
	public MortarAndPestleScreen(MortarAndPestleContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn)
	{
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	protected void init() {
		super.init();
		
		addButton(new Button(width / 2 - 87, height / 2 - 105, 20, 20, "?", button -> 
		{
			toggleHelpGui = !toggleHelpGui;
		}));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		renderHelpGui(mouseX, mouseY);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		font.drawString(title.getFormattedText(), 8, 6, 0x404040);
		font.drawString(playerInventory.getDisplayName().getFormattedText(), 8, ySize - 96 + 2, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bindTexture(MORTAR_AND_PESTLE_CONTAINER_GUI);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		blit(x, y, 0, 0, xSize, ySize, xSize, ySize);
	}
	
	/**
	 *	Render help GUI
	 * 
	 * 	@param mouseX
	 * 	@param mouseY
	 */
	private void renderHelpGui(int mouseX, int mouseY)
	{
		if(toggleHelpGui)
		{   
			
            disableLightning();
			GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);			
			minecraft.getTextureManager().bindTexture(HELP_GUI);
			int x = (width / 2) - 225;
			int y = (height - ySize) / 2;
			blit(x, y, 0, 0, helpScreenX, helpScreenY, helpScreenX, helpScreenY);
			enableLightning();
		}
	}
	
	private void disableLightning()
	{
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	private void enableLightning()
	{
		GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}
}














