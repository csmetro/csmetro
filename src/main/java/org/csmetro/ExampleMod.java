package org.csmetro;

import net.fabricmc.api.ModInitializer;

import org.csmetro.block.ModBlockEntities;
import org.csmetro.block.ModBlocks;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		ModBlocks.register();        // 1. 注册方块
		ModBlockEntities.register(); // 2. 注册实体
	}
}