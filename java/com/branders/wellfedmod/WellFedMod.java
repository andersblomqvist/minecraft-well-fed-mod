package com.branders.wellfedmod;

import com.branders.wellfedmod.block.GreatFeast;
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
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("wellfedmod")
public class WellFedMod
{
	public static final String modid = "wellfedmod";
	
    // Directly reference a log4j logger.
    // private static final Logger LOGGER = LogManager.getLogger();

    public WellFedMod() 
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    	
    }

    private void processIMC(final InterModProcessEvent event)
    {
    	
    }
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
    	
    }
    
	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents 
    {	
    	
    	/**
	 	 * 	------------------------
	 	 * 	Block registry
	 	 * 	------------------------
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
	 	 * 	------------------------
	 	 * 	Item registry
	 	 * 	------------------------
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
				ItemList.golden_stew = new GoldenStew(GoldenStew.healAmount, GoldenStew.saturation, GoldenStew.meat, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("golden_stew")),
				
				ItemList.great_feast_item = new GreatFeastItem(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("great_feast_item"))
	 		);
	    }
		
		/**
	 	 * 	------------------------
	 	 * 	Potion registry
	 	 * 	------------------------
	 	 */
		@SubscribeEvent
		public static void onPotionsRegistry(final RegistryEvent.Register<Potion> potionRegistryEvent)
		{
			potionRegistryEvent.getRegistry().registerAll
			(
				PotionList.well_fed = (new WellFedPotion(false, 16262179)).setBeneficial().setRegistryName(location("well_fed"))
			);
		}
		
        private static ResourceLocation location(String name)
        {
        	return new ResourceLocation(modid, name);
        }
    }
}
