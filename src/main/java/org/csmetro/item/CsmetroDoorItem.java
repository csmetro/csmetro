package org.csmetro.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.NotNull;

public class CsmetroDoorItem extends BlockItem {

    public CsmetroDoorItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPlaceContext placeContext = new BlockPlaceContext(context);

        // 1. 获取方块的放置状态（这里会调用 MTR 的 getStateForPlacement，包含上下两格的判断）
        BlockState placementState = this.getBlock().getStateForPlacement(placeContext);

        if (placementState == null) {
            return InteractionResult.FAIL;
        }

        // 2. 把方块放进世界
        if (placeContext.getLevel().setBlock(placeContext.getClickedPos(), placementState, 11)) {

            // 3. 关键！手动调用 setPlacedBy，让 MTR 的源码去生成上半格！
            this.getBlock().setPlacedBy(placeContext.getLevel(), placeContext.getClickedPos(), placementState, context.getPlayer(), context.getItemInHand());

            // 4. 消耗物品（非创造模式）
            if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                context.getItemInHand().shrink(1);
            }

            return InteractionResult.sidedSuccess(placeContext.getLevel().isClientSide);
        }

        return InteractionResult.FAIL;
    }
}