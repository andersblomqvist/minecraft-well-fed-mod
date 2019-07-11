package com.branders.wellfedmod.item;

import com.branders.wellfedmod.gui.GuiCookeryBook;
import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class StevesBookOfCooking extends CookeryBook 
{
	public StevesBookOfCooking(Properties builder) { super(builder); }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		Minecraft mc = Minecraft.getInstance();
		
		if(!worldIn.isRemote)
			mc.displayGuiScreen(new GuiCookeryBook(GuiCookeryBookPagesList.getStevesBookPages(), new TranslationTextComponent("why do I need this?")));
		
		ItemStack book = new ItemStack(ItemList.steves_book_of_cooking);
		return new ActionResult<>(ActionResultType.SUCCESS, book);
	}
	
}
