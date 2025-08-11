package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.menus.IronShulkerBoxMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class IronShulkerBoxMountedStorage extends SimpleMountedStorage {
    public static final MapCodec<IronShulkerBoxMountedStorage> CODEC = SimpleMountedStorage.codec(IronShulkerBoxMountedStorage::new);

    protected IronShulkerBoxMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public IronShulkerBoxMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.IRON_SHULKER_BOX.get(), handler);
    }

    @Override
    protected @Nullable MenuProvider createMenuProvider(Component name, IItemHandlerModifiable handler, Predicate<Player> stillValid, Consumer<Player> onClose) {
        return IronShulkerBoxMenuProvider.create(name, handler, stillValid, onClose);
    }
}
