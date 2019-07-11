package com.branders.wellfedmod.event;

import java.util.Random;

import com.branders.wellfedmod.config.WellFedConfig;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class MobDropEvent
{
	private float BAT_DROP_RATE = WellFedConfig.bat_wing_drop_chance.get() / 100f;
	private float SQUID_DROP_RATE = WellFedConfig.squid_limb_drop_chance.get() / 100f;
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
			
			// Get the Bat Wing
			ItemStack itemStack = new ItemStack(ItemList.bat_wing);
					
			// Add it to drops
			addDrop(itemStack, event, entity);
		}
	}
	
	/**
	 * 	Add item to drops when mob died
	 */
	private void addDrop(ItemStack itemStack, LivingDropsEvent event, Entity entity)
	{
		event.getDrops().add(new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, itemStack));
	}
}
