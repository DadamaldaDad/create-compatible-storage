package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ClosetMountedStorageType extends SimpleMountedStorageType<ClosetMountedStorage> {

    public ClosetMountedStorageType() {
        super(ClosetMountedStorage.CODEC);
    }

    @Override
    protected IItemHandler getHandler(BlockEntity be) {
        return be instanceof Container container ? new InvWrapper(container) : null;
    }

    @Override
    protected ClosetMountedStorage createStorage(IItemHandler handler) {
        return new ClosetMountedStorage(handler);
    }
}
