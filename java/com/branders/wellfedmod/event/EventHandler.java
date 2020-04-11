package com.branders.wellfedmod.event;

import java.util.Random;

import com.branders.wellfedmod.WellFedMod;
import com.branders.wellfedmod.config.WellFedConfig;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EventHandler
{
	private float BAT_DROP_RATE = WellFedConfig.bat_wing_drop_chance.get() / 100f;
	private float SQUID_DROP_RATE = WellFedConfig.squid_limb_drop_chance.get() / 100f;
	private float RARE_BOOK_DROP_RATE = WellFedConfig.rare_book_drop_chance.get() / 100f;
	private Random random = new Random();
	
	/**
     * 	Called when a mob drops items
     * 	Makes so that Squids and Bats drops new items
     */
	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event)
	{
		Entity entity = event.getEntity();
		EntityType<?> entityType = entity.getType();
		
		// Do Squid drop
		if(entityType.equals(EntityType.SQUID))
		{
			if(random.nextFloat() > SQUID_DROP_RATE)
				return;
			
			// Get Squid Limb
			ItemStack itemStack = new ItemStack(ItemList.squid_limb);
			
			// Add it to drops
			addDrop(itemStack, event, entity);
		}
		
		// Do bat drop
		else if(entityType.equals(EntityType.BAT))
		{
			if(random.nextFloat() > BAT_DROP_RATE)
				return;
			
			ItemStack itemStack = new ItemStack(ItemList.bat_wing);
			addDrop(itemStack, event, entity);
		}
		
		/**
		 *	Drop book if zombie has it equipped
		 */
		else if(entityType.equals(EntityType.ZOMBIE))
		{
			for(ItemStack stack : entity.getHeldEquipment())
			{
				if(stack.getItem() == ItemList.steves_book_of_cooking)
				{
					ItemStack itemStack = new ItemStack(ItemList.steves_book_of_cooking);
					addDrop(itemStack, event, entity);
					return;
				}
			}
		}
	}
	
	/**
	 * 	Add equipment to zombie 
	 */
	@SubscribeEvent
	public void onMobSpawn(LivingSpawnEvent.SpecialSpawn event)
	{
		if(event.getEntity() instanceof ZombieEntity)
		{
			if(random.nextFloat() > RARE_BOOK_DROP_RATE)
				return;
			
			ZombieEntity zombie = (ZombieEntity) event.getEntity();
			zombie.setItemStackToSlot(
					EquipmentSlotType.OFFHAND, 
					new ItemStack(ItemList.steves_book_of_cooking));
		}		
	}
	
	/**
	 * 	Add item to drops when mob died.
	 * 
	 * 	@param itemStack What type of item will be dropped
	 * 	@param event Reference to mob event
	 * 	@param entity Reference to the entity which have died
	 */
	private void addDrop(ItemStack itemStack, LivingDropsEvent event, Entity entity)
	{
		event.getDrops().add(
				new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, itemStack));
	}
	
	/**
	 * 	Event fired when player craft an item
	 * 	Used to remove pestle when crafting Mortar and Pestle.
	 */
	@SubscribeEvent
	public void playerCraftedEvent(PlayerEvent.ItemCraftedEvent event)
	{
		if(event.getCrafting().getItem().equals(ItemList.mortar_and_pestle_item))
		{
			// Crafting grid slots
			IInventory inventory = event.getInventory();
			
			// Search for Pestle and remove it
			for(int i = 0; i < inventory.getSizeInventory(); i++)
				if(inventory.getStackInSlot(i).getItem().equals(ItemList.pestle))
					inventory.removeStackFromSlot(i);
		}
	}
	
	/**
	 * 	Inject custom loot table to dungeon chest loot
	 */ 
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent event)
	{
		String type = "minecraft:chests/";
		String name = event.getName().toString();
		
		// Load tables with type chests
		if(name.startsWith(type))
		{
			String fileName = name.substring(type.length());
			
			switch(fileName)
			{
				case "simple_dungeon":
				case "shipwreck_treasure":
				case "end_city_treasure":
				case "stronghold_library":
				case "buried_treasure":
				case "abandoned_mineshaft":
					event.getTable().addPool(getInjectPool("simple_dungeon"));
				
			}
		}
	}
	
	private static LootPool getInjectPool(String entryName) 
	{
		return LootPool.builder().addEntry(getInjectEntry(entryName, 1)).build();
	}

	@SuppressWarnings("rawtypes")
	private static LootEntry.Builder getInjectEntry(String name, int weight) 
	{
		ResourceLocation table = new ResourceLocation(WellFedMod.MODID, "inject/" + name);
	
		return TableLootEntry.builder(table).weight(weight);
	}
	
}
