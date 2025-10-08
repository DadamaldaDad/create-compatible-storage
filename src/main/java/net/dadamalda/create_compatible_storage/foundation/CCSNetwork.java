package net.dadamalda.create_compatible_storage.foundation;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class CCSNetwork {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation.parse("create_compatible_storage:main"))
            .networkProtocolVersion(() -> PROTOCOL)
            .clientAcceptedVersions(PROTOCOL::equals)
            .serverAcceptedVersions(PROTOCOL::equals)
            .simpleChannel();

    private static int index = 0;

    public static void register() {
        CHANNEL.messageBuilder(PreciseContraptionInteractionPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(PreciseContraptionInteractionPacket::new)
                .encoder(PreciseContraptionInteractionPacket::encode)
                .consumerMainThread(PreciseContraptionInteractionPacket::handle)
                .add();
    }
}
