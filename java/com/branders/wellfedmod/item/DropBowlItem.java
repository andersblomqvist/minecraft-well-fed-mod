package com.branders.wellfedmod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class DropBowlItem extends Item
{
	// This property is set to true for spiced up seafood magifique
	private boolean hasGlow = false;
	
	public DropBowlItem(Properties properties, boolean hasGlow) {
		super(properties);
		
		this.hasGlow = hasGlow;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) 
	{
		((PlayerEntity)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1));
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return hasGlow;
	}
}
