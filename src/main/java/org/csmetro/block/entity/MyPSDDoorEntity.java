package org.csmetro.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MyPSDDoorEntity extends mtr.block.BlockPSDAPGDoorBase.TileEntityPSDAPGDoorBase {
    public MyPSDDoorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PSD_DOOR, pos, state);
    }
}