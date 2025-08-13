package net.dadamalda.create_compatible_storage;

import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public class DynamicTags {
    public static void init() {
        ServerDataGenerator generator = new ServerDataGenerator();
        generator.register();
    }

    public static class ServerDataGenerator extends DynServerResourcesGenerator {
        protected ServerDataGenerator() {
            super(new DynamicDataPack(ResourceLocation.parse(Create_compatible_storage.MODID+":everycomp_tags")));

            getPack().addNamespaces("create");
        }

        @Override
        public Logger getLogger() {
            return Moonlight.LOGGER;
        }

        @Override
        public boolean dependsOnLoadedPacks() {
            return true;
        }

        @Override
        public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
            super.regenerateDynamicAssets(executor);

            boolean isChestModLoaded = ModList.get().isLoaded("quark")
                    || ModList.get().isLoaded("lolmcv")
                    || ModList.get().isLoaded("woodworks");

            boolean isStoneChestsLoaded = ModList.get().isLoaded("stonechest");

            boolean isFarmersDelightLoaded = ModList.get().isLoaded("farmersdelight");

            boolean isFurnitureRefurbishedLoaded = ModList.get().isLoaded("refurbished_furniture");

            executor.accept((resourceManager, resourceSink) -> {
                TagKey<Block> tagKey = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:chest_mounted_storage"));

                SimpleTagBuilder tag = SimpleTagBuilder.of(tagKey);

                Pattern pattern1 = Pattern.compile("^(?:q|mcv|abnww)/.*_chest$");
                Pattern pattern2 = Pattern.compile("^sc/.*$");

                for (Block b : ForgeRegistries.BLOCKS) {
                    ResourceLocation key = ForgeRegistries.BLOCKS.getKey(b);
                    if (isChestModLoaded && key != null && key.getNamespace().equals("everycomp") && pattern1.matcher(key.getPath()).matches()) {
                        tag.add(key);
                    }
                    if (isStoneChestsLoaded && key != null && key.getNamespace().equals("stonezone") && pattern2.matcher(key.getPath()).matches()) {
                        tag.add(key);
                    }
                }

                resourceSink.addTag(tag, Registries.BLOCK);
            });

            executor.accept((resourceManager, resourceSink) -> {
                TagKey<Block> tagKey1 = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:simple_mounted_storage"));
                TagKey<Block> tagKey2 = TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("create:single_block_inventories"));

                SimpleTagBuilder tag1 = SimpleTagBuilder.of(tagKey1);
                SimpleTagBuilder tag2 = SimpleTagBuilder.of(tagKey2);

                Pattern pattern1 = Pattern.compile("^fd/.*_cabinet$");

                Pattern pattern2 = Pattern.compile("^rfm/.*_(?:storage_cabinet|drawer|kitchen_drawwer|kitchen_storage_cabinet|crate|mail_box)$");

                for (Block b : ForgeRegistries.BLOCKS) {
                    ResourceLocation key = ForgeRegistries.BLOCKS.getKey(b);
                    if (isFarmersDelightLoaded && key != null && key.getNamespace().equals("everycomp") && pattern1.matcher(key.getPath()).matches()) {
                        tag1.add(key);
                        tag2.add(key);
                    }
                    if (isFurnitureRefurbishedLoaded && key != null && key.getNamespace().equals("everycomp") && pattern2.matcher(key.getPath()).matches()) {
                        tag1.add(key);
                        tag2.add(key);
                    }
                }

                resourceSink.addTag(tag1, Registries.BLOCK);
                resourceSink.addTag(tag2, Registries.BLOCK);
            });
        }
    }
}
