package com.branders.wellfedmod.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiCookeryBook extends GuiScreen 
{	
	// Dimensions of book
	private int imageWidth = 146;
	private int imageHeight = 180;
	
	// Holds the page we are currently rendering on screen
	private int currentPage = 0;
	
	// Navigation buttons
	private GuiButton nextButton;
	private GuiButton prevButton;
	
	private List<ResourceLocation> sources = new ArrayList<ResourceLocation>();
	
	/**
	 * 	Takes a list of resource locations which is the book pages
	 */
	public GuiCookeryBook(List<ResourceLocation> sources)
	{
		this.sources = sources;
	}
	
	/**
	 * 	Create all the buttons
	 */
	@Override
	protected void initGui() 
	{
		/**
		 * 	Close button
		 */
		addButton(new GuiButton(0, width / 2 - 64, imageHeight + 35, 128, 20, "Close") 
		{
			public void onClick(double mouseX, double mouseY)
			{
				close();
			}
		});
		
		/**
		 * 	"Previous" nav button
		 */
		addButton(prevButton = new GuiButton(0, width / 2 - 64, imageHeight + 10, 56, 20, "< Prev")
		{
			public void onClick(double mouseX, double mouseY)
			{
				// Change page in negative direction (backwards)
				changePage(-1);
			}
		});
		
		/**
		 * 	"Next" nav button
		 */
		addButton(nextButton = new GuiButton(0, width / 2 + 8, imageHeight + 10, 56, 20, "Next >")
		{
			public void onClick(double mouseX, double mouseY)
			{
				// Change page in positive direction (forward)
				changePage(1);
			}
		});
		
		// Start set prevButton to false because we start at page 0
		prevButton.enabled = false;
	}
	
	/**
	 * 	Functionality for changing page in cookery book
	 */
	private void changePage(int direction)
	{
		// Go forward in book
		if(direction > 0)
		{
			prevButton.enabled = true;
			currentPage++;
			
			// Make sure we can't go beyond book pages
			if(currentPage == sources.size() - 1)
				nextButton.enabled = false;
		}
		
		// Same as forward (code above) but for backwards
		else
		{
			nextButton.enabled = true;
			currentPage--;
			
			if(currentPage == 0)
				prevButton.enabled = false;
		}
	}
	
	
	/**
	 * 	Render book
	 */
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		// Draw page texture
		mc.getTextureManager().bindTexture(sources.get(currentPage));
		drawModalRectWithCustomSizedTexture(width / 2 - imageWidth / 2, 5, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		
		
		// Render other stuff aswell (buttons etc)
		super.render(mouseX, mouseY, partialTicks);
	}
}