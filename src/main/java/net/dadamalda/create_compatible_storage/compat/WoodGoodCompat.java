package net.dadamalda.create_compatible_storage.compat;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WoodGoodCompat {
    private static DynamicTagHelper helper;

    public static void generateTags(ResourceManager manager, ResourceSink sink) {
        if(!UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES.get()) return;

        helper = new DynamicTagHelper();

        for(WoodType woodType : WoodTypeRegistry.INSTANCE.getValues()) {
            addTag(CCSTags.CHEST_MOUNTED_STORAGE, woodType,
                    "quark:chest", "quark:trapped_chest",
                    "woodworks:chest", "woodworks:trapped_chest",
                    "lolmcv:chest", "lolmcv:trapped_chest");

            addTag(CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS, woodType, "woodworks:chest", "woodworks:trapped_chest");

            addTag(CCSTags.FD_CABINETS, woodType, "farmersdelight:cabinet");

            addTag(CCSTags.SD_CABINET_SOUND, woodType, "storagedelight:single_door_cabinet", "storagedelight:cabinet_with_glass_doors",
                    "storagedelight:glass_cabinet", "storagedelight:bookshelf_with_door");
            addTag(CCSTags.SD_CABINET_VARIANTS, woodType, "storagedelight:single_door_cabinet", "storagedelight:cabinet_with_glass_doors");
            addTag(CCSTags.SD_GLASS_CABINETS, woodType, "storagedelight:glass_cabinet");
            addTag(CCSTags.SD_BOOKSHELVES_WITH_DOOR, woodType, "storagedelight:bookshelf_with_door");
            addTag(CCSTags.SD_SMALL_DRAWERS, woodType, "storagedelight:small_drawers");
            addTag(CCSTags.SD_DRAWERS_WITH_BOOKS, woodType, "storagedelight:drawer_with_books");
            addTag(CCSTags.SD_DRAWERS_WITH_DOOR, woodType, "storagedelight:drawer_with_door");
            addTag(CCSTags.SD_DRAWERS, woodType, "storagedelight:drawer");

            addTag(CCSTags.FR_KITCHEN_DRAWERS, woodType, "refurbished_furniture:kitchen_drawer");
            addTag(CCSTags.FR_DRAWERS, woodType, "refurbished_furniture:drawer");
            addTag(CCSTags.FR_STORAGE_CABINETS, woodType, "refurbished_furniture:storage_cabinet");
            addTag(CCSTags.FR_KITCHEN_STORAGE_CABINETS, woodType, "refurbished_furniture:kitchen_storage_cabinet");
            addTag(CCSTags.FR_CRATES, woodType, "refurbished_furniture:crate");
            addTag(CCSTags.FR_MAILBOXES, woodType, "refurbished_furniture:mail_box");
        }

        helper.registerAll(sink);
    }



    private static void addTag(TagKey<Block> tag, WoodType woodType, String... ids) {
        for(String id : ids) {
            Block block = woodType.getBlockOfThis(id);
            if (block != null) {
                helper.get(tag).addOptional(Utils.getID(block));
            }
        }
    }
}
