package net.dadamalda.create_compatible_storage.storage_types;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CrystalChestMountedStorageType extends MountedItemStorageType<CrystalChestMountedStorage> {

    public CrystalChestMountedStorageType() {
        super(CrystalChestMountedStorage.CODEC);
    }

    @Override
    public @Nullable CrystalChestMountedStorage mount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        return CrystalChestMountedStorage.fromCrystalChest(be);
    }
}
