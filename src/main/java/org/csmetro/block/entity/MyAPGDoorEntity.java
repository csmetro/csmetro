package org.csmetro.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MyAPGDoorEntity extends mtr.block.BlockPSDAPGDoorBase.TileEntityPSDAPGDoorBase {

    public MyAPGDoorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.APG_DOOR, pos, state);
    }
}