package org.csmetro.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.csmetro.CSMetro;
import org.csmetro.util.Util;

public class ModBlocks {
    public static final Block APG_DOOR = initialize("apg_door", new MyAPGDoor());
    public static final Block APG_GLASS = initialize("apg_glass", new MyAPGGlass());
    public static final Block APG_GLASS_END = initialize("apg_glass_end", new MyAPGGlassEnd());
    public static final Block PSD_DOOR_1 = initialize("psd_door_1", new MyPSDDoor(0));
    public static final Block PSD_DOOR_2 = initialize("psd_door_2", new MyPSDDoor(1));
    public static final Block PSD_GLASS_1 = initialize("psd_glass_1", new MyPSDGlass(0));
    public static final Block PSD_GLASS_2 = initialize("psd_glass_2", new MyPSDGlass(1));
    public static final Block PSD_GLASS_END_1 = initialize("psd_glass_end_1", new MyPSDGlassEnd(0));
    public static final Block PSD_GLASS_END_2 = initialize("psd_glass_end_2", new MyPSDGlassEnd(1));
    public static final Block PSD_TOP = initialize("psd_top", new MyPSDTop());

    private static Block initialize(String name, Block block) {
        BlockItem item = new BlockItem(block, new Item.Properties());
        Util.register(BuiltInRegistries.BLOCK, name, block);
        Util.register(BuiltInRegistries.ITEM, name, item);
        if (block instanceof Util.HasItem h) {
            h.setItem(item);
        }
        return block;
    }


    public static void initialize() {}
}