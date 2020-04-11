package com.branders.wellfedmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 	Just make the Pestle a container item to itself
 */
public class PestleItem extends Item
{
	public PestleItem(Properties properties) { super(properties); }
	
	@Override
	public boolean hasContainerItem() { return true; }
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack stack = new ItemStack(this);
		return stack;
	}
}