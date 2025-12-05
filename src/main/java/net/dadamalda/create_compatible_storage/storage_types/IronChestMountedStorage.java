package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.menus.IronChestMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class IronChestMountedStorage extends SimpleMountedStorage {
    public static final Codec<IronChestMountedStorage> CODEC = SimpleMountedStorage.codec(IronChestMountedStorage::new);

    protected IronChestMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public IronChestMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.IRON_CHEST.get(), handler);
    }

    @Override
    protected @Nullable MenuProvider createMenuProvider(Component name, IItemHandlerModifiable handler, Predicate<Player> stillValid, Consumer<Player> onClose) {
        return IronChestMenuProvider.create(name, handler, stillValid, onClose);
    }
}
