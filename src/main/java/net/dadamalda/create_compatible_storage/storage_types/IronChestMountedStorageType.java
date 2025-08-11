package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.neoforged.neoforge.items.IItemHandler;

public class IronChestMountedStorageType extends SimpleMountedStorageType<IronChestMountedStorage> {

    public IronChestMountedStorageType() {
        super(IronChestMountedStorage.CODEC);
    }

    @Override
    protected IronChestMountedStorage createStorage(IItemHandler handler) {
        return new IronChestMountedStorage(handler);
    }
}
