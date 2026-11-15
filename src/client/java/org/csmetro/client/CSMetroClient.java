package org.csmetro.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import org.csmetro.block.entity.ModBlockEntities;
import org.csmetro.block.ModBlocks;
import mtr.render.RenderPSDTop;

public class CSMetroClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRenderers.register(ModBlockEntities.APG_DOOR, ctx -> new CustomPSDRenderer<>(ctx.getBlockEntityRenderDispatcher(), 2));
		BlockEntityRenderers.register(ModBlockEntities.PSD_DOOR, ctx -> new CustomPSDRenderer<>(ctx.getBlockEntityRenderDispatcher(), 0));
		BlockEntityRenderers.register(ModBlockEntities.PSD_TOP, ctx -> new RenderPSDTop(ctx.getBlockEntityRenderDispatcher()));

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
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TICKET_BARRIER_ENTRANCE, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TICKET_BARRIER_EXIT, RenderType.cutout());
	}
}