package net.dadamalda.create_compatible_storage;

import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.dadamalda.create_compatible_storage.storage_types.SoundMountedStorageType;
import net.dadamalda.create_compatible_storage.storage_types.SoundUncooperativeMountedStorageType;
import net.dadamalda.create_compatible_storage.storage_types.UncooperativeMountedStorageType;

public class CCSMountedStorageTypes {
    private static final CreateRegistrate REGISTRATE = Create_compatible_storage.REGISTRATE;

    public static final RegistryEntry<UncooperativeMountedStorageType> UNCOOPERATIVE = REGISTRATE.mountedItemStorage("uncooperative", UncooperativeMountedStorageType::new)
            .associateBlockTag(CCSTags.UNCOOPERATIVE_MOUNTED_STORAGE)
            .register();

    @Deprecated
    public static final RegistryEntry<SoundMountedStorageType> SOUND = REGISTRATE.mountedItemStorage("sound", SoundMountedStorageType::new)
            .register();

    @Deprecated
    public static final RegistryEntry<SoundUncooperativeMountedStorageType> SOUND_UNCOOPERATIVE = REGISTRATE.mountedItemStorage("sound_uncooperative", SoundUncooperativeMountedStorageType::new)
            .register();


    public static void register() {}
}
