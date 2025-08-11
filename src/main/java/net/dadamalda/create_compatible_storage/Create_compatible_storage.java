package net.dadamalda.create_compatible_storage;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.dadamalda.create_compatible_storage.compat.DynamicTags;
import net.dadamalda.create_compatible_storage.datagen.ModBlockTagsProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Create_compatible_storage.MODID)
public class Create_compatible_storage {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "create_compatible_storage";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);


    public Create_compatible_storage(IEventBus modEventBus, ModContainer modContainer) {
        // IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        if(ModList.get().isLoaded("moonlight")) {
            MoonlightRegistration.register(modEventBus);
        }

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::gatherData);

        CCSMountedStorageTypes.register();

        REGISTRATE.registerEventListeners(modEventBus);

        // Register ourselves for server and other game events we are interested in
        // MinecraftForge.EVENT_BUS.register(this);

        if(ModList.get().isLoaded("moonlight")) {
            MoonlightRegistration.register(modEventBus);
        }

        if(ModList.get().isLoaded("everycomp") || ModList.get().isLoaded("stonezone")) {
            DynamicTags.init();
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void gatherData(final GatherDataEvent event) {
        event.getGenerator().addProvider(
                event.includeServer(),
                new ModBlockTagsProvider(event.getGenerator(), event.getLookupProvider(), event.getExistingFileHelper())
        );

    }

    /*
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    */

}
