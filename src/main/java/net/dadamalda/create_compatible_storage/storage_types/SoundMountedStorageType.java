package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraftforge.items.IItemHandler;

public class SoundMountedStorageType extends SimpleMountedStorageType<SoundMountedStorage> {

    public SoundMountedStorageType() {
        super(SoundMountedStorage.CODEC);
    }

    @Override
    protected SoundMountedStorage createStorage(IItemHandler handler) {
        return new SoundMountedStorage(handler);
    }

}
