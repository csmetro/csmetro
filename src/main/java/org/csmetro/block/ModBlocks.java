package org.csmetro.block;

import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

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

    // 所有方块类实现这个接口，用来接收物品实例
    public interface HasItem {
        void setItem(Item item);
    }

    public static class MyAPGDoor extends mtr.block.BlockAPGDoor implements HasItem {
        private Item item;
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockAPGDoor.TileEntityAPGDoor(pos, state);
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
        @Override public void setItem(Item item) { this.item = item; }
        @Override public Item asItem() { return item; }
        @Override
        public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
            return new mtr.block.BlockPSDDoor.TileEntityPSDDoor(0, pos, state);
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

    public static void register() {
        // 注册已经在 static final 里完成了，这里留空即可
    }
}