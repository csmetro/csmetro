package org.csmetro.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<mtr.block.BlockAPGDoor.TileEntityAPGDoor> APG_DOOR =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation("csmetro", "apg_door"),
                    BlockEntityType.Builder.of(mtr.block.BlockAPGDoor.TileEntityAPGDoor::new, ModBlocks.APG_DOOR).build(null));

    public static final BlockEntityType<mtr.block.BlockPSDDoor.TileEntityPSDDoor> PSD_DOOR =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation("csmetro", "psd_door"),
                    BlockEntityType.Builder.of((pos, state) -> new mtr.block.BlockPSDDoor.TileEntityPSDDoor(0, pos, state),
                            ModBlocks.PSD_DOOR_1, ModBlocks.PSD_DOOR_2).build(null));

    public static final BlockEntityType<mtr.block.BlockPSDTop.TileEntityPSDTop> PSD_TOP =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation("csmetro", "psd_top"),
                    BlockEntityType.Builder.of(mtr.block.BlockPSDTop.TileEntityPSDTop::new, ModBlocks.PSD_TOP).build(null));

    public static void register() {}
}