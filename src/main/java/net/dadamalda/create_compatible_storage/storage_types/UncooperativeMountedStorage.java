package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class UncooperativeMountedStorage extends SimpleMountedStorage {
    public static final MapCodec<UncooperativeMountedStorage> CODEC = SimpleMountedStorage.codec(UncooperativeMountedStorage::new);

    protected UncooperativeMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public UncooperativeMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.UNCOOPERATIVE.get(), handler);
    }

    @Override
    public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        if(be == null) return;
        for (int i = 0; i < wrapped.getSlots(); i++) {
            ((BaseContainerBlockEntity) be).setItem(i, wrapped.getStackInSlot(i));
            be.setChanged();
        }
    }
}
