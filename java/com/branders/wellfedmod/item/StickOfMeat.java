package com.branders.wellfedmod.item;

import com.branders.wellfedmod.config.WellFedConfig;
import com.branders.wellfedmod.lists.PotionList;
import com.branders.wellfedmod.potion.WellFedPotionEffect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StickOfMeat extends ItemFood
{
	// How many hunger bars to fill (max = 20)
	public static int healAmount = 16;
	
	// How full we become, mainly set by the potion effect for this item
	public static float saturation = 0.5F;
	
	// Whether wolves likes it or not
	public static boolean meat = false;
	
	// 5 Min duration time
	private int potionDurationTime = WellFedConfig.stick_of_meat_buff_duration.get();
	
	// Amplifier or potion level
	private int level = 0;					
	
	/**
	 * 	Constructor
	 */
	public StickOfMeat(int healAmountIn, float saturation, boolean meat, Properties builder) 
	{
		super(healAmountIn, saturation, meat, builder);
	}
	
	/**
	 * 	Called when we ate the food
	 */
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) 
	{
		if(!worldIn.isRemote)
		{
			// Remove current Well Fed potion effect
			player.removePotionEffect(PotionList.well_fed);
			
			// Add new Well Fed effect (false = no beacon buff) (false = don't show particles) (true = show icon)
			player.addPotionEffect(new WellFedPotionEffect(PotionList.well_fed, potionDurationTime, level, false, false, true));
		}
	}
}
