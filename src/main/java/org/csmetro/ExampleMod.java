package org.csmetro;

import net.fabricmc.api.ModInitializer;

import org.csmetro.block.entity.ModBlockEntities;
import org.csmetro.block.ModBlocks;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModBlockEntities.register();
		ModItemGroups.register();
	}
}