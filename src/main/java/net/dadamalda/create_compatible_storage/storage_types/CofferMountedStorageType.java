package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraftforge.items.IItemHandler;

public class CofferMountedStorageType extends SimpleMountedStorageType<CofferMountedStorage> {

    public CofferMountedStorageType() {
        super(CofferMountedStorage.CODEC);
    }

    @Override
    protected CofferMountedStorage createStorage(IItemHandler handler) {
        return new CofferMountedStorage(handler);
    }
}
