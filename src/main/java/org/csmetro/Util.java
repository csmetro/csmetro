package org.csmetro;

import mtr.block.BlockPSDTop;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.csmetro.block.ModBlocks;

public class Util {
    public static void generateTop(Level world, BlockPos pos) {
        for (int i = 0; i < 2; i++) {
            BlockPos topPos = pos.above(2 + i);
            if (world.getBlockState(topPos).isAir()) {
                BlockState defaultTop = ModBlocks.PSD_TOP.defaultBlockState()
                        .setValue(BlockPSDTop.HALF, i == 0 ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER);
                world.setBlock(topPos, defaultTop, 3);

                BlockState actualTop = BlockPSDTop.getActualState(world, topPos);
                world.setBlock(topPos, actualTop, 3);
            }
        }
    }

    public interface HasItem {
        void setItem(Item item);
    }
}
