package net.dadamalda.create_compatible_storage;

import com.mojang.logging.LogUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
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
            getPack().addNamespaces(Create_compatible_storage.MODID);
        }

        @Override
        public Logger getLogger() {
            return Moonlight.LOGGER;
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
                TagKey<Block> tagKey = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:chest_mounted_storage"));

                SimpleTagBuilder tag = SimpleTagBuilder.of(tagKey);

                Pattern pattern1 = Pattern.compile("^(?:q|mcv|abnww)/.*_chest$");
                Pattern pattern2 = Pattern.compile("^sc/.*$");

                for (Block b : BuiltInRegistries.BLOCK) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(b);
                    if (isChestModLoaded && key.getNamespace().equals("everycomp") && pattern1.matcher(key.getPath()).matches()) {
                        tag.add(key);
                    }
                    if (isStoneChestsLoaded && key.getNamespace().equals("stonezone") && pattern2.matcher(key.getPath()).matches()) {
                        tag.add(key);
                    }
                }

                resourceSink.addTag(tag, Registries.BLOCK);
            });

            executor.accept((resourceManager, resourceSink) -> {
                TagKey<Block> tagKey1 = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:simple_mounted_storage"));
                TagKey<Block> tagKey2 = TagKey.create(Registries.BLOCK, ResourceLocation.parse("create:single_block_inventories"));

                TagKey<Block> tagKey3 = TagKey.create(Registries.BLOCK, ResourceLocation.parse(Create_compatible_storage.MODID+":uncooperative_mounted_storage"));

                SimpleTagBuilder tag1 = SimpleTagBuilder.of(tagKey1);
                SimpleTagBuilder tag2 = SimpleTagBuilder.of(tagKey2);
                SimpleTagBuilder tag3 = SimpleTagBuilder.of(tagKey3);

                Pattern pattern1 = Pattern.compile("^fd/.*_cabinet$");

                Pattern pattern2 = Pattern.compile("^rfm/.*_(?:storage_cabinet|drawer|kitchen_drawwer|kitchen_storage_cabinet|crate|mail_box)$");

                for (Block b : BuiltInRegistries.BLOCK) {
                    ResourceLocation key = BuiltInRegistries.BLOCK.getKey(b);
                    if (isFarmersDelightLoaded && key.getNamespace().equals("everycomp") && pattern1.matcher(key.getPath()).matches()) {
                        tag1.add(key);
                        tag2.add(key);
                    }
                    if (isFurnitureRefurbishedLoaded && key.getNamespace().equals("everycomp") && pattern2.matcher(key.getPath()).matches()) {
                        tag3.add(key);
                        tag2.add(key);
                    }
                }

                resourceSink.addTag(tag1, Registries.BLOCK);
                resourceSink.addTag(tag2, Registries.BLOCK);
                resourceSink.addTag(tag3, Registries.BLOCK);
            });
        }
    }
}
