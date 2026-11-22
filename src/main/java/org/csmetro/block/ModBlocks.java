package org.csmetro.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.csmetro.Util;

public class ModBlocks {
    public static final Block APG_DOOR = register("apg_door", new APGDoorBlock());
    public static final Block APG_GLASS = register("apg_glass", new APGGlassBlock());
    public static final Block APG_GLASS_END = register("apg_glass_end", new APGGlassEndBlock());
    public static final Block PSD_DOOR_1 = register("psd_door_1", new MyPSDDoor(0));
    public static final Block PSD_DOOR_2 = register("psd_door_2", new MyPSDDoor(1));
    public static final Block PSD_GLASS_1 = register("psd_glass_1", new PSDGlassBlock(0));
    public static final Block PSD_GLASS_2 = register("psd_glass_2", new PSDGlassBlock(1));
    public static final Block PSD_GLASS_END_1 = register("psd_glass_end_1", new PSDGlassEndBlock(0));
    public static final Block PSD_GLASS_END_2 = register("psd_glass_end_2", new PSDGlassEndBlock(1));
    public static final Block PSD_TOP = register("psd_top", new PSDTopBlock());
    public static final Block TICKET_BARRIER_ENTRANCE = register("ticket_barrier_entrance", new TicketBarrierEntranceBlock());
    public static final Block TICKET_BARRIER_EXIT = register("ticket_barrier_exit", new TicketBarrierExitBlock());

    private static Block register(String name, Block block) {
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