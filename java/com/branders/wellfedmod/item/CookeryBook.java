package com.branders.wellfedmod.item;

//import com.branders.wellfedmod.gui.GuiCookeryBook;
//import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CookeryBook extends Item
{
	public CookeryBook(Properties builder) { super(builder); }
	
	/**
	 * 	Open Book GUI when right click with this item
	 */
	@OnlyIn(Dist.CLIENT)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		Minecraft mc = Minecraft.getInstance();

		if(worldIn.isRemote)
			mc.addScheduledTask(() -> mc.displayGuiScreen(new com.branders.wellfedmod.gui.GuiCookeryBook(com.branders.wellfedmod.gui.GuiCookeryBookPagesList.getRecipeBookPages())));
		
		ItemStack book = new ItemStack(ItemList.cookery_book);
		return new ActionResult<>(EnumActionResult.SUCCESS, book);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return false;
	}
}
