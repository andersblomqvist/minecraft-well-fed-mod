package com.branders.wellfedmod.item;

import com.branders.wellfedmod.lists.BlockList;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;


public class GreatFeastItem extends Item 
{
	private final BlockState blockState;
	
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
	public ActionResultType onItemUse(ItemUseContext ctx) 
	{
		IWorld iworld = ctx.getWorld();
		BlockPos blockpos = ctx.getPos().up();
		
		if(ctx.getFace() == Direction.UP && iworld.isAirBlock(blockpos))
		{
			ItemStack itemstack = ctx.getItem();
	        PlayerEntity entityplayer = ctx.getPlayer();
			
	        if(entityplayer instanceof ServerPlayerEntity)
	        	CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)entityplayer, blockpos, itemstack);
			
	        // Place out block
	        iworld.setBlockState(blockpos, blockState, 1);
	        
			itemstack.shrink(1);
			return ActionResultType.SUCCESS;
		}
		
		else
			return ActionResultType.FAIL;
	}
}
