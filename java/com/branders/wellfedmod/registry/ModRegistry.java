package com.branders.wellfedmod.registry;

import com.branders.wellfedmod.WellFedMod;
import com.branders.wellfedmod.block.GreatFeast;
import com.branders.wellfedmod.block.MortarAndPestleBlock;
import com.branders.wellfedmod.item.BatSoupItem;
import com.branders.wellfedmod.item.CookeryBook;
import com.branders.wellfedmod.item.PestleItem;
import com.branders.wellfedmod.item.StevesBookOfCooking;
import com.branders.wellfedmod.item.WellFedItem;
import com.branders.wellfedmod.lists.BlockList;
import com.branders.wellfedmod.lists.ContainerTypeList;
import com.branders.wellfedmod.lists.EffectList;
import com.branders.wellfedmod.lists.FoodList;
import com.branders.wellfedmod.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class ModRegistry
{
	/**
	 * 	Register all items here
	 */
	public static void itemRegistry(IForgeRegistry<Item> reg)
	{
		reg.registerAll (
			ItemList.stick_of_raw_meat = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("stick_of_raw_meat")),
				
			ItemList.stick_of_meat = new WellFedItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.stick_of_meat),
				false, false)
				.setRegistryName(name("stick_of_meat")),
				
			ItemList.uncooked_seafood = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("uncooked_seafood")),
			
			ItemList.seafood_magnifique = new WellFedItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.seafood_magnifique),
				true, false)
				.setRegistryName("seafood_magnifique"),
				
			ItemList.seafood_magnifique_breath = new WellFedItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.seafood_magnifique_breath),
				true, true)
				.setRegistryName("seafood_magnifique_breath"),
			
			ItemList.bat_wing = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("bat_wing")),
			
			ItemList.crispy_bat_wing = new Item(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.crispy_bat_wing))
				.setRegistryName(name("crispy_bat_wing")),
				
    		ItemList.bat_soup = new BatSoupItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.bat_soup))
				.setRegistryName(name("bat_soup")),
			
			ItemList.squid_limb = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("squid_limb")),
				
			ItemList.grilled_squid_limb = new Item(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.grilled_squid_limb))
				.setRegistryName(name("grilled_squid_limb")),
			
			ItemList.golden_stew = new WellFedItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.golden_stew),
				true, true)
				.setRegistryName(name("golden_stew")),
			
			ItemList.cookery_book = new CookeryBook(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("cookery_book")),
				
			ItemList.steves_book_of_cooking = new StevesBookOfCooking(new Item.Properties()
				.rarity(Rarity.RARE))
				.setRegistryName(name("steves_book_of_cooking")),
			
			ItemList.mortar_and_pestle_item = new BlockItem
				(BlockList.mortar_and_pestle, new Item.Properties()
				.group(ItemGroup.DECORATIONS))
				.setRegistryName(name("mortar_and_pestle_item")),
			
			ItemList.pestle = new PestleItem(new Item.Properties()
				.group(ItemGroup.TOOLS)
				.maxStackSize(1))
				.setRegistryName(name("pestle")),
			
			ItemList.forest_spices = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("forest_spices")),
			
			ItemList.nether_spices = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("nether_spices")),
				
    		ItemList.ocean_spices = new Item(new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("ocean_spices")),
					
			ItemList.first_night_meal = new Item(new Item.Properties()
				.food(FoodList.first_night_meal))
				.setRegistryName(name("first_night_meal")),
			
			ItemList.first_night_meal_cooked = new WellFedItem(new Item.Properties()
				.food(FoodList.first_night_meal_cooked),
				false, false)
				.setRegistryName(name("first_night_meal_cooked")),
			
			ItemList.steves_mining_meal_raw = new Item(new Item.Properties())
				.setRegistryName(name("steves_mining_meal_raw")),
			
			ItemList.steves_mining_meal = new WellFedItem(new Item.Properties()
				.food(FoodList.steves_mining_meal)
				.rarity(Rarity.RARE),
				false, false)
				.setRegistryName(name("steves_mining_meal")),
			
			ItemList.firecracker_salmon = new WellFedItem(new Item.Properties()
				.group(ItemGroup.FOOD)
				.food(FoodList.firecracker_salmon),
				false, false)
				.setRegistryName(name("firecracker_salmon")),
			
			ItemList.great_feast_item = new BlockItem(BlockList.great_feast, new Item.Properties()
				.group(ItemGroup.FOOD))
				.setRegistryName(name("great_feast_item"))
		);
	}
	
	/**
	 * 	Register all blocks
	 */
	public static void blockRegistry(IForgeRegistry<Block> reg)
	{
		reg.registerAll
		(
			BlockList.mortar_and_pestle = new MortarAndPestleBlock(
				Block.Properties.from(Blocks.FLOWER_POT))
				.setRegistryName(name("mortar_and_pestle")),
				
			BlockList.great_feast = new GreatFeast(
				Block.Properties.from(Blocks.CAKE))
				.setRegistryName(name("great_feast"))
		);
	}
	
	/**
	 * 	Register potion effects
	 */
	public static void effectRegistry(IForgeRegistry<Effect> reg)
	{
		reg.register(EffectList.well_fed.setRegistryName(name("well_fed")));
	}
	
	/**
	 * 	Register containers
	 */
	public static void containerRegistry(IForgeRegistry<ContainerType<?>> reg)
	{
		reg.register(ContainerTypeList.mortar_and_pestle.setRegistryName(name("mortar_and_pestle")));
	}
	
	/**
	 * 	Returns a new Resource Location with format: modid:name
	 * 	Makes it easy to assign new registry names
	 */
    private static ResourceLocation name(String name)
    {
    	return new ResourceLocation(WellFedMod.MODID, name);
    } 
}
