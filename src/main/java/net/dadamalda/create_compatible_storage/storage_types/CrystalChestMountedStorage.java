package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.progwml6.ironchest.common.block.regular.entity.CrystalChestBlockEntity;
import com.simibubi.create.api.contraption.storage.SyncedMountedStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.WrapperMountedItemStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.utility.CreateCodecs;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.foundation.ContraptionUtils;
import net.dadamalda.create_compatible_storage.menus.IronChestMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CrystalChestMountedStorage extends WrapperMountedItemStorage<CrystalChestMountedStorage.Handler> implements SyncedMountedStorage {
    public static final Codec<CrystalChestMountedStorage> CODEC = CreateCodecs.ITEM_STACK_HANDLER.xmap(
            CrystalChestMountedStorage::new, storage -> storage.wrapped
    );

    private boolean dirty;

    protected CrystalChestMountedStorage(MountedItemStorageType<?> type, IItemHandler wrapped) {
        super(type, new CrystalChestMountedStorage.Handler(wrapped));
        this.wrapped.onChange = () -> this.dirty = true;
    }

    public CrystalChestMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.CRYSTAL_CHEST.get(), handler);
    }

    @Override
    public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
        if(be instanceof CrystalChestBlockEntity crystalChest) {
            NonNullList<ItemStack> items = NonNullList.create();
            for(int i = 0; i < 108; i++) {
                items.add(this.wrapped.getStackInSlot(i));
            }
            crystalChest.setItems(items);
        }
    }

    @Override
    protected @Nullable MenuProvider createMenuProvider(Component name, IItemHandlerModifiable handler, Predicate<Player> stillValid, Consumer<Player> onClose) {
        return IronChestMenuProvider.create(name, handler, stillValid, onClose);
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void markClean() {
        dirty = false;
    }

    @Override
    public void afterSync(Contraption contraption, BlockPos localPos) {
        BlockEntity be = ContraptionUtils.getBlockEntity(contraption, localPos);
        if(be instanceof CrystalChestBlockEntity crystalChest) {
            NonNullList<ItemStack> items = NonNullList.create();
            for(int i = 0; i < 108; i++) {
                items.add(this.wrapped.getStackInSlot(i));
            }
            crystalChest.setItems(items);
            crystalChest.sortTopStacks();
            crystalChest.sendTopStacksPacket();
        }
    }

    public static @Nullable CrystalChestMountedStorage fromCrystalChest(BlockEntity be) {
        if(be instanceof CrystalChestBlockEntity) {
            IItemHandler handler = be.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
            // make sure the handler is modifiable so new contents can be moved over on disassembly
            return handler instanceof IItemHandlerModifiable modifiable ? new CrystalChestMountedStorage(modifiable) : null;
        }
        return null;
    }

    public static final class Handler extends ItemStackHandler {
        private Runnable onChange = () -> {};

        private Handler(IItemHandler handler) {
            super(108);
            for(int i = 0; i < 108; i++) {
                this.setStackInSlot(i, handler.getStackInSlot(i));
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            this.onChange.run();
        }
    }
}
