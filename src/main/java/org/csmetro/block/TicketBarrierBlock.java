package org.csmetro.block;

import mtr.SoundEvents;
import mtr.block.IBlock;
import mtr.mappings.BlockDirectionalMapper;
import mtr.mappings.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.csmetro.data.TicketBarrierOpen;
import org.csmetro.data.TicketSystem;
import org.jetbrains.annotations.NotNull;

public class TicketBarrierBlock extends BlockDirectionalMapper {
    private final boolean isEntrance;
    public static final EnumProperty<TicketBarrierOpen> OPEN = EnumProperty.create("open", TicketBarrierOpen.class);

    public TicketBarrierBlock(boolean isEntrance) {
        super(Properties.of().requiresCorrectToolForDrops().strength(2).lightLevel(state -> 5).noOcclusion());
        this.isEntrance = isEntrance;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!world.isClientSide && entity instanceof Player) {
            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            final Vec3 playerPosRotated = entity.position().subtract(pos.getX() + 0.5, 0, pos.getZ() + 0.5).yRot((float) Math.toRadians(facing.toYRot()));
            final TicketBarrierOpen open = IBlock.getStatePropertySafe(state, OPEN);

            if (open.isOpen() && playerPosRotated.z > 0) {
                world.setBlockAndUpdate(pos, state.setValue(OPEN, TicketBarrierOpen.CLOSED));
            } else if (!open.isOpen() && playerPosRotated.z < 0) {
                final TicketBarrierOpen newOpen = TicketSystem.passThrough(world, pos, (Player) entity, isEntrance, !isEntrance, SoundEvents.TICKET_BARRIER, SoundEvents.TICKET_BARRIER_CONCESSIONARY, SoundEvents.TICKET_BARRIER, SoundEvents.TICKET_BARRIER_CONCESSIONARY, null, false);
                world.setBlockAndUpdate(pos, state.setValue(OPEN, newOpen));
                if (newOpen != TicketBarrierOpen.CLOSED && !world.getBlockTicks().hasScheduledTick(pos, this)) {
                    Utilities.scheduleBlockTick(world, pos, this, 40);
                }
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos) {
        world.setBlockAndUpdate(pos, state.setValue(OPEN, TicketBarrierOpen.CLOSED));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(OPEN, TicketBarrierOpen.CLOSED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(12, 0, 0, 16, 15, 16, facing);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        final TicketBarrierOpen open = IBlock.getStatePropertySafe(state, OPEN);
        final VoxelShape base = IBlock.getVoxelShapeByDirection(15, 0, 0, 16, 24, 16, facing);
        return open.isOpen() ? base : Shapes.or(IBlock.getVoxelShapeByDirection(0, 0, 7, 16, 24, 9, facing), base);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }
}