package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public class FurnitureRefurbishedMountedStorageType extends SimpleMountedStorageType<FurnitureRefurbishedMountedStorage> {

    public FurnitureRefurbishedMountedStorageType() {
        super(FurnitureRefurbishedMountedStorage.CODEC);
    }

    @Override
    protected FurnitureRefurbishedMountedStorage createStorage(IItemHandler handler) {
        return new FurnitureRefurbishedMountedStorage(handler);
    }

    @Override
    protected IItemHandler getHandler(Level level, BlockEntity be) {
        assert be instanceof BaseContainerBlockEntity;
        if(be instanceof RandomizableContainerBlockEntity) {
            ((RandomizableContainerBlockEntity) be).unpackLootTable(null);
        }
        return new InvWrapper((BaseContainerBlockEntity) be);
    }
}
