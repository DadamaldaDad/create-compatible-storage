package net.dadamalda.create_compatible_storage.compat;

import net.dadamalda.create_compatible_storage.Create_compatible_storage;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

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

            executor.accept(WoodGoodCompat::generateTags);

            if(ModList.get().isLoaded("stonezone")) {
                executor.accept(StoneZoneCompat::generateTags);
            }
        }
    }
}
