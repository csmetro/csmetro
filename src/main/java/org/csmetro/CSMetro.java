package org.csmetro;

import net.fabricmc.api.ModInitializer;

import org.csmetro.block.entity.ModBlockEntities;
import org.csmetro.block.ModBlocks;

public class CSMetro implements ModInitializer {
	public static final String MOD_ID = "csmetro";

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModItemGroups.initialize();
	}
}