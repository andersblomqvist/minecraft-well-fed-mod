package com.branders.wellfedmod.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemFood;
import net.minecraft.world.World;

public class GoldenStew extends ItemFood 
{
	// How many hunger bars to fill (max = 20)
	public static int healAmount = 3;
	
	// How full we become, mainly set by the potion effect for this item
	public static float saturation = 1.2F;
	
	// Whether wolves likes it or not
	public static boolean meat = false;
	
	// 5 Min duration time
	private int potionDurationTime = 6000;
	
	// Amplifier or potion level
	private int level = 0;
		
	/**
	 * 	Constructor
	 */
	public GoldenStew(int healAmountIn, float saturation, boolean meat, Properties builder) 
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
			// Remove current potion effect
			player.removePotionEffect(MobEffects.LUCK);
			
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, potionDurationTime, level));
		}
	}
	
	/**
    * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
    * the Item before the action is complete.
    */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) 
	{
		((EntityPlayer)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1));
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}