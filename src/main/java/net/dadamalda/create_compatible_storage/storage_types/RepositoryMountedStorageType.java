package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RepositoryMountedStorageType extends MountedItemStorageType<RepositoryMountedStorage> {

    public RepositoryMountedStorageType() { super(RepositoryMountedStorage.CODEC); }

    @Override
    public @Nullable RepositoryMountedStorage mount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        return RepositoryMountedStorage.fromRepository(be);
    }
}
