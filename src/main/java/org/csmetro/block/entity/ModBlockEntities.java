package org.csmetro.block.entity;

import mtr.block.BlockPSDTop;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.csmetro.block.ModBlocks;
import org.csmetro.util.Util;

public class ModBlockEntities {

    public static final BlockEntityType<MyAPGDoorEntity> APG_DOOR = register("apg_door", BlockEntityType.Builder.of(MyAPGDoorEntity::new, ModBlocks.APG_DOOR));
    public static final BlockEntityType<MyPSDDoorEntity> PSD_DOOR = register("psd_door", BlockEntityType.Builder.of(MyPSDDoorEntity::new, ModBlocks.PSD_DOOR_1, ModBlocks.PSD_DOOR_2));
    public static final BlockEntityType<BlockPSDTop.TileEntityPSDTop> PSD_TOP = register("psd_top", BlockEntityType.Builder.of(mtr.block.BlockPSDTop.TileEntityPSDTop::new, ModBlocks.PSD_TOP));

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Util.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, name, blockEntityType);
        return blockEntityType;
    }

    public static void initialize() {}
}