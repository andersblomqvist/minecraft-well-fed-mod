package com.branders.wellfedmod.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BatSoupItem extends Item
{
	private static final TranslationTextComponent textComponent = 
			new TranslationTextComponent("tooltip.wellfedmod.bat_soup");
	
	public BatSoupItem(Properties properties) {
		super(properties);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) 
	{
		((PlayerEntity)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1));
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(textComponent.applyTextStyle(TextFormatting.RED));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
