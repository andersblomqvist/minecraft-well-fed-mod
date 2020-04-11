package com.branders.wellfedmod;

import com.branders.wellfedmod.config.Config;
import com.branders.wellfedmod.event.EventHandler;
import com.branders.wellfedmod.gui.GuiCookeryBookPagesList;
import com.branders.wellfedmod.gui.MortarAndPestleScreen;
import com.branders.wellfedmod.lists.ContainerTypeList;
import com.branders.wellfedmod.registry.ModRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

/**
 * 	Vanilla friendly mod adding new foods to Minecraft Forge 1.14
 * 
 * 	@author Anders <Branders> Blomqvist
 */
@Mod(WellFedMod.MODID)
public class WellFedMod
{
	public static final String MODID = "wellfedmod";

    public WellFedMod()
    {
    	// Get config
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.config);
    	Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get()
    			.resolve("wellfedmod-config.toml")
    			.toString());
    	
    	// Register event
    	MinecraftForge.EVENT_BUS.register(new EventHandler());
     	MinecraftForge.EVENT_BUS.register(this);
     	
     	GuiCookeryBookPagesList.initPages();
     	
     	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initClient);
    }
    
    private void initClient(final FMLClientSetupEvent event)
    {
    	ScreenManager.registerFactory(
    			ContainerTypeList.mortar_and_pestle, 
    			MortarAndPestleScreen::new);
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
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
			ModRegistry.blockRegistry(event.getRegistry());
		}
		
		/**
	 	 * 	Item registry
	 	 */
		@SubscribeEvent
	    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
	 		ModRegistry.itemRegistry(event.getRegistry());
	    }
		
		/**
	 	 * 	Potion registry
	 	 */
		@SubscribeEvent
		public static void onPotionsRegistry(final RegistryEvent.Register<Effect> event) {
			ModRegistry.effectRegistry(event.getRegistry());
		}
		
		/**
		 * 	Container registry
		 */
		@SubscribeEvent
		public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {	
			ModRegistry.containerRegistry(event.getRegistry());
		}
    }
}