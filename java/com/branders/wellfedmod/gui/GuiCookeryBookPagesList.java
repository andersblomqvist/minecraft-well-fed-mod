package com.branders.wellfedmod.gui;

import java.util.ArrayList;
import java.util.List;

import com.branders.wellfedmod.WellFedMod;

import net.minecraft.util.ResourceLocation;

public class GuiCookeryBookPagesList 
{
	// Max number of pages for book
	public static int MAX_PAGES_RECIPE_BOOK = 6;
	public static int MAX_PAGES_STEVES_BOOK = 2;
	
	// Path to GUI textures
	public static String RECIPE_BOOK_PATH = "/textures/gui/cookery_book_page_";
	public static String STEVES_BOOK_PATH = "/textures/gui/steves_book_of_cooking_";
	public static String FILE_TYPE = ".png";
	
	// List containing all the ResourceLocations for the book textures
	public static List<ResourceLocation> RECIPE_BOOK_PAGES = new ArrayList<ResourceLocation>();
	public static List<ResourceLocation> STEVES_BOOK_PAGES = new ArrayList<ResourceLocation>();
	
	public static void initPages()
	{
		fillResourceLocationList(RECIPE_BOOK_PATH, FILE_TYPE, MAX_PAGES_RECIPE_BOOK, RECIPE_BOOK_PAGES);
		fillResourceLocationList(STEVES_BOOK_PATH, FILE_TYPE, MAX_PAGES_STEVES_BOOK, STEVES_BOOK_PAGES);
	}
	
	/**
	 * 	Fills a ResourceLocation list with path to GUI textures.
	 */
	private static void fillResourceLocationList(String path, String fileType, int MAX_PAGES, List<ResourceLocation> list)
	{
		for(int i = 0; i < MAX_PAGES; i++)
			list.add(new ResourceLocation(WellFedMod.modid, path + i + fileType));
	}
	
	/**
	 * 	Methods for getting Book Pages.
	 */
	public static List<ResourceLocation> getRecipeBookPages()
	{
		return RECIPE_BOOK_PAGES;
	}
	
	public static List<ResourceLocation> getStevesBookPages()
	{
		return STEVES_BOOK_PAGES;
	}
}
