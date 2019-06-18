package com.branders.wellfedmod.item;

import com.branders.wellfedmod.lists.BlockList;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class GreatFeastItem extends Item 
{
	private final IBlockState blockState;
	
	public GreatFeastItem(Properties properties) 
	{
		super(properties);
		this.blockState = BlockList.great_feast.getDefaultState();
	}
	
	/**
	 * 	Called when we right click with the item
	 * 	Places out the Great Feast Block
	 */
	@Override
	public EnumActionResult onItemUse(ItemUseContext ctx) 
	{
		IWorld iworld = ctx.getWorld();
		BlockPos blockpos = ctx.getPos().up();
		
		if(ctx.getFace() == EnumFacing.UP && iworld.isAirBlock(blockpos))
		{
			ItemStack itemstack = ctx.getItem();
	        EntityPlayer entityplayer = ctx.getPlayer();
			
	        if(entityplayer instanceof EntityPlayerMP)
	        	CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)entityplayer, blockpos, itemstack);
			
	        // Place out block
	        iworld.setBlockState(blockpos, blockState, 1);
	        
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		
		else
			return EnumActionResult.FAIL;
	}	
}