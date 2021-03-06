package com.branders.wellfedmod.block;

import com.branders.wellfedmod.config.WellFedConfig;
import com.branders.wellfedmod.lists.EffectList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class GreatFeast extends CakeBlock
{
	public static final IntegerProperty BITES = BlockStateProperties.BITES_0_6;
	protected static final VoxelShape[] field_196402_b = new VoxelShape[]{Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.makeCuboidShape(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)};
	
	// Get duration time from config
	private int potionDurationTime = WellFedConfig.great_feast_buff_duration.get();
	
	// Amplifier or potion level
	private int level = 0;
	
	public GreatFeast(Properties builder) 
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(BITES, Integer.valueOf(0)));
	}
	
	/**
	 * 	When we right click the block
	 */
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult side) 
	{
		if(!worldIn.isRemote)
			return this.eatFeast(worldIn, pos, state, player);
		
		else
		{
			ItemStack itemstack = player.getHeldItem(hand);
			return this.eatFeast(worldIn, pos, state, player) || itemstack.isEmpty();
		}
	}
	
	/**
	 * 	Eat function
	 */
	private boolean eatFeast(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		if(!player.canEat(false))
			return false;
	
		else
		{
			player.getFoodStats().addStats(10, 0.5F);
			
			// Remove current Well Fed potion effect
			// player.removePotionEffect(PotionList.well_fed);
			player.removePotionEffect(EffectList.well_fed);
			
			// Add new Well Fed effect (false = no beacon buff) (false = don't show particles) (true = show icon)
			// player.addPotionEffect(new WellFedPotionEffect(PotionList.well_fed, potionDurationTime, level, false, false, true));
			player.addPotionEffect(new EffectInstance(EffectList.well_fed, potionDurationTime, level, false, false, true));
			
			int i = state.get(BITES);
			
			if(i < 6)
				worldIn.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), 3);
			else
				worldIn.removeBlock(pos, true);
			
			return true;
		}
	}
}
