package org.csmetro.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.csmetro.Util;

public class MyPSDGlass extends mtr.block.BlockPSDGlass implements Util.HasItem {
    private Item item;
    public MyPSDGlass(int style) { super(style); }
    @Override public void setItem(Item item) { this.item = item; }
    @Override public Item asItem() { return item; }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.isClientSide) return;
        Util.generateTop(world, pos);
    }
}