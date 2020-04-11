package com.branders.wellfedmod.container;

import com.branders.wellfedmod.lists.BlockList;
import com.branders.wellfedmod.lists.ContainerTypeList;
import com.branders.wellfedmod.lists.ItemList;
import com.google.common.collect.ImmutableList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

/**
 * 	Handle logic behind container GUI and "recipes" for spices
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class MortarAndPestleContainer extends Container
{
	// How much spices per ingredient
	private final int CRAFT_AMOUNT = 3;
	
	/**
	 * 	List of all valid items which can be used in the Mortar and Pestle
	 */
	private static final ImmutableList<Item> validIngredients = ImmutableList.of(
			// Forest
			Items.ACACIA_SAPLING,
			Items.BIRCH_SAPLING,
			Items.DARK_OAK_SAPLING,
			Items.JUNGLE_SAPLING,
			Items.OAK_SAPLING,
			Items.SPRUCE_SAPLING,
			
			// Nether
			Items.NETHER_WART,
			
			// Ocean
			Items.DRIED_KELP
	);
	
	private ItemStack tempStack = ItemStack.EMPTY;
	private IWorldPosCallable pos;
	
	private Slot ingredientSlot;
	private Slot resultSlot;
	
	private long gameTime;
	
	private Runnable inventoryUpdateListener = () -> {};
	
	private IInventory ingredientSlotInventory = new Inventory(1)
	{
		/**
       	 * 	For tile entities, ensures the chunk containing the tile entity 
       	 * 	is saved to disk later - the game won't think it hasn't changed 
       	 * 	and skip it.
       	 */
		public void markDirty() 
		{
			super.markDirty();
			onCraftMatrixChanged(this);
			inventoryUpdateListener.run();
      	}
	};
	
	private CraftResultInventory inventory = new CraftResultInventory();
	
	/**
	 * 	Dummy constructor for easy registration
	 */
	public MortarAndPestleContainer(int window, PlayerInventory playerInventory)
	{
		this(window, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	/**
	 * 	Main constructor. Setup all Slots
	 */
	public MortarAndPestleContainer(int window, PlayerInventory playerInventory, IWorldPosCallable pos)
	{
		super(ContainerTypeList.mortar_and_pestle, window);
		
		this.pos = pos;
		
		ingredientSlot = addSlot(new Slot(ingredientSlotInventory, 0, 53, 34));
		
		// Slot for crafted item (spices)
		resultSlot = addSlot(new Slot(inventory, 1, 111, 34)
		{
			/**
			 * 	Prevents items to be placed in this slot by the player
			 */
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
			
			/**
			 * 	Called when player takes the crafted item.
			 * 	Here we reduce ingredient stack by 1 and update result slot.
			 * 	We also play a sound.
			 */
			public ItemStack onTake(PlayerEntity player, ItemStack stack)
			{		
				ItemStack itemStack = ingredientSlot.decrStackSize(1);
				
	            if (!itemStack.isEmpty())
	            	updateAvailableRecipes(itemStack);
	            
	            stack.getItem().onCreated(stack, player.world, player);
	            pos.consume((world, blockpos) ->
	            {
	            	long l = world.getGameTime();
	            	if (gameTime != l)
	            	{
	            		world.playSound((PlayerEntity)null, blockpos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            		gameTime = l;
	            	}

	            });
	            
	            return super.onTake(player, stack);
			}
		});
		
		// Setup player inventory slots
		for(int i = 0; i < 3; ++i)
			for(int j = 0; j < 9; ++j)
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	    for(int k = 0; k < 9; ++k)
	    	this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn)
	{
		ItemStack ingredient = ingredientSlot.getStack();
		
		// Leave if ingredient is air or the same
		if(ingredient.getItem() == tempStack.getItem())
			return;
		
		tempStack = ingredient.copy();
		updateAvailableRecipes(ingredient);
	}
	
	/**
	 * 	Get "recipe" for ingredient and put it in the result slot.
	 * 
	 * 	@param ingredient
	 */
	private void updateAvailableRecipes(ItemStack ingredient)
	{
		resultSlot.putStack(ItemStack.EMPTY);
		
		// Leave if ingredient slot is empty
		if(!ingredientSlot.getHasStack())
			return;
		
		if(isIngredient("sapling", ingredient.getItem()))
		{
			// Return Forest spices
			ItemStack forestSpices = new ItemStack(ItemList.forest_spices, CRAFT_AMOUNT);
			resultSlot.putStack(forestSpices);
		}
		else if(isIngredient("nether_wart", ingredient.getItem()))
		{
			// Return Nether spices
			ItemStack netherSpices = new ItemStack(ItemList.nether_spices, CRAFT_AMOUNT);
			resultSlot.putStack(netherSpices);
		}
		else if(isIngredient("kelp", ingredient.getItem()))
		{
			// Return ocean spices
			ItemStack oceanSpices = new ItemStack(ItemList.ocean_spices, CRAFT_AMOUNT);
			resultSlot.putStack(oceanSpices);
		}
	}
	
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return isWithinUsableDistance(pos, playerIn, BlockList.mortar_and_pestle);
	}
	
	@Override
	public ContainerType<?> getType()
	{
		return ContainerTypeList.mortar_and_pestle;
	}
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
	{
		return false;
	}
	
	/**
	 * 	Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * 	inventory and the other inventory(s).
	 * 
	 * 	Taken from StonecutterContainer
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			
			if (index == 1)
			{
				item.onCreated(itemstack1, playerIn.world, playerIn);
				
				if (!mergeItemStack(itemstack1, 2, 38, true))
					return ItemStack.EMPTY;
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			
			else if (index == 0) {
				if (!mergeItemStack(itemstack1, 2, 38, false)) 
					return ItemStack.EMPTY;
			}
			
			else if (validIngredients.contains(item)) {
				if (!mergeItemStack(itemstack1, 0, 1, false)) 
					return ItemStack.EMPTY;
			}
			
			else if (index >= 2 && index < 29) {
				if (!mergeItemStack(itemstack1, 29, 38, false))
					return ItemStack.EMPTY;
			}
				
			
			else if (index >= 29 && index < 38 && !mergeItemStack(itemstack1, 2, 29, false))
				return ItemStack.EMPTY;
			
			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			
			slot.onSlotChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			
			slot.onTake(playerIn, itemstack1);
			
			detectAndSendChanges();
		}
		
		return itemstack;
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn)
	{
		super.onContainerClosed(playerIn);
		inventory.removeStackFromSlot(1);
		pos.consume((world, blockpos) -> {
			clearContainer(playerIn, playerIn.world, ingredientSlotInventory);
		});
	}
	
	/**
	 * 	Return true/false if the ingredient matches the input {@code name} 
	 * 
	 * 	@param ingredient Valid item for crafting spcies
	 * 	@return true/false
	 */
	private boolean isIngredient(String name, Item ingredient)
	{
		String item = ingredient.getRegistryName().toString();
		
		if(item.contains(name))
			return true;
		else
			return false;
	}
}
