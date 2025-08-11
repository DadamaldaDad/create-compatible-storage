package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public class UncooperativeMountedStorageType extends SimpleMountedStorageType<UncooperativeMountedStorage> {

    public UncooperativeMountedStorageType() {
        super(UncooperativeMountedStorage.CODEC);
    }

    @Override
    protected UncooperativeMountedStorage createStorage(IItemHandler handler) {
        return new UncooperativeMountedStorage(handler);
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
