package net.dadamalda.create_compatible_storage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CCSTags {
    public static TagKey<Block> CHEST_MOUNTED_STORAGE = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:chest_mounted_storage"));
    public static TagKey<Block> SIMPLE_MOUNTED_STORAGE = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:simple_mounted_storage"));
    public static TagKey<Block> SINGLE_BLOCK_INVENTORIES = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:single_block_inventories"));

    // Farmer's Delight
    public static TagKey<Block> FD_CABINETS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fd/cabinets"));
    // Storage Delight
    // public static TagKey<Block> SD_CABINET_SOUND = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/cabinet_sound"));
    public static TagKey<Block> SD_CABINET_VARIANTS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/cabinet_variants"));
    public static TagKey<Block> SD_GLASS_CABINETS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/glass_cabinets"));
    public static TagKey<Block> SD_BOOKSHELVES_WITH_DOOR = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/bookshelves_with_door"));
    public static TagKey<Block> SD_SMALL_DRAWERS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/small_drawers"));
    public static TagKey<Block> SD_DRAWERS_WITH_BOOKS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/drawers_with_books"));
    public static TagKey<Block> SD_DRAWERS_WITH_DOOR = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/drawers_with_door"));
    public static TagKey<Block> SD_DRAWERS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:sd/drawers"));
    // Furniture Refurbished
    public static TagKey<Block> FR_DRAWERS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/drawers"));
    public static TagKey<Block> FR_KITCHEN_DRAWERS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/kitchen_drawers"));
    public static TagKey<Block> FR_STORAGE_CABINETS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/storage_cabinets"));
    public static TagKey<Block> FR_COOLERS = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/coolers"));
    public static TagKey<Block> FR_CRATES = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/crates"));
    public static TagKey<Block> FR_MAILBOXES = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create_compatible_storage:fr/mailboxes"));
}
