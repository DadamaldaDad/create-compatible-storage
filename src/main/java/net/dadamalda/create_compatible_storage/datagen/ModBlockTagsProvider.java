package net.dadamalda.create_compatible_storage.datagen;

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

    TagKey<Block> CHEST_MOUNTED_STORAGE = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), new ResourceLocation("create", "chest_mounted_storage"));
    TagKey<Block> SIMPLE_MOUNTED_STORAGE = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), new ResourceLocation("create", "simple_mounted_storage"));
    TagKey<Block> SINGLE_BLOCK_INVENTORIES = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), new ResourceLocation("create", "single_block_inventories"));

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

        QUARK_CHEST_TYPES.forEach((chest_type) -> {
            addChests(List.of(
                    "quark:"+chest_type+"_chest",
                    "quark:"+chest_type+"_trapped_chest"
            ));
        });

        addSimples(List.of(
                "farmersdelight:basket"
        ));
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
