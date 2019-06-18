package com.branders.wellfedmod.potion;

import com.branders.wellfedmod.WellFedMod;
import com.branders.wellfedmod.lists.PotionList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class WellFedPotion extends Potion
{
	// Buff icon top right
	private ResourceLocation iconHUDTexture;
	
	// Buff icon in inventory
	private ResourceLocation iconInventoryTexture;
	
	// Higher value = less frequent updates
	private int rate = 100; 
	
	// How big saturation increase per tick
	private float strength = 0.35F;
	
	public WellFedPotion(boolean isBadEffectIn, int liquidColorIn) 
	{
		super(isBadEffectIn, liquidColorIn);
		
		// Get the HUD textures
		iconHUDTexture = new ResourceLocation(WellFedMod.modid, "/textures/gui/well_fed_hud_icon.png");
		iconInventoryTexture = new ResourceLocation(WellFedMod.modid, "/textures/gui/well_fed_inventory_icon.png");
	}
	
	/**
	 * 	Apply potion effect
	 */
	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) 
	{
		if(!entityLivingBaseIn.world.isRemote)
			if(this == PotionList.well_fed)
				((EntityPlayer)entityLivingBaseIn).getFoodStats().addStats(amplifier + 1, strength);
	}
	
	/**
	 * 	Check if we are ready to apply the potion effect
	 */
	@Override
	public boolean isReady(int duration, int amplifier) 
	{
		int k = rate >> amplifier;
		
		if(k > 0)
			return duration % k == 0;
		
		else
			return true;
	}
			
	/**
     * 	Called to draw the this Potion onto the player's inventory when it's active.
     */
	@Override
	public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) 
	{
		Minecraft.getInstance().getTextureManager().bindTexture(iconInventoryTexture);
		Gui.drawModalRectWithCustomSizedTexture(x - 8, y - 8, 0, 0, 32, 32, 48, 48);
	}
	
	/**
     * 	Called to draw the this Potion icon onto the player's ingame HUD when it's active.
     */
	@Override
	public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(iconHUDTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 18, 18, 18, 18);
	}
	
}
