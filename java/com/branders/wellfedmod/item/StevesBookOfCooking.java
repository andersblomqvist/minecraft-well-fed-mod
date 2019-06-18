package com.branders.wellfedmod.item;

import com.branders.wellfedmod.gui.GuiCookeryBook;
import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class StevesBookOfCooking extends CookeryBook 
{
	public StevesBookOfCooking(Properties builder) { super(builder); }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		Minecraft mc = Minecraft.getInstance();
		
		if(!worldIn.isRemote)
			mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiCookeryBook(GuiCookeryBookPagesList.getStevesBookPages())));
		
		ItemStack book = new ItemStack(ItemList.steves_book_of_cooking);
		return new ActionResult<>(EnumActionResult.SUCCESS, book);
	}
	
}
