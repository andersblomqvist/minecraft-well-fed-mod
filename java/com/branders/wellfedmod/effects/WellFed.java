package com.branders.wellfedmod.effects;

import com.branders.wellfedmod.lists.EffectList;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class WellFed extends Effect 
{
	// How big saturation increase per tick
	private float strength = 0.35F;
	
	// Higher value = less frequent updates
	private int rate = 100; 
	
	public WellFed(EffectType typeIn, int liquidColorIn) 
	{
		super(typeIn, liquidColorIn);
	}
	
	/**
	 * 	Apply potion effect
	 */
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) 
	{
		if(!entityLivingBaseIn.world.isRemote)
			if(this == EffectList.well_fed && entityLivingBaseIn instanceof PlayerEntity)
				((PlayerEntity)entityLivingBaseIn).getFoodStats().addStats(amplifier + 1, strength);
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
}
