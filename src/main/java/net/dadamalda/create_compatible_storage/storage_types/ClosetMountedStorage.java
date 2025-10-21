package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.item.ItemHelper;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class ClosetMountedStorage extends SimpleMountedStorage {
    public static final Codec<ClosetMountedStorage> CODEC = SimpleMountedStorage.codec(ClosetMountedStorage::new);

    protected ClosetMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public ClosetMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.CLOSET.get(), handler);
    }

    @Override
    public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        // the capability will include both sides of chests, but mounted storage is 1:1
        if (be instanceof Container container && this.getSlots() == container.getContainerSize()) {
            ItemHelper.copyContents(this, new InvWrapper(container));
        }
    }

    @Override
    protected IItemHandlerModifiable getHandlerForMenu(StructureTemplate.StructureBlockInfo info, Contraption contraption) {
        BlockState state = info.state();
        ChestType type = state.getValue(ChestBlock.TYPE);
        if (type == ChestType.SINGLE)
            return this;

        Direction facing = state.getValue(ChestBlock.FACING);
        Direction connectedDirection = type == ChestType.LEFT ? Direction.DOWN : Direction.UP;
        BlockPos otherHalfPos = info.pos().relative(connectedDirection);

        MountedItemStorage otherHalf = this.getOtherHalf(contraption, otherHalfPos, state.getBlock(), facing, type);
        if (otherHalf == null)
            return this;

        if (type == ChestType.LEFT) {
            return new CombinedInvWrapper(this, otherHalf);
        } else {
            return new CombinedInvWrapper(otherHalf, this);
        }
    }

    @Nullable
    protected MountedItemStorage getOtherHalf(Contraption contraption, BlockPos localPos, Block block,
                                              Direction thisFacing, ChestType thisType) {
        StructureTemplate.StructureBlockInfo info = contraption.getBlocks().get(localPos);
        if (info == null)
            return null;
        BlockState state = info.state();
        if (!state.is(block))
            return null;

        Direction facing = state.getValue(ChestBlock.FACING);
        ChestType type = state.getValue(ChestBlock.TYPE);

        return facing == thisFacing && type == thisType.getOpposite()
                ? contraption.getStorage().getMountedItems().storages.get(localPos)
                : null;
    }
}
