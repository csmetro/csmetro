package org.csmetro.block;

import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import org.csmetro.Util;
import org.jetbrains.annotations.NotNull;

public class MyPSDTop extends mtr.block.BlockPSDTop implements Util.HasItem {
    private Item item;
    @Override public void setItem(Item item) { this.item = item; }
    @Override public @NotNull Item asItem() { return item; }
    @Override
    public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
        return new mtr.block.BlockPSDTop.TileEntityPSDTop(pos, state);
    }
}