package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.menus.CrateMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class CrateMountedStorage extends SimpleMountedStorage {
    public static final Codec<CrateMountedStorage> CODEC = SimpleMountedStorage.codec(CrateMountedStorage::new);

    protected CrateMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public CrateMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.CRATE.get(), handler);
    }

    @Override
    protected @Nullable MenuProvider createMenuProvider(Component name, IItemHandlerModifiable handler, Predicate<Player> stillValid, Consumer<Player> onClose) {
        return CrateMenuProvider.create(name, handler, stillValid, onClose);
    }
}
