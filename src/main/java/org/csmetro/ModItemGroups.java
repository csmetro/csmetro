package org.csmetro;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.csmetro.block.ModBlocks;

public class ModItemGroups {

    // 创建你的专属物品栏
    public static final CreativeModeTab CSMETRO_GROUP = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation("csmetro", "main"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.APG_DOOR)) // 图标用你的 APG 门
                    .title(Component.translatable("itemGroup.csmetro.main")) // 标签页名称的翻译键
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModBlocks.APG_DOOR.asItem());
                        entries.accept(ModBlocks.APG_GLASS.asItem());
                        entries.accept(ModBlocks.APG_GLASS_END.asItem());
                        entries.accept(ModBlocks.PSD_DOOR_1.asItem());
                        entries.accept(ModBlocks.PSD_DOOR_2.asItem());
                        entries.accept(ModBlocks.PSD_GLASS_1.asItem());
                        entries.accept(ModBlocks.PSD_GLASS_2.asItem());
                        entries.accept(ModBlocks.PSD_GLASS_END_1.asItem());
                        entries.accept(ModBlocks.PSD_GLASS_END_2.asItem());
                        entries.accept(ModBlocks.PSD_TOP.asItem());
                    })
                    .build()
    );

    public static void initialize() {

    }
}