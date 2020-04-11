package com.branders.wellfedmod.block;

import com.branders.wellfedmod.container.MortarAndPestleContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class MortarAndPestleBlock extends HorizontalBlock
{
	// Facing property connected to .json file
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	// Text inside container
	private static final TranslationTextComponent textComponent = 
			new TranslationTextComponent("container.wellfedmod.mortar_and_pestle");
	
	// Block hitbox
	protected static final VoxelShape SHAPE = 
			Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 9.0D, 11.0D);
	
	public MortarAndPestleBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, 
			BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY());
	}
	
	@Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
    	return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
    }
	
	/**
	 * 	If the block under breaks this will also break.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, 
			BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) 
				? Blocks.AIR.getDefaultState() 
				: super.updatePostPlacement(
							stateIn, 
							facing, 
							facingState, 
							worldIn, 
							currentPos, 
							facingPos);
	}
	
	/**
	 * 	Open container when block is clicked on.
	 */
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, 
			BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		player.openContainer(state.getContainer(worldIn, pos));
		return true;
	}
	
	/**
	 * 	Returns what container to open
	 */
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return new SimpleNamedContainerProvider((window, playerInventory, playerEntity) -> {
			return new MortarAndPestleContainer(
					window, 
					playerInventory, 
					IWorldPosCallable.of(worldIn, pos));
	    }, textComponent);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}