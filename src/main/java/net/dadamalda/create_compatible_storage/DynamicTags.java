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
            getPack().addNamespaces(Create_compatible_storage.MODID);
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

            boolean isStorageDelightLoaded = ModList.get().isLoaded("storagedelight");

            executor.accept((resourceManager, resourceSink) -> {
                SimpleTagBuilder chestMountedStorage = SimpleTagBuilder.of(CCSTags.CHEST_MOUNTED_STORAGE);

                Pattern pattern1 = Pattern.compile("^(?:abnww|mcv|q)/[\\w/]+_chest$");
                Pattern pattern2 = Pattern.compile("^sc/[\\w/]+$");

                for (Block b : ForgeRegistries.BLOCKS) {
                    ResourceLocation key = ForgeRegistries.BLOCKS.getKey(b);
                    if (isChestModLoaded && key != null && key.getNamespace().equals("everycomp") && pattern1.matcher(key.getPath()).matches()) {
                        chestMountedStorage.add(key);
                    }
                    if (isStoneChestsLoaded && key != null && key.getNamespace().equals("stonezone") && pattern2.matcher(key.getPath()).matches()) {
                        chestMountedStorage.add(key);
                    }
                }

                resourceSink.addTag(chestMountedStorage, Registries.BLOCK);
            });

            executor.accept((resourceManager, resourceSink) -> {

                // TAGS

                SimpleTagBuilder simpleMountedStorage = SimpleTagBuilder.of(CCSTags.SIMPLE_MOUNTED_STORAGE);
                SimpleTagBuilder singleBlockInventories = SimpleTagBuilder.of(CCSTags.SINGLE_BLOCK_INVENTORIES);

                SimpleTagBuilder fdCabinets = SimpleTagBuilder.of(CCSTags.FD_CABINETS);

                SimpleTagBuilder sdCabinetVariants = SimpleTagBuilder.of(CCSTags.SD_CABINET_VARIANTS);
                SimpleTagBuilder sdGlassCabinets = SimpleTagBuilder.of(CCSTags.SD_GLASS_CABINETS);
                SimpleTagBuilder sdBookshelvesWithDoor = SimpleTagBuilder.of(CCSTags.SD_BOOKSHELVES_WITH_DOOR);
                SimpleTagBuilder sdSmallDrawers = SimpleTagBuilder.of(CCSTags.SD_SMALL_DRAWERS);
                SimpleTagBuilder sdDrawersWithBooks = SimpleTagBuilder.of(CCSTags.SD_DRAWERS_WITH_BOOKS);
                SimpleTagBuilder sdDrawersWithDoor = SimpleTagBuilder.of(CCSTags.SD_DRAWERS_WITH_DOOR);
                SimpleTagBuilder sdDrawers = SimpleTagBuilder.of(CCSTags.SD_DRAWERS);

                SimpleTagBuilder frKitchenDrawers = SimpleTagBuilder.of(CCSTags.FR_KITCHEN_DRAWERS);
                SimpleTagBuilder frDrawers = SimpleTagBuilder.of(CCSTags.FR_DRAWERS);
                SimpleTagBuilder frStorageCabinets = SimpleTagBuilder.of(CCSTags.FR_STORAGE_CABINETS);
                SimpleTagBuilder frCrates = SimpleTagBuilder.of(CCSTags.FR_CRATES);
                SimpleTagBuilder frMailboxes = SimpleTagBuilder.of(CCSTags.FR_MAILBOXES);

                // REGEX PATTERNS

                Pattern fdCabinetPattern = Pattern.compile("^fd/[\\w/]+_cabinet$");

                Pattern furnitureRefurbishedPattern = Pattern.compile("^rfm/[\\w/]+_(?:drawer|storage_cabinet|crate|mail_box)$");

                Pattern storageDelightPattern = Pattern.compile("^sdl/[\\w/]+$");
                Pattern sdCabinetVariantPattern = Pattern.compile("^sdl/[\\w/]+_(?:single_door_cabinet|cabinet_with_glass_doors)$");
                Pattern sdGlassCabinetPattern = Pattern.compile("^sdl/\\w+/glass_\\w+_cabinet$");
                Pattern sdBookshelfWithDoorPattern = Pattern.compile("^sdl/[\\w/]+_bookshelf_with_door$");
                Pattern sdSmallDrawerPattern = Pattern.compile("^sdl/\\w+/small_\\w+_drawers$");
                Pattern sdDrawerWithBooksPattern = Pattern.compile("^sdl/\\w+/\\w+_drawer_with_books$");
                Pattern sdDrawerWithDoorPattern = Pattern.compile("^sdl/\\w+/\\w+_drawer_with_door$");
                Pattern sdDrawerPattern = Pattern.compile("^sdl/\\w+/\\w+_drawer$");

                Pattern frKitchenDrawerPattern = Pattern.compile("^rfm/\\w+/\\w+_kitchen_drawer$");
                Pattern frDrawerPattern = Pattern.compile("^rfm/\\w+/\\w+_drawer$");
                Pattern frStorageCabinetPattern = Pattern.compile("^rfm/\\w+/\\w+_storage_cabinet$");
                Pattern frCratePattern = Pattern.compile("^rfm/\\w+/\\w+_crate$");
                Pattern frMailboxPattern = Pattern.compile("^rfm/\\w+/\\w+_mail_box$");

                for (Block b : ForgeRegistries.BLOCKS) {
                    ResourceLocation key = ForgeRegistries.BLOCKS.getKey(b);

                    if (isFarmersDelightLoaded && key != null && key.getNamespace().equals("everycomp") && fdCabinetPattern.matcher(key.getPath()).matches()) {
                        simpleMountedStorage.add(key);
                        singleBlockInventories.add(key);
                        fdCabinets.add(key);
                    }
                    // Storage Delight
                    if(isStorageDelightLoaded && key != null && key.getNamespace().equals("everycomp") && storageDelightPattern.matcher(key.getPath()).matches()) {
                        simpleMountedStorage.add(key);
                        singleBlockInventories.add(key);

                        if(sdCabinetVariantPattern.matcher(key.getPath()).matches()) {
                            sdCabinetVariants.add(key);
                        } else if(sdGlassCabinetPattern.matcher(key.getPath()).matches()) {
                            sdGlassCabinets.add(key);
                        } else if(sdBookshelfWithDoorPattern.matcher(key.getPath()).matches()) {
                            sdBookshelvesWithDoor.add(key);
                        } else if(sdSmallDrawerPattern.matcher(key.getPath()).matches()) {
                            sdSmallDrawers.add(key);
                        } else if(sdDrawerWithBooksPattern.matcher(key.getPath()).matches()) {
                            sdDrawersWithBooks.add(key);
                        } else if(sdDrawerWithDoorPattern.matcher(key.getPath()).matches()) {
                            sdDrawersWithDoor.add(key);
                        } else if(sdDrawerPattern.matcher(key.getPath()).matches()) {
                            sdDrawers.add(key);
                        }
                    }
                    // Furniture Refurbished
                    if (isFurnitureRefurbishedLoaded && key != null && key.getNamespace().equals("everycomp") && furnitureRefurbishedPattern.matcher(key.getPath()).matches()) {
                        simpleMountedStorage.add(key);
                        singleBlockInventories.add(key);

                        if(frKitchenDrawerPattern.matcher(key.getPath()).matches()) {
                            frKitchenDrawers.add(key);
                        } else if (frDrawerPattern.matcher(key.getPath()).matches()) {
                            frDrawers.add(key);
                        } else if (frStorageCabinetPattern.matcher(key.getPath()).matches()) {
                            frStorageCabinets.add(key);
                        } else if (frCratePattern.matcher(key.getPath()).matches()) {
                            frCrates.add(key);
                        } else if (frMailboxPattern.matcher(key.getPath()).matches()) {
                            frMailboxes.add(key);
                        }
                    }
                }

                resourceSink.addTag(simpleMountedStorage, Registries.BLOCK);
                resourceSink.addTag(singleBlockInventories, Registries.BLOCK);

                resourceSink.addTag(fdCabinets, Registries.BLOCK);

                resourceSink.addTag(sdCabinetVariants, Registries.BLOCK);
                resourceSink.addTag(sdGlassCabinets, Registries.BLOCK);
                resourceSink.addTag(sdBookshelvesWithDoor, Registries.BLOCK);
                resourceSink.addTag(sdSmallDrawers, Registries.BLOCK);
                resourceSink.addTag(sdDrawersWithBooks, Registries.BLOCK);
                resourceSink.addTag(sdDrawersWithDoor, Registries.BLOCK);
                resourceSink.addTag(sdDrawers, Registries.BLOCK);

                resourceSink.addTag(frKitchenDrawers, Registries.BLOCK);
                resourceSink.addTag(frDrawers, Registries.BLOCK);
                resourceSink.addTag(frStorageCabinets, Registries.BLOCK);
                resourceSink.addTag(frCrates, Registries.BLOCK);
                resourceSink.addTag(frMailboxes, Registries.BLOCK);
            });
        }
    }
}
