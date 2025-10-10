package net.dadamalda.create_compatible_storage.storage_types;

import com.hollingsworth.arsnouveau.common.block.tile.RepositoryTile;
import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.SyncedMountedStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.WrapperMountedItemStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.utility.CreateCodecs;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class RepositoryMountedStorage extends WrapperMountedItemStorage<RepositoryMountedStorage.Handler> implements SyncedMountedStorage {
    public static final Codec<RepositoryMountedStorage> CODEC = CreateCodecs.ITEM_STACK_HANDLER.xmap(
            RepositoryMountedStorage::new, storage -> storage.wrapped
    );

    private boolean dirty;

    protected RepositoryMountedStorage(MountedItemStorageType<?> type, IItemHandler wrapped) {
        super(type, new Handler(wrapped));
        this.wrapped.onChange = () -> this.dirty = true;
    }

    public RepositoryMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.REPOSITORY.get(), handler);
    }

    @Override
    public boolean isDirty() {
        return this.dirty;
    }

    @Override
    public void markClean() {
        this.dirty = false;
    }

    @Override
    public void afterSync(Contraption contraption, BlockPos localPos) {
        BlockEntity be = contraption.presentBlockEntities.get(localPos);
        if(be instanceof RepositoryTile repository) {
            for(int i = 0; i < 54; i++) {
                repository.setItem(i, this.wrapped.getStackInSlot(i));
            }
        }
    }

    @Override
    public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        if(be instanceof RepositoryTile repository) {
            for(int i = 0; i < 54; i++) {
                repository.setItem(i, this.wrapped.getStackInSlot(i));
            }
        }
    }

    public static @Nullable RepositoryMountedStorage fromRepository(BlockEntity be) {
        if(be instanceof RepositoryTile repository) {
            IItemHandler handler = be.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
            // make sure the handler is modifiable so new contents can be moved over on disassembly
            return handler instanceof IItemHandlerModifiable modifiable ? new RepositoryMountedStorage(modifiable) : null;
        }
        return null;
    }

    public static final class Handler extends ItemStackHandler {
        private Runnable onChange = () -> {};

        private Handler(IItemHandler handler) {
            super(54);
            for(int i = 0; i < 54; i++) {
                this.setStackInSlot(i, handler.getStackInSlot(i));
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            this.onChange.run();
        }
    }
}
