package net.dadamalda.create_compatible_storage.datagen;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.Create_compatible_storage;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    List<String> COLOURS = List.of(
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "lime",
            "green",
            "cyan",
            "light_blue",
            "blue",
            "purple",
            "magenta",
            "pink"
    );

    List<String> WOOD_TYPES = List.of(
            "oak",
            "spruce",
            "birch",
            "jungle",
            "acacia",
            "dark_oak",
            "crimson",
            "warped",
            "mangrove",
            "bamboo",
            "cherry"
    );

    List<String> QUARK_CHEST_TYPES = List.of(
            "nether_brick",
            "purpur",
            "prismarine",
            "ancient",
            "azalea",
            "blossom"
    );

    List<String> WOODWORKS_WOOD_TYPES = List.of(
            "autumnity,maple",
            "atmospheric,rosewood",
            "atmospheric,morado",
            "atmospheric,yucca",
            "atmospheric,laurel",
            "atmospheric,aspen",
            "atmospheric,kousa",
            "atmospheric,grimwood",
            "environmental,willow",
            "environmental,pine",
            "environmental,plum",
            "environmental,wisteria",
            "upgrade_aquatic,driftwood",
            "upgrade_aquatic,river",
            "endergetic,poise",
            "windswept,holly",
            "windswept,chestnut",
            "windswept,pine"
    );

    List<String> STONE_CHEST_STONE_TYPES = List.of(
            "andesite",
            "calcite",
            "cobbled_deepslate",
            "cobblestone",
            "deepslate",
            "diorite",
            "granite",
            "prismarine",
            "stone",
            "tuff"
    );

    public ModBlockTagsProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), registries, Create_compatible_storage.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        COLOURS.forEach(colour -> {
            addBlocksToTag(CCSTags.FR_KITCHEN_DRAWERS, "refurbished_furniture:"+colour+"_kitchen_drawer");
            addBlocksToTag(CCSTags.FR_KITCHEN_STORAGE_CABINETS, "refurbished_furniture:"+colour+"_kitchen_storage_cabinet");
            addBlocksToTag(CCSTags.FR_COOLERS, "refurbished_furniture:"+colour+"_cooler");
        });

        WOOD_TYPES.forEach((wood_type) -> {
            addChests(List.of(
                    "quark:"+wood_type+"_chest",
                    "quark:"+wood_type+"_trapped_chest",
                    "woodworks:"+wood_type+"_chest",
                    "woodworks:trapped_"+wood_type+"_chest",
                    "lolmcv:"+wood_type+"_chest",
                    "lolmcv:"+wood_type+"_trapped_chest"
            ));

            addBlocksToTag(CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS, "woodworks:"+wood_type+"_chest", "woodworks:trapped_"+wood_type+"_chest");

            addBlocksToTag(CCSTags.FD_CABINETS, "farmersdelight:"+wood_type+"_cabinet");

            addBlocksToTag(CCSTags.SD_CABINET_VARIANTS, "storagedelight:"+wood_type+"_single_door_cabinet",
                    "storagedelight:"+wood_type+"_cabinet_with_glass_doors");
            addBlocksToTag(CCSTags.SD_GLASS_CABINETS, "storagedelight:glass_"+wood_type+"_cabinet");
            addBlocksToTag(CCSTags.SD_BOOKSHELVES_WITH_DOOR, "storagedelight:"+wood_type+"_bookshelf_with_door");
            addBlocksToTag(CCSTags.SD_SMALL_DRAWERS, "storagedelight:small_"+wood_type+"_drawers");
            addBlocksToTag(CCSTags.SD_DRAWERS_WITH_BOOKS, "storagedelight:"+wood_type+"_drawer_with_books");
            addBlocksToTag(CCSTags.SD_DRAWERS_WITH_DOOR, "storagedelight:"+wood_type+"_drawer_with_door");
            addBlocksToTag(CCSTags.SD_DRAWERS, "storagedelight:"+wood_type+"_drawer");

            addBlocksToTag(CCSTags.FR_DRAWERS, "refurbished_furniture:"+wood_type+"_drawer");
            addBlocksToTag(CCSTags.FR_KITCHEN_DRAWERS, "refurbished_furniture:"+wood_type+"_kitchen_drawer");
            addBlocksToTag(CCSTags.FR_STORAGE_CABINETS, "refurbished_furniture:"+wood_type+"_storage_cabinet");
            addBlocksToTag(CCSTags.FR_KITCHEN_STORAGE_CABINETS, "refurbished_furniture:"+wood_type+"_kitchen_storage_cabinet");
            addBlocksToTag(CCSTags.FR_CRATES, "refurbished_furniture:"+wood_type+"_crate");
            addBlocksToTag(CCSTags.FR_MAILBOXES, "refurbished_furniture:"+wood_type+"_mail_box");
        });

        WOODWORKS_WOOD_TYPES.forEach(wood_type -> {
            String namespace = wood_type.split(",")[0];
            String id = wood_type.split(",")[1];
            addChests(List.of(
                    namespace+":"+id+"_chest",
                    namespace+":trapped_"+id+"_chest"
            ));

            addBlocksToTag(CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS, namespace+":"+id+"_chest", namespace+":trapped_"+id+"_chest");
        });

        QUARK_CHEST_TYPES.forEach((chest_type) -> {
            addChests(List.of(
                    "quark:"+chest_type+"_chest",
                    "quark:"+chest_type+"_trapped_chest"
            ));
        });

        STONE_CHEST_STONE_TYPES.forEach(stone_type -> {
            addChest("stonechest:chest_"+stone_type);

            addBlocksToTag(CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS, "stonechest:chest_"+stone_type);
        });

        addBlocksToTag(CCSTags.FD_CABINETS, "mynethersdelight:red_nether_bricks_cabinet", "mynethersdelight:nether_bricks_cabinet",
                "mynethersdelight:blackstone_bricks_cabinet", "mynethersdelight:powdery_cabinet");

        addBlocksToTag(CCSTags.SILENT_MOUNTED_STORAGE,
                "farmersdelight:basket");

        addTagsToTag(CCSTags.SIMPLE_MOUNTED_STORAGE,
                CCSTags.FD_CABINETS);
        addBlocksToTag(CCSTags.SIMPLE_MOUNTED_STORAGE,
                "farmersdelight:basket");

        addTagsToTag(CCSTags.SIMPLE_MOUNTED_STORAGE,
                CCSTags.SD_DRAWERS, CCSTags.SD_SMALL_DRAWERS, CCSTags.SD_DRAWERS_WITH_BOOKS,
                CCSTags.SD_CABINET_VARIANTS, CCSTags.SD_GLASS_CABINETS,  CCSTags.SD_BOOKSHELVES_WITH_DOOR, CCSTags.SD_DRAWERS_WITH_DOOR,
                CCSTags.AF_DRAWERS,
                CCSTags.FR_COOLERS, CCSTags.FR_CRATES, CCSTags.FR_MAILBOXES);
        addTagsToTag(CCSTags.SIMPLE_MOUNTED_STORAGE,
                "handcrafted:desks", "handcrafted:nightstands", "handcrafted:counters",
                "handcrafted:cupboards", "handcrafted:drawers", "handcrafted:shelves", "handcrafted:side_tables");

        addTagsToTag(CCSTags.UNCOOPERATIVE_STATIONARY_STORAGE,
                CCSTags.UNCOOPERATIVE_MOUNTED_STORAGE,
                CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS);

        addTagsToTag(CCSTags.SINGLE_BLOCK_INVENTORIES,
                CCSTags.SILENT_MOUNTED_STORAGE, CCSTags.BARREL_SOUND, CCSTags.SD_CABINET_SOUND,
                CCSTags.FR_STORAGE_CABINETS, CCSTags.FR_COOLERS, CCSTags.FR_DRAWERS,
                CCSTags.FR_KITCHEN_DRAWERS, CCSTags.FR_CRATES);
        addBlocksToTag(CCSTags.SINGLE_BLOCK_INVENTORIES,
                "ars_nouveau:repository");

        addTagsToTag(CCSTags.SILENT_MOUNTED_STORAGE,
                CCSTags.FR_MAILBOXES);
        addTagsToTag(CCSTags.SILENT_MOUNTED_STORAGE,
                "handcrafted:desks", "handcrafted:nightstands", "handcrafted:counters",
                "handcrafted:cupboards", "handcrafted:drawers", "handcrafted:shelves", "handcrafted:side_tables");
        addBlocksToTag(CCSTags.SILENT_MOUNTED_STORAGE,
                "ars_nouveau:repository");

        addTagsToTag(CCSTags.BARREL_SOUND,
                CCSTags.FD_CABINETS, CCSTags.SD_DRAWERS, CCSTags.SD_SMALL_DRAWERS, CCSTags.SD_DRAWERS_WITH_BOOKS, CCSTags.AF_DRAWERS);

        addTagsToTag(CCSTags.SD_CABINET_SOUND,
                CCSTags.SD_CABINET_VARIANTS, CCSTags.SD_GLASS_CABINETS,
                CCSTags.SD_BOOKSHELVES_WITH_DOOR, CCSTags.SD_DRAWERS_WITH_DOOR);

        addTagsToTag(CCSTags.FR_MOUNTED_STORAGE,
                CCSTags.FR_DRAWERS, CCSTags.FR_KITCHEN_DRAWERS, CCSTags.FR_STORAGE_CABINETS, CCSTags.FR_KITCHEN_STORAGE_CABINETS);

        addBlocksToTag(CCSTags.AN_REPOSITORY,
                "ars_nouveau:repository");
    }

    private void addChest(String id) {
        this.tag(CCSTags.CHEST_MOUNTED_STORAGE).addOptional(ResourceLocation.parse(id));
    }

    private void addChests(List<String> ids) {
        ids.forEach(this::addChest);
    }

    @SafeVarargs
    private void addTagsToTag(TagKey<Block> tag, TagKey<Block>... tags) {
        for (TagKey<Block> tag2 : tags) {
            this.tag(tag).addOptionalTag(tag2);
        }
    }

    private void addTagsToTag(TagKey<Block> tag, String... tags) {
        for (String tag2 : tags) {
            this.tag(tag).addOptionalTag(ResourceLocation.parse(tag2));
        }
    }

    private void addBlocksToTag(TagKey<Block> tag, String... blocks) {
        for(String block : blocks) {
            this.tag(tag).addOptional(ResourceLocation.parse(block));
        }
    }
}
