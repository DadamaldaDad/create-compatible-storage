package net.dadamalda.create_compatible_storage.foundation;

import net.dadamalda.create_compatible_storage.Create_compatible_storage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Create_compatible_storage.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class CCSNetwork {
    private static final String PROTOCOL = "1";

    @SubscribeEvent
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL);
        registrar.playToServer(
            PreciseContraptionInteractionPacket.TYPE,
                PreciseContraptionInteractionPacket.STREAM_CODEC,
                PreciseContraptionInteractionStore::handle
        );
    }
}
