package com.branders.wellfedmod.lists;

import com.branders.wellfedmod.config.WellFedConfig;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList 
{
	public static Food stick_of_meat = (new Food.Builder())
			.hunger(16)
			.saturation(0.8F)
			.meat()
			.effect(new EffectInstance(EffectList.well_fed, 
					WellFedConfig.stick_of_meat_buff_duration.get(), 0), 1)
			.build();
	
	public static Food seafood_magnifique = (new Food.Builder())
			.hunger(12)
			.saturation(0.5F)
			.meat()
			.effect(new EffectInstance(EffectList.well_fed, 
					WellFedConfig.seafood_magnifique_buff_duration.get(), 0), 1)
			.build();
	
	public static Food seafood_magnifique_breath = (new Food.Builder())
			.hunger(12)
			.saturation(0.5F)
			.meat()
			.effect(new EffectInstance(EffectList.well_fed, 
					WellFedConfig.seafood_magnifique_buff_duration.get(), 0), 1)
			.effect(new EffectInstance(Effects.WATER_BREATHING, 6000, 0), 1)
			.build();
	
	public static Food golden_stew = (new Food.Builder())
			.hunger(3)
			.saturation(1.2F)
			.meat().effect(new EffectInstance(Effects.LUCK, 6000, 0), 1)
			.build();	
	
	public static Food crispy_bat_wing = (new Food.Builder())
			.hunger(5)
			.saturation(0.2F)
			.meat()
			.build();
	
	public static Food bat_soup = (new Food.Builder())
			.hunger(8)
			.saturation(0.5F)
			.effect(new EffectInstance(Effects.POISON, 600, 0), 1)
			.effect(new EffectInstance(Effects.NAUSEA, 600, 0), 1)
			.build();
	
	public static Food grilled_squid_limb = (new Food.Builder())
			.hunger(5)
			.saturation(0.2F)
			.meat()
			.build();
	
	public static Food steves_mining_meal = (new Food.Builder())
			.hunger(16)
			.saturation(0.8F)
			.meat()
			.effect(new EffectInstance(Effects.REGENERATION, 100, 1), 1.0F)
			.effect(new EffectInstance(Effects.ABSORPTION, 2400, 0), 1.0F)
			.effect(new EffectInstance(EffectList.well_fed, 
					WellFedConfig.steves_mining_meal_buff_duration.get(), 0), 1)
			.effect(new EffectInstance(Effects.STRENGTH, 
					WellFedConfig.steves_mining_meal_buff_duration.get(), 0, false, false), 1)
			.build();
	
	public static Food first_night_meal = (new Food.Builder())
			.hunger(3)
			.saturation(0.1F)
			.build();
	
	public static Food first_night_meal_cooked = (new Food.Builder())
			.hunger(4)
			.saturation(0.3F)
			.effect(new EffectInstance(EffectList.well_fed, 
					WellFedConfig.first_night_meal_buff_duration.get(), 0), 1)
			.build();
	
	public static Food firecracker_salmon = (new Food.Builder())
			.hunger(6)
			.saturation(0.96F)
			.effect(new EffectInstance(Effects.FIRE_RESISTANCE,
					WellFedConfig.firecracker_salmon_buff_duration.get(), 0), 1)
			.build();
}
