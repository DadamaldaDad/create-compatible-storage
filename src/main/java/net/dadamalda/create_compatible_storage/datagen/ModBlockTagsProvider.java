package net.dadamalda.create_compatible_storage.datagen;

import cpw.mods.modlauncher.api.INameMappingService;
import net.dadamalda.create_compatible_storage.Create_compatible_storage;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

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
            "endergetic,poise"
    );

    /*
    List<String> EVERY_COMPAT_WOOD_TYPES = Arrays.asList("""
            mynethersdelight,powdery
            quark,azalea
            regions_unexplored,baobab
            regions_unexplored,blackwood
            regions_unexplored,blue_bioshroom
            regions_unexplored,brimwood
            regions_unexplored,cobalt
            regions_unexplored,cypress
            regions_unexplored,dead
            regions_unexplored,eucalyptus
            regions_unexplored,green_bioshroom
            regions_unexplored,joshua
            regions_unexplored,kapok
            regions_unexplored,larch
            regions_unexplored,magnolia
            regions_unexplored,maple
            regions_unexplored,mauve
            regions_unexplored,palm
            regions_unexplored,pine
            regions_unexplored,pink_bioshroom
            regions_unexplored,redwood
            regions_unexplored,socotra
            regions_unexplored,willow
            regions_unexplored,yellow_bioshroom
            biomeswevegone,aspen
            biomeswevegone,baobab
            biomeswevegone,blue_enchanted
            biomeswevegone,cika
            biomeswevegone,cypress
            biomeswevegone,ebony
            biomeswevegone,fir
            biomeswevegone,florus
            biomeswevegone,green_enchanted
            biomeswevegone,holly
            biomeswevegone,ironwood
            biomeswevegone,jacaranda
            biomeswevegone,mahogany
            biomeswevegone,maple
            biomeswevegone,palm
            biomeswevegone,pine
            biomeswevegone,rainbow_eucalyptus
            biomeswevegone,redwood
            biomeswevegone,sakura
            biomeswevegone,skyris
            biomeswevegone,white_mangrove
            biomeswevegone,willow
            biomeswevegone,witch_hazel
            biomeswevegone,zelkova
            biomesoplenty,fir
            biomesoplenty,pine
            biomesoplenty,maple
            biomesoplenty,redwood
            biomesoplenty,mahogany
            biomesoplenty,jacaranda
            biomesoplenty,palm
            biomesoplenty,willow
            biomesoplenty,dead
            biomesoplenty,magic
            biomesoplenty,umbran
            biomesoplenty,hellbark
            biomesoplenty,empyreal
            sullysmod,petrified
            quark,ancient
            quark,blossom
            natures_spirit,redwood
            natures_spirit,sugi
            natures_spirit,wisteria
            natures_spirit,fir
            natures_spirit,willow
            natures_spirit,aspen
            natures_spirit,maple
            natures_spirit,cypress
            natures_spirit,olive
            natures_spirit,joshua
            natures_spirit,ghaf
            natures_spirit,palo_verde
            natures_spirit,coconut
            natures_spirit,cedar
            natures_spirit,larch
            natures_spirit,mahogany
            """.split("\n"));
     */

    TagKey<Block> CHEST_MOUNTED_STORAGE = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:chest_mounted_storage"));
    TagKey<Block> SIMPLE_MOUNTED_STORAGE = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:simple_mounted_storage"));
    TagKey<Block> SINGLE_BLOCK_INVENTORIES = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:single_block_inventories"));

    public ModBlockTagsProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), registries, Create_compatible_storage.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
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
                    "farmersdelight:"+wood_type+"_cabinet"
            ));
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

        addSimples(List.of(
                "farmersdelight:basket"
        ));

        /*
        EVERY_COMPAT_WOOD_TYPES.forEach((wood_type) -> {
            String namespace = wood_type.split(",")[0];
            String id = wood_type.split(",")[1];

            addChests(List.of(
                    "everycomp:q/"+namespace+"/"+id+"_chest",
                    "everycomp:q/"+namespace+"/"+id+"_trapped_chest",
                    "everycomp:mcv/"+namespace+"/"+id+"_chest",
                    "everycomp:mcv/"+namespace+"/"+id+"_trapped_chest",
                    "everycomp:abnww/"+namespace+"/"+id+"_chest",
                    "everycomp:abnww/"+namespace+"/trapped_"+id+"_chest"
            ));

            addSimples(List.of(
                    "everycomp:fd/"+namespace+"/"+id+"_cabinet"
            ));

            if (namespace.equals("biomesoplenty")) {
                addChests(List.of(
                        "lolmcvbop:"+id+"_chest",
                        "lolmcvbop:"+id+"_trapped_chest"
                ));
            }
        });
        */
    }

    private void addChest(String id) {
        this.tag(CHEST_MOUNTED_STORAGE).addOptional(ResourceLocation.parse(id));
    }

    private void addSimple(String id) {
        this.tag(SIMPLE_MOUNTED_STORAGE).addOptional(ResourceLocation.parse(id));
        this.tag(SINGLE_BLOCK_INVENTORIES).addOptional(ResourceLocation.parse(id));
    }

    private void addChests(List<String> ids) {
        ids.forEach(this::addChest);
    }

    private void addSimples(List<String> ids) {
        ids.forEach(this::addSimple);
    }
}
