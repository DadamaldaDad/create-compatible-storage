package net.dadamalda.create_compatible_storage;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.dadamalda.create_compatible_storage.storage_types.*;

public class CCSMountedStorageTypes {
    private static final CreateRegistrate REGISTRATE = Create_compatible_storage.REGISTRATE;

    public static final RegistryEntry<MountedItemStorageType<?>, UncooperativeMountedStorageType> UNCOOPERATIVE = REGISTRATE.mountedItemStorage("uncooperative", UncooperativeMountedStorageType::new)
            .associateBlockTag(CCSTags.UNCOOPERATIVE_MOUNTED_STORAGE)
            .register();

    public static final RegistryEntry<MountedItemStorageType<?>, FurnitureRefurbishedMountedStorageType> FURNITURE_REFURBISHED = REGISTRATE.mountedItemStorage("furniture_refurbished", FurnitureRefurbishedMountedStorageType::new)
            .associateBlockTag(CCSTags.FR_MOUNTED_STORAGE)
            .register();

    public static final RegistryEntry<MountedItemStorageType<?>, RepositoryMountedStorageType> REPOSITORY = REGISTRATE.mountedItemStorage("repository", RepositoryMountedStorageType::new)
            .associateBlockTag(CCSTags.AN_REPOSITORY)
            .register();

    public static final RegistryEntry<MountedItemStorageType<?>, ClosetMountedStorageType> CLOSET = REGISTRATE.mountedItemStorage("closet", ClosetMountedStorageType::new)
            .associateBlockTag(CCSTags.WW_CLOSETS)
            .register();

    public static final RegistryEntry<MountedItemStorageType<?>, IronChestMountedStorageType> IRON_CHEST = REGISTRATE.mountedItemStorage("iron_chest", IronChestMountedStorageType::new)
            .associateBlockTag(CCSTags.IC_IRON_CHEST_MOUNTED_STORAGE)
            .register();

    public static final RegistryEntry<MountedItemStorageType<?>, CrystalChestMountedStorageType> CRYSTAL_CHEST = REGISTRATE.mountedItemStorage("crystal_chest", CrystalChestMountedStorageType::new)
            .associateBlockTag(CCSTags.IC_CRYSTAL_CHEST)
            .register();

    @Deprecated
    public static final RegistryEntry<MountedItemStorageType<?>, SoundMountedStorageType> SOUND = REGISTRATE.mountedItemStorage("sound", SoundMountedStorageType::new)
            .register();

    @Deprecated
    public static final RegistryEntry<MountedItemStorageType<?>, SoundUncooperativeMountedStorageType> SOUND_UNCOOPERATIVE = REGISTRATE.mountedItemStorage("sound_uncooperative", SoundUncooperativeMountedStorageType::new)
            .register();


    public static void register() {}
}
