package com.branders.wellfedmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WellFedConfig 
{
	public static ForgeConfigSpec.IntValue great_feast_buff_duration;
	public static ForgeConfigSpec.IntValue stick_of_meat_buff_duration;
	public static ForgeConfigSpec.IntValue seafood_magnifique_buff_duration;
	
	public static ForgeConfigSpec.IntValue firecracker_salmon_buff_duration;
	
	public static ForgeConfigSpec.IntValue bat_wing_drop_chance;
	public static ForgeConfigSpec.IntValue squid_limb_drop_chance;
	public static ForgeConfigSpec.IntValue rare_book_drop_chance;
	
	public static ForgeConfigSpec.IntValue steves_mining_meal_buff_duration;
	public static ForgeConfigSpec.IntValue first_night_meal_buff_duration;
	
	public static void init(ForgeConfigSpec.Builder common)
	{
		common.comment("WellFed Config | Duration inputs ranges from: 0 - 48000 and drop rates: 0 - 100 %");
		
		great_feast_buff_duration = common
				.defineInRange("wellfedconfig.great_feast_buff_duration", 12000, 0, 48000);
		
		stick_of_meat_buff_duration = common
				.defineInRange("wellfedconfig.stick_of_meat_buff_duration", 12000, 0, 48000);
		
		seafood_magnifique_buff_duration = common
				.defineInRange("wellfedconfig.seafood_magnifique_buff_duration", 6000, 0, 48000);
		
		bat_wing_drop_chance = common
				.defineInRange("wellfedconfig.bat_wing_drop_chance", 50, 0, 100);
		
		squid_limb_drop_chance = common
				.defineInRange("wellfedconfig.squid_limb_drop_chance", 50, 0, 100);
		
		rare_book_drop_chance = common
				.defineInRange("wellfedconfig.rare_book_drop_chance", 2, 0, 100);
		
		steves_mining_meal_buff_duration = common
				.defineInRange("wellfedconfig.steves_mining_meal_buff_duration", 24000, 0, 48000);
		
		first_night_meal_buff_duration = common
				.defineInRange("wellfedconfig.first_night_meal_buff_duration", 6000, 0, 48000);
		
		firecracker_salmon_buff_duration = common
				.defineInRange("wellfedconfig.firecracker_salmon_buff_duration", 3000, 0, 48000);
	}
}