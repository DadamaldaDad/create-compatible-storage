package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.neoforged.neoforge.items.IItemHandler;

public class CrateMountedStorageType extends SimpleMountedStorageType<CrateMountedStorage> {

    public CrateMountedStorageType() {
        super(CrateMountedStorage.CODEC);
    }

    @Override
    protected CrateMountedStorage createStorage(IItemHandler handler) {
        return new CrateMountedStorage(handler);
    }
}
