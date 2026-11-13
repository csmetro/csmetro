package org.csmetro.block;

import mtr.block.IBlock;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.csmetro.item.CsmetroDoorItem;

public class ModBlocks {

    public static final Block APG_DOOR = new MyAPGDoor();
    public static final Block APG_GLASS = new mtr.block.BlockAPGGlass();
    public static final Block APG_GLASS_END = new mtr.block.BlockAPGGlassEnd();
    public static final Block PSD_DOOR_1 = new MyPSDDoor(0);
    public static final Block PSD_DOOR_2 = new MyPSDDoor(1);
    public static final Block PSD_GLASS_1 = new mtr.block.BlockPSDGlass(0);
    public static final Block PSD_GLASS_2 = new mtr.block.BlockPSDGlass(1);
    public static final Block PSD_GLASS_END_1 = new mtr.block.BlockPSDGlassEnd(0);
    public static final Block PSD_GLASS_END_2 = new mtr.block.BlockPSDGlassEnd(1);
    public static final Block PSD_TOP = new MyPSDTop();

    public static void register() {
        registerBlock("apg_door", APG_DOOR);
        registerBlock("apg_glass", APG_GLASS);
        registerBlock("apg_glass_end", APG_GLASS_END);
        registerBlock("psd_door_1", PSD_DOOR_1);
        registerBlock("psd_door_2", PSD_DOOR_2);
        registerBlock("psd_glass_1", PSD_GLASS_1);
        registerBlock("psd_glass_2", PSD_GLASS_2);
        registerBlock("psd_glass_end_1", PSD_GLASS_END_1);
        registerBlock("psd_glass_end_2", PSD_GLASS_END_2);
        registerBlock("psd_top", PSD_TOP);
    }

    private static void registerBlock(String name, Block block) {
        // 注册方块
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("csmetro", name), block);

        // 为门方块注册自定义物品，其他方块仍用 BlockItem
        if (block instanceof mtr.block.BlockPSDAPGDoorBase) {
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("csmetro", name),
                    new CsmetroDoorItem(block, new Item.Properties()));
        } else {
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("csmetro", name),
                    new BlockItem(block, new Item.Properties()));
        }
    }

    // 注意：这里的 createBlockEntity 必须直接返回 MTR 的实体，千万不要返回自定义的
    public static class MyAPGDoor extends mtr.block.BlockAPGDoor {
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockAPGDoor.TileEntityAPGDoor(pos, state);
        }
        @Override
        public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
            super.setPlacedBy(world, pos, state, placer, stack);

            if (world.isClientSide) return;

            // 获取门朝向
            Direction facing = state.getValue(FACING);
            // 获取侧面方向（左边或右边）
            Direction sideDir = IBlock.getSideDirection(state);
            BlockPos sidePos = pos.relative(sideDir);

            // 检查旁边是不是空气，避免覆盖已有方块
            if (!world.getBlockState(sidePos).isAir()) return;
            if (!world.getBlockState(sidePos.above()).isAir()) return;

            // 在侧面生成一扇相同朝向、SIDE 相反的门
            BlockState sideState = state
                    .setValue(SIDE, state.getValue(SIDE) == EnumSide.LEFT ? EnumSide.RIGHT : EnumSide.LEFT)
                    .setValue(HALF, DoubleBlockHalf.LOWER);
            BlockState sideUpper = sideState.setValue(HALF, DoubleBlockHalf.UPPER);

            world.setBlockAndUpdate(sidePos, sideState);
            world.setBlockAndUpdate(sidePos.above(), sideUpper);
        }
    }

    public static class MyPSDDoor extends mtr.block.BlockPSDDoor {
        public MyPSDDoor(int style) { super(style); }
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockPSDDoor.TileEntityPSDDoor(0, pos, state);
        }
    }

    public static class MyPSDTop extends mtr.block.BlockPSDTop {
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockPSDTop.TileEntityPSDTop(pos, state);
        }
    }
}