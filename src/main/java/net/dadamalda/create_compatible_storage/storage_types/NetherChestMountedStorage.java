package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.menus.IronChestMenuProvider;
import net.dadamalda.create_compatible_storage.menus.NetherChestMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class NetherChestMountedStorage extends SimpleMountedStorage {
    public static final Codec<NetherChestMountedStorage> CODEC = SimpleMountedStorage.codec(NetherChestMountedStorage::new);

    protected NetherChestMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public NetherChestMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.NETHER_CHEST.get(), handler);
    }

    @Override
    protected @Nullable MenuProvider createMenuProvider(Component name, IItemHandlerModifiable handler, Predicate<Player> stillValid, Consumer<Player> onClose) {
        return NetherChestMenuProvider.create(name, handler, stillValid, onClose);
    }
}
