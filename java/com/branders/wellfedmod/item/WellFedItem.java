package com.branders.wellfedmod.item;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * 	Bundle specific item behaviours into one class such as specific
 * 	item tooltips, bowl drop and glow effect.	
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class WellFedItem extends Item 
{	
	private boolean dropBowl;
	private boolean hasGlow;
	
	public WellFedItem(Properties properties, boolean dropBowl, boolean hasGlow) {
		super(properties);
		
		this.dropBowl = dropBowl;
		this.hasGlow = hasGlow;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		if(!dropBowl)
			return super.onItemUseFinish(stack, worldIn, entityLiving);
		
		((PlayerEntity)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1));
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	/**
	 * 	Add tooltip text for buffs this food gives, just like potions
	 */
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		List<Pair<EffectInstance, Float>> list = stack.getItem().getFood().getEffects();
		for(Pair<EffectInstance, Float> p : list)
		{
			EffectInstance effect = (EffectInstance) p.getLeft();
			String effectName = effect.getEffectName();
			int duration = MathHelper.floor((float)effect.getDuration());
			String durationString = StringUtils.ticksToElapsedTime(duration);
			
			TranslationTextComponent text = new TranslationTextComponent(effectName);
			text.appendText(" (" + durationString + ")");
			tooltip.add(text.applyTextStyle(TextFormatting.BLUE));
		}
		// super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return hasGlow;
	}
}
