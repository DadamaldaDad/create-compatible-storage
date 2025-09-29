package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import com.simibubi.create.content.contraptions.Contraption;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.sounds.MountedStorageSound;
import net.dadamalda.create_compatible_storage.sounds.MountedStorageSoundHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class SoundUncooperativeMountedStorage extends SimpleMountedStorage {
    public static final Codec<SoundUncooperativeMountedStorage> CODEC = SimpleMountedStorage.codec(SoundUncooperativeMountedStorage::new);

    private MountedStorageSound sound;

    protected SoundUncooperativeMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public SoundUncooperativeMountedStorage(IItemHandler handler) {
        super(CCSMountedStorageTypes.SOUND_UNCOOPERATIVE.get(), handler);
    }

    @Override
    public boolean handleInteraction(ServerPlayer player, Contraption contraption, StructureTemplate.StructureBlockInfo info) {
        BlockState state = info.state();

        sound = MountedStorageSoundHandler.getSoundFromState(state);

        return super.handleInteraction(player, contraption, info);
    }

    @Override
    protected void playOpeningSound(ServerLevel level, Vec3 pos) {
        MountedStorageSoundHandler.playOpeningSound(sound, level, pos);
    }

    @Override
    protected void playClosingSound(ServerLevel level, Vec3 pos) {
        MountedStorageSoundHandler.playClosingSound(sound, level, pos);
    }

    @Override
    public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        if(be == null) return;
        for (int i = 0; i < wrapped.getSlots(); i++) {
            ((BaseContainerBlockEntity) be).setItem(i, wrapped.getStackInSlot(i));
            be.setChanged();
        }
    }
}
