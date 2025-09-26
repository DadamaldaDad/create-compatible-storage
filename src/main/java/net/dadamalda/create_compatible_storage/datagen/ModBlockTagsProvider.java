package net.dadamalda.create_compatible_storage.datagen;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.Create_compatible_storage;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
            addSimples(List.of(
                    "refurbished_furniture:"+colour+"_kitchen_storage_cabinet",
                    "refurbished_furniture:"+colour+"_kitchen_drawer",
                    "refurbished_furniture:"+colour+"_cooler"
            ));

            this.tag(CCSTags.FR_KITCHEN_DRAWERS).addOptional(ResourceLocation.parse("refurbished_furniture:"+colour+"_kitchen_drawer"));
            this.tag(CCSTags.FR_STORAGE_CABINETS).addOptional(ResourceLocation.parse("refurbished_furniture:"+colour+"_kitchen_storage_cabinet"));
            this.tag(CCSTags.FR_COOLERS).addOptional(ResourceLocation.parse("refurbished_furniture:"+colour+"_kitchen_cooler"));
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

            addSimples(List.of(
                    "farmersdelight:"+wood_type+"_cabinet",
                    "storagedelight:"+wood_type+"_single_door_cabinet",
                    "storagedelight:"+wood_type+"_drawer",
                    "storagedelight:"+wood_type+"_drawer_with_door",
                    "storagedelight:"+wood_type+"_drawer_with_books",
                    "storagedelight:small_"+wood_type+"_drawers",
                    "storagedelight:"+wood_type+"_bookshelf_with_door",
                    "storagedelight:glass_"+wood_type+"_cabinet",
                    "storagedelight:"+wood_type+"_cabinet_with_glass_doors",
                    "refurbished_furniture:"+wood_type+"_storage_cabinet",
                    "refurbished_furniture:"+wood_type+"_drawer",
                    "refurbished_furniture:"+wood_type+"_kitchen_drawer",
                    "refurbished_furniture:"+wood_type+"_kitchen_storage_cabinet",
                    "refurbished_furniture:"+wood_type+"_crate",
                    "refurbished_furniture:"+wood_type+"_mail_box"
            ));

            this.tag(CCSTags.FD_CABINETS).addOptional(ResourceLocation.parse("farmersdelight:"+wood_type+"_cabinet"));

            this.tag(CCSTags.SD_CABINET_VARIANTS).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_single_door_cabinet"));
            this.tag(CCSTags.SD_CABINET_VARIANTS).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_cabinet_with_glass_doors"));
            this.tag(CCSTags.SD_GLASS_CABINETS).addOptional(ResourceLocation.parse("storagedelight:glass_"+wood_type+"_cabinet"));
            this.tag(CCSTags.SD_BOOKSHELVES_WITH_DOOR).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_bookshelf_with_door"));
            this.tag(CCSTags.SD_SMALL_DRAWERS).addOptional(ResourceLocation.parse("storagedelight:small_"+wood_type+"_drawers"));
            this.tag(CCSTags.SD_DRAWERS_WITH_BOOKS).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_drawer_with_books"));
            this.tag(CCSTags.SD_DRAWERS_WITH_DOOR).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_drawer_with_door"));
            this.tag(CCSTags.SD_DRAWERS).addOptional(ResourceLocation.parse("storagedelight:"+wood_type+"_drawer"));

            this.tag(CCSTags.FR_DRAWERS).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_drawer"));
            this.tag(CCSTags.FR_KITCHEN_DRAWERS).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_kitchen_drawer"));
            this.tag(CCSTags.FR_STORAGE_CABINETS).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_storage_cabinet"));
            this.tag(CCSTags.FR_STORAGE_CABINETS).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_kitchen_storage_cabinet"));
            this.tag(CCSTags.FR_CRATES).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_crate"));
            this.tag(CCSTags.FR_MAILBOXES).addOptional(ResourceLocation.parse("refurbished_furniture:"+wood_type+"_mail_box"));
        });

        WOODWORKS_WOOD_TYPES.forEach(wood_type -> {
            String namespace = wood_type.split(",")[0];
            String id = wood_type.split(",")[1];
            addChests(List.of(
                    namespace+":"+id+"_chest",
                    namespace+":trapped_"+id+"_chest"
            ));
        });

        QUARK_CHEST_TYPES.forEach((chest_type) -> {
            addChests(List.of(
                    "quark:"+chest_type+"_chest",
                    "quark:"+chest_type+"_trapped_chest"
            ));
        });

        STONE_CHEST_STONE_TYPES.forEach(stone_type -> {
            addChest("stonechest:chest_"+stone_type);
        });

        addSimples(List.of(
                "farmersdelight:basket"
        ));

        addSimpleTags(List.of(
                "handcrafted:desks",
                "handcrafted:nightstands",
                "handcrafted:counters",
                "handcrafted:cupboards",
                "handcrafted:drawers",
                "handcrafted:shelves",
                "handcrafted:side_tables"
        ));
    }

    private void addChest(String id) {
        this.tag(CCSTags.CHEST_MOUNTED_STORAGE).addOptional(ResourceLocation.parse(id));
    }

    private void addSimple(String id) {
        this.tag(CCSTags.SIMPLE_MOUNTED_STORAGE).addOptional(ResourceLocation.parse(id));
        this.tag(CCSTags.SINGLE_BLOCK_INVENTORIES).addOptional(ResourceLocation.parse(id));
    }

    private void addSimpleTag(String tag) {
        this.tag(CCSTags.SIMPLE_MOUNTED_STORAGE).addOptionalTag(ResourceLocation.parse(tag));
        this.tag(CCSTags.SINGLE_BLOCK_INVENTORIES).addOptionalTag(ResourceLocation.parse(tag));
    }

    private void addChests(List<String> ids) {
        ids.forEach(this::addChest);
    }

    private void addSimples(List<String> ids) {
        ids.forEach(this::addSimple);
    }

    private void addSimpleTags(List<String> tags) {
        tags.forEach(this::addSimpleTag);
    }
}
