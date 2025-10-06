package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

@Deprecated
public class SoundUncooperativeMountedStorageType extends SimpleMountedStorageType<SoundUncooperativeMountedStorage> {

    public SoundUncooperativeMountedStorageType() {
        super(SoundUncooperativeMountedStorage.CODEC);
    }

    @Override
    protected SoundUncooperativeMountedStorage createStorage(IItemHandler handler) {
        return new SoundUncooperativeMountedStorage(handler);
    }

    @Override
    protected IItemHandler getHandler(BlockEntity be) {
        assert be instanceof BaseContainerBlockEntity;
        if(be instanceof RandomizableContainerBlockEntity) {
            ((RandomizableContainerBlockEntity) be).unpackLootTable(null);
        }
        return new InvWrapper((BaseContainerBlockEntity) be);
    }
}
