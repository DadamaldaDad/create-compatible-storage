package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraftforge.items.IItemHandler;

public class IronShulkerBoxMountedStorageType extends SimpleMountedStorageType<IronShulkerBoxMountedStorage> {

    public IronShulkerBoxMountedStorageType() {
        super(IronShulkerBoxMountedStorage.CODEC);
    }

    @Override
    protected IronShulkerBoxMountedStorage createStorage(IItemHandler handler) {
        return new IronShulkerBoxMountedStorage(handler);
    }
}
