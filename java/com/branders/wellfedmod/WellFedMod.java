package com.branders.wellfedmod;

import com.branders.wellfedmod.block.GreatFeast;
import com.branders.wellfedmod.config.Config;
import com.branders.wellfedmod.event.MobDropEvent;
import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.item.CookeryBook;
import com.branders.wellfedmod.item.GoldenStew;
import com.branders.wellfedmod.item.GreatFeastItem;
import com.branders.wellfedmod.item.SeafoodMagnifique;
import com.branders.wellfedmod.item.StickOfMeat;
import com.branders.wellfedmod.lists.BlockList;
import com.branders.wellfedmod.lists.ItemList;
import com.branders.wellfedmod.lists.PotionList;
import com.branders.wellfedmod.potion.WellFedPotion;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

/**
 * 	Vanilla friendly mod adding new foods to Minecraft Forge 1.13
 * 
 * 	@author Anders <Branders> Blomqvist
 * 		
 */
@Mod("wellfedmod")
public class WellFedMod
{
	public static final String modid = "wellfedmod";

    public WellFedMod() 
    {
    	// Get config
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.config);
    	Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("wellfedmod-config.toml").toString());
    	
    	// Register event
    	MinecraftForge.EVENT_BUS.register(new MobDropEvent());
     	MinecraftForge.EVENT_BUS.register(this);
     	
     	GuiCookeryBookPagesList.initPages();
    }
    
    /**
     * 	Register new content
     */
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents 
    {	
    	/**
	 	 * 	Block registry
	 	 */
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
		{
			blockRegistryEvent.getRegistry().registerAll
			(
				BlockList.great_feast = new GreatFeast(Block.Properties.from(Blocks.CAKE)).setRegistryName(location("great_feast"))
			);
		}
		
		/**
	 	 * 	Item registry
	 	 */
		@SubscribeEvent
	    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) 
	    {
	 		itemRegistryEvent.getRegistry().registerAll
	 		(
 				ItemList.stick_of_raw_meat = new Item(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("stick_of_raw_meat")),
 				ItemList.stick_of_meat = new StickOfMeat(StickOfMeat.healAmount, StickOfMeat.saturation, StickOfMeat.meat, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("stick_of_meat")),
 				
 				ItemList.uncooked_seafood = new Item(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("uncooked_seafood")),
				ItemList.seafood_magnifique = new SeafoodMagnifique(SeafoodMagnifique.healAmount, SeafoodMagnifique.saturation, SeafoodMagnifique.meat, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("seafood_magnifique")),
				
				ItemList.bat_wing = new Item(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("bat_wing")),
				ItemList.crispy_bat_wing = new ItemFood(5, 0.1F, false, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("crispy_bat_wing")),
				
				ItemList.squid_limb = new Item(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("squid_limb")),
				ItemList.grilled_squid_limb = new ItemFood(5, 0.1F, false, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("grilled_squid_limb")),
				
				ItemList.golden_stew = new GoldenStew(GoldenStew.healAmount, GoldenStew.saturation, GoldenStew.meat, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("golden_stew")),
				
				ItemList.cookery_book = new CookeryBook(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("cookery_book")),
				// ItemList.steves_book_of_cooking = new StevesBookOfCooking(new Item.Properties().rarity(EnumRarity.RARE)).setRegistryName(location("steves_book_of_cooking")),
						
				ItemList.great_feast_item = new GreatFeastItem(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("great_feast_item"))
	 		);
	    }
		
		/**
	 	 * 	Potion registry
	 	 */
		@SubscribeEvent
		public static void onPotionsRegistry(final RegistryEvent.Register<Potion> potionRegistryEvent)
		{
			potionRegistryEvent.getRegistry().registerAll
			(
				PotionList.well_fed = (new WellFedPotion(false, 16262179)).setBeneficial().setRegistryName(location("well_fed"))
			);
		}
		
		/**
		 * 	Returns a new Resource Location with format: modid:name
		 * 	Makes it easy to assign new RegistryNames
		 */
        private static ResourceLocation location(String name)
        {
        	return new ResourceLocation(modid, name);
        }
    }
}