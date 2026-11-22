package org.csmetro.block.entity;

import mtr.block.BlockPSDAPGDoorBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class APGDoorEntity extends BlockPSDAPGDoorBase.TileEntityPSDAPGDoorBase {
    public APGDoorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.APG_DOOR, pos, state);
    }
}