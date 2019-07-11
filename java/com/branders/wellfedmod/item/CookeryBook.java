package com.branders.wellfedmod.item;

import com.branders.wellfedmod.gui.GuiCookeryBook;
import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CookeryBook extends Item
{
	public CookeryBook(Properties builder) { super(builder); }
	
	/**
	 * 	Open Book GUI when right click with this item
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		Minecraft mc = Minecraft.getInstance();
		
		if(!worldIn.isRemote)
			mc.displayGuiScreen(new GuiCookeryBook(GuiCookeryBookPagesList.getRecipeBookPages(), new TranslationTextComponent("why do I need this?")));
		
		ItemStack book = new ItemStack(ItemList.cookery_book);
		return new ActionResult<>(ActionResultType.SUCCESS, book);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return false;
	}
}