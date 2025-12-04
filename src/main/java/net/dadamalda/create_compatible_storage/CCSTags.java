package net.dadamalda.create_compatible_storage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CCSTags {
    // Minecraft
    public static TagKey<Block> SHULKER_BOXES = tag("minecraft:shulker_boxes");
    // Create
    public static TagKey<Block> CHEST_MOUNTED_STORAGE = tag("create:chest_mounted_storage");
    public static TagKey<Block> SIMPLE_MOUNTED_STORAGE = tag("create:simple_mounted_storage");
    public static TagKey<Block> SINGLE_BLOCK_INVENTORIES = tag("create:single_block_inventories");
    // Generic
    public static TagKey<Block> SILENT_MOUNTED_STORAGE = tag("create_compatible_storage:silent_mounted_storage");
    public static TagKey<Block> UNCOOPERATIVE_MOUNTED_STORAGE = tag("create_compatible_storage:uncooperative_mounted_storage");
    public static TagKey<Block> UNCOOPERATIVE_STATIONARY_STORAGE = tag("create_compatible_storage:uncooperative_stationary_storage");
    public static TagKey<Block> UNCOOPERATIVE_STATIONARY_CHESTS = tag("create_compatible_storage:uncooperative_stationary_chests");
    public static TagKey<Block> BARREL_SOUND = tag("create_compatible_storage:barrel_sound");
    // Farmer's Delight
    public static TagKey<Block> FD_CABINETS = tag("create_compatible_storage:fd/cabinets");
    // Storage Delight
    public static TagKey<Block> SD_CABINET_SOUND = tag("create_compatible_storage:sd/cabinet_sound");
    public static TagKey<Block> SD_CABINET_VARIANTS = tag("create_compatible_storage:sd/cabinet_variants");
    public static TagKey<Block> SD_GLASS_CABINETS = tag("create_compatible_storage:sd/glass_cabinets");
    public static TagKey<Block> SD_BOOKSHELVES_WITH_DOOR = tag("create_compatible_storage:sd/bookshelves_with_door");
    public static TagKey<Block> SD_SMALL_DRAWERS = tag("create_compatible_storage:sd/small_drawers");
    public static TagKey<Block> SD_DRAWERS_WITH_BOOKS = tag("create_compatible_storage:sd/drawers_with_books");
    public static TagKey<Block> SD_DRAWERS_WITH_DOOR = tag("create_compatible_storage:sd/drawers_with_door");
    public static TagKey<Block> SD_DRAWERS = tag("create_compatible_storage:sd/drawers");
    // Furniture Refurbished
    public static TagKey<Block> FR_MOUNTED_STORAGE = tag("create_compatible_storage/fr/mounted_storage");
    public static TagKey<Block> FR_DRAWERS = tag("create_compatible_storage:fr/drawers");
    public static TagKey<Block> FR_KITCHEN_DRAWERS = tag("create_compatible_storage:fr/kitchen_drawers");
    public static TagKey<Block> FR_STORAGE_CABINETS = tag("create_compatible_storage:fr/storage_cabinets");
    public static TagKey<Block> FR_KITCHEN_STORAGE_CABINETS = tag("create_compatible_storage:fr/kitchen_storage_cabinets");
    public static TagKey<Block> FR_COOLERS = tag("create_compatible_storage:fr/coolers");
    public static TagKey<Block> FR_CRATES = tag("create_compatible_storage:fr/crates");
    public static TagKey<Block> FR_MAILBOXES = tag("create_compatible_storage:fr/mailboxes");
    // Another Furniture
    public static TagKey<Block> AF_DRAWERS = tag("another_furniture:drawers");
    // Ars Nouveau
    public static TagKey<Block> AN_REPOSITORY = tag("create_compatible_storage:an/repository");
    // Woodworks
    public static TagKey<Block> WW_CLOSETS = tag("create_compatible_storage:ww/closets");
    // Framed Blocks
    public static TagKey<Block> FB_FRAMED_SECRET_STORAGE = tag("create_compatible_storage:fb/framed_secret_storage");
    public static TagKey<Block> FB_FRAMED_CHEST = tag("create_compatible_storage:fb/framed_chest");

    public static TagKey<Block> tag(String path) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.parse(path));
    }
}
