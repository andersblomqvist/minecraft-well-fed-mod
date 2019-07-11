package com.branders.wellfedmod.lists;

import com.branders.wellfedmod.config.WellFedConfig;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList 
{
	public static Food stick_of_meat = (new Food.Builder()).hunger(16).saturation(0.8F).meat().effect(new EffectInstance(EffectList.well_fed, WellFedConfig.stick_of_meat_buff_duration.get(), 0), 1).build();
	public static Food seafood_magnifique = (new Food.Builder()).hunger(12).saturation(0.5F).meat().effect(new EffectInstance(EffectList.well_fed, WellFedConfig.seafood_magnifique_buff_duration.get(), 0), 1).build();
	public static Food golden_stew = (new Food.Builder()).hunger(3).saturation(1.2F).meat().effect(new EffectInstance(Effects.LUCK, 6000, 0), 1).build();	
	
	public static Food crispy_bat_wing = (new Food.Builder()).hunger(5).saturation(0.2F).meat().build();
	public static Food grilled_squid_limb = (new Food.Builder()).hunger(5).saturation(0.2F).meat().build();
}
