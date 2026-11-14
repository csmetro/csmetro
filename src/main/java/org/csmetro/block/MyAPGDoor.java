package org.csmetro.block;

import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.csmetro.Util;
import org.csmetro.block.entity.MyAPGDoorEntity;
import org.jetbrains.annotations.NotNull;

public class MyAPGDoor extends mtr.block.BlockAPGDoor implements Util.HasItem {
    private Item item;
    @Override public void setItem(Item item) { this.item = item; }
    @Override public @NotNull Item asItem() { return item; }

    @Override
    public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
        return new MyAPGDoorEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.isClientSide) return;

        Util.generateTop(world, pos);
    }
}