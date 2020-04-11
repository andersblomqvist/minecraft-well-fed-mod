package com.branders.wellfedmod.lists;

import com.branders.wellfedmod.container.MortarAndPestleContainer;

import net.minecraft.inventory.container.ContainerType;

public class ContainerTypeList 
{
	public static ContainerType<MortarAndPestleContainer> mortar_and_pestle = 
			new ContainerType<MortarAndPestleContainer>(MortarAndPestleContainer::new);
}
