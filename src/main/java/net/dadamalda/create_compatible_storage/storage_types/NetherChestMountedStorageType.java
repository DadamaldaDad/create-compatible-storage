package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraftforge.items.IItemHandler;

public class NetherChestMountedStorageType extends SimpleMountedStorageType<NetherChestMountedStorage> {

    public NetherChestMountedStorageType() {
        super(NetherChestMountedStorage.CODEC);
    }

    @Override
    protected NetherChestMountedStorage createStorage(IItemHandler handler) {
        return new NetherChestMountedStorage(handler);
    }
}
