package net.dadamalda.create_compatible_storage;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.dadamalda.create_compatible_storage.storage_types.UncooperativeMountedStorageType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class CCSMountedStorageTypes {
    private static final CreateRegistrate REGISTRATE = Create_compatible_storage.REGISTRATE;

    public static final TagKey<Block> SIMPLE_3X9_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.parse(Create_compatible_storage.MODID+":uncooperative_mounted_storage"));

    public static final RegistryEntry<MountedItemStorageType<?>, UncooperativeMountedStorageType> SIMPLE_3X9 = REGISTRATE.mountedItemStorage("uncooperative", UncooperativeMountedStorageType::new)
            .associateBlockTag(SIMPLE_3X9_TAG)
            .register();

    private static <T extends MountedItemStorageType<?>> RegistryEntry<MountedItemStorageType<?>, T> simpleItem(String name, Supplier<T> supplier) {
        return REGISTRATE.mountedItemStorage(name, supplier).register();
    }

    public static void register() {}
}
