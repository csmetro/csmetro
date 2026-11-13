package org.csmetro.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import org.csmetro.block.ModBlockEntities;
import org.csmetro.block.ModBlocks;
import mtr.render.RenderPSDTop;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// 门：用你自己的 CustomPSDRenderer
		BlockEntityRenderers.register(ModBlockEntities.APG_DOOR, ctx ->
				new CustomPSDRenderer<>(ctx.getBlockEntityRenderDispatcher(), 2)
		);
		BlockEntityRenderers.register(ModBlockEntities.PSD_DOOR, ctx ->
				new CustomPSDRenderer<>(ctx.getBlockEntityRenderDispatcher(), 0)
		);

		// 顶盖：用 MTR 自带的渲染器（因为它不是门，是另一种实体）
		BlockEntityRenderers.register(ModBlockEntities.PSD_TOP, ctx ->
				new RenderPSDTop(ctx.getBlockEntityRenderDispatcher())
		);

		// 渲染层
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APG_DOOR, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_DOOR_1, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_DOOR_2, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_TOP, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APG_GLASS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APG_GLASS_END, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_GLASS_1, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_GLASS_2, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_GLASS_END_1, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PSD_GLASS_END_2, RenderType.cutout());
	}
}