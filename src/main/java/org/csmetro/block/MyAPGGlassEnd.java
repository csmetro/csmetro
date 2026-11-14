package org.csmetro.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.csmetro.Util;
import org.jetbrains.annotations.NotNull;

public  class MyAPGGlassEnd extends mtr.block.BlockAPGGlassEnd implements Util.HasItem {
    private Item item;
    @Override public void setItem(Item item) { this.item = item; }
    @Override public @NotNull Item asItem() { return item; }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.isClientSide) return;
        Util.generateTop(world, pos);
    }
}