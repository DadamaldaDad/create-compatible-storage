package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class FurnitureRefurbishedMountedStorageType extends SimpleMountedStorageType<FurnitureRefurbishedMountedStorage> {

    public FurnitureRefurbishedMountedStorageType() {
        super(FurnitureRefurbishedMountedStorage.CODEC);
    }

    @Override
    protected FurnitureRefurbishedMountedStorage createStorage(IItemHandler handler) {
        return new FurnitureRefurbishedMountedStorage(handler);
    }
}
