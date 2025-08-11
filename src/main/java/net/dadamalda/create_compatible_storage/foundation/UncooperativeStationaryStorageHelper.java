package net.dadamalda.create_compatible_storage.foundation;

import com.mojang.logging.LogUtils;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class UncooperativeStationaryStorageHelper {
    public static @Nullable IItemHandler wrap(BlockEntity be) {
        if (!(be instanceof BaseContainerBlockEntity container)) return null;
        InvWrapper invWrapper = new InvWrapper(container);
        if(ModList.get().isLoaded("woodworks") && be instanceof ClosetBlockEntity) {
            BlockState state = be.getBlockState();
            ChestType type = state.getValue(ChestBlock.TYPE);
            if(type == ChestType.SINGLE) {
                return invWrapper;
            } else {
                Direction connectedDirection = type == ChestType.LEFT ? Direction.DOWN : Direction.UP;
                BlockPos pos = be.getBlockPos();
                BlockPos otherHalfPos = pos.relative(connectedDirection);
                Level world = be.getLevel();
                if (world == null) return invWrapper;
                BlockEntity otherHalf = world.getBlockEntity(otherHalfPos);

                if (!(otherHalf instanceof ClosetBlockEntity otherContainer)) return invWrapper;
                InvWrapper otherInvWrapper = new InvWrapper(otherContainer);

                // TODO: Change tp ChestType.LEFT once the bug gets fixed in Woodworks
                if (type == ChestType.RIGHT) {
                    return new CombinedInvWrapper(invWrapper, otherInvWrapper);
                } else {
                    return new CombinedInvWrapper(otherInvWrapper, invWrapper);
                }
            }
        } else if(be instanceof ChestBlockEntity) {
            BlockState state = be.getBlockState();
            ChestType type = state.getValue(ChestBlock.TYPE);
            if(type == ChestType.SINGLE) {
                return invWrapper;
            } else {
                Direction connectedDirection = ChestBlock.getConnectedDirection(state);
                BlockPos pos = be.getBlockPos();
                BlockPos otherHalfPos = pos.relative(connectedDirection);
                Level world = be.getLevel();
                if(world == null) return invWrapper;
                BlockEntity otherHalf = world.getBlockEntity(otherHalfPos);

                if(!(otherHalf instanceof ChestBlockEntity otherContainer)) return invWrapper;
                InvWrapper otherInvWrapper = new InvWrapper(otherContainer);

                if(type == ChestType.RIGHT) {
                    return new CombinedInvWrapper(invWrapper, otherInvWrapper);
                } else {
                    return new CombinedInvWrapper(otherInvWrapper, invWrapper);
                }
            }
        } else {
            return new InvWrapper(container);
        }
    }
}
