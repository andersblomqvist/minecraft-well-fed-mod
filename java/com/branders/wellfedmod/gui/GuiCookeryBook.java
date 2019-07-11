package com.branders.wellfedmod.gui;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiCookeryBook extends Screen
{	
	// Dimensions of book
	private int imageWidth = 146;
	private int imageHeight = 180;
	
	// Holds the page we are currently rendering on screen
	private int currentPage = 0;
	
	// Navigation buttons
	private Button nextButton;
	private Button prevButton;
	
	private List<ResourceLocation> sources = new ArrayList<ResourceLocation>();
	
	/**
	 * 	Takes a list of resource locations which is the book pages
	 */
	public GuiCookeryBook(List<ResourceLocation> sources, ITextComponent textComponent)
	{
		super(textComponent);
		this.sources = sources;
	}
	
	/**
	 * 	Create all the buttons
	 */
	@Override
	protected void init() 
	{
		/**
		 * 	Close button
		 */
		addButton(new Button(width / 2 - 64, imageHeight + 35, 128, 20, "Close", button ->
		{
			onClose();
		}));
		
		/**
		 * 	"Previous" nav button
		 */
		addButton(prevButton = new Button(width / 2 - 64, imageHeight + 10, 56, 20, "< Prev", button ->
		{
			changePage(-1);
		}));
		
		/**
		 * 	"Next" nav button
		 */
		addButton(nextButton = new Button(width / 2 + 8, imageHeight + 10, 56, 20, "Next >", button ->
		{
			changePage(1);
		}));
		
		// Start set prevButton to false because we start at page 0
		prevButton.active = false;
	}
	
	/**
	 * 	Functionality for changing page in cookery book
	 */
	private void changePage(int direction)
	{
		// Go forward in book
		if(direction > 0)
		{
			prevButton.active = true;
			currentPage++;
			
			// Make sure we can't go beyond book pages
			if(currentPage == sources.size() - 1)
				nextButton.active = false;
		}
		
		// Same as forward (code above) but for backwards
		else
		{
			nextButton.active = true;
			currentPage--;
			
			if(currentPage == 0)
				prevButton.active = false;
		}
	}
	
	
	/**
	 * 	Render book
	 */
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		
		// Draw page texture
		minecraft.getTextureManager().bindTexture(sources.get(currentPage));
		blit(width / 2 - imageWidth / 2, 5, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
		
		
		// Render other stuff aswell (buttons etc)
		super.render(mouseX, mouseY, partialTicks);
	}
}