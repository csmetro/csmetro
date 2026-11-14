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
import org.csmetro.block.entity.MyAPGDoorEntity;
import org.csmetro.block.entity.MyPSDDoorEntity;

public class ModBlocks {
    public static final Block APG_DOOR = register("apg_door", new MyAPGDoor());
    public static final Block APG_GLASS = register("apg_glass", new MyAPGGlass());
    public static final Block APG_GLASS_END = register("apg_glass_end", new MyAPGGlassEnd());
    public static final Block PSD_DOOR_1 = register("psd_door_1", new MyPSDDoor(0));
    public static final Block PSD_DOOR_2 = register("psd_door_2", new MyPSDDoor(1));
    public static final Block PSD_GLASS_1 = register("psd_glass_1", new MyPSDGlass(0));
    public static final Block PSD_GLASS_2 = register("psd_glass_2", new MyPSDGlass(1));
    public static final Block PSD_GLASS_END_1 = register("psd_glass_end_1", new MyPSDGlassEnd(0));
    public static final Block PSD_GLASS_END_2 = register("psd_glass_end_2", new MyPSDGlassEnd(1));
    public static final Block PSD_TOP = register("psd_top", new MyPSDTop());

    private static Block register(String name, Block block) {
        ResourceLocation id = new ResourceLocation("csmetro", name);
        BlockItem item = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, item);
        if (block instanceof HasItem hasItem) {
            hasItem.setItem(item);
        }
        return block;
    }

    public interface HasItem {
        void setItem(Item item);
    }

    public static class MyAPGDoor extends mtr.block.BlockAPGDoor implements HasItem {
        private Item item;
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }

        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            // 返回你自己的实体！
            return new MyAPGDoorEntity(pos, state);
        }

        @Override
        public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
            super.setPlacedBy(world, pos, state, placer, stack);
            if (world.isClientSide) return;

            Direction sideDir = IBlock.getSideDirection(state);
            BlockPos sidePos = pos.relative(sideDir);

            if (world.getBlockState(sidePos).isAir()) {
                BlockState rightLower = state
                        .setValue(SIDE, EnumSide.RIGHT)
                        .setValue(HALF, DoubleBlockHalf.LOWER);

                world.setBlock(sidePos, rightLower, 3);
                world.setBlock(sidePos.above(), rightLower.setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }
        }
    }

    public static class MyAPGGlass extends mtr.block.BlockAPGGlass implements HasItem {
        private Item item;
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
    }

    public static class MyAPGGlassEnd extends mtr.block.BlockAPGGlassEnd implements HasItem {
        private Item item;
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
    }

    public static class MyPSDDoor extends mtr.block.BlockPSDDoor implements HasItem {
        private Item item;
        public MyPSDDoor(int style) { super(style); }
        @Override public void setItem(Item item) {
            this.item = item;
        }
        @Override public Item asItem() {
            return item;
        }

        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new MyPSDDoorEntity(pos, state);
        }

        @Override
        public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
            super.setPlacedBy(world, pos, state, placer, stack);
            if (world.isClientSide) return;

            Direction sideDir = IBlock.getSideDirection(state);
            BlockPos sidePos = pos.relative(sideDir);

            if (world.getBlockState(sidePos).isAir()) {
                BlockState rightLower = state
                        .setValue(SIDE, EnumSide.RIGHT)
                        .setValue(HALF, DoubleBlockHalf.LOWER);

                world.setBlock(sidePos, rightLower, 3);
                world.setBlock(sidePos.above(), rightLower.setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }
        }
    }

    public static class MyPSDGlass extends mtr.block.BlockPSDGlass implements HasItem {
        private Item item;
        public MyPSDGlass(int style) { super(style); }
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
    }

    public static class MyPSDGlassEnd extends mtr.block.BlockPSDGlassEnd implements HasItem {
        private Item item;
        public MyPSDGlassEnd(int style) { super(style); }
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
    }

    public static class MyPSDTop extends mtr.block.BlockPSDTop implements HasItem {
        private Item item;
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockPSDTop.TileEntityPSDTop(pos, state);
        }
    }

    public static void register() {}
}