package net.dadamalda.create_compatible_storage;

import com.simibubi.create.foundation.data.CreateRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.dadamalda.create_compatible_storage.storage_types.UncooperativeMountedStorageType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CCSMountedStorageTypes {
    private static final CreateRegistrate REGISTRATE = Create_compatible_storage.REGISTRATE;

    public static final TagKey<Block> UNCOOPERATIVE_TAG = TagKey.create(Registries.BLOCK, ResourceLocation.parse(Create_compatible_storage.MODID+":uncooperative_mounted_storage"));

    public static final RegistryEntry<UncooperativeMountedStorageType> UNCOOPERATIVE = REGISTRATE.mountedItemStorage("uncooperative", UncooperativeMountedStorageType::new)
            .associateBlockTag(UNCOOPERATIVE_TAG)
            .register();

    public static void register() {}
}
