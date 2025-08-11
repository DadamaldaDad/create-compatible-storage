package net.dadamalda.create_compatible_storage.foundation;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

// Note: Parts of this code are AI generated
public record PreciseContraptionInteractionPacket(
        double x, double y, double z
) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PreciseContraptionInteractionPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.parse("create_compatible_storage:precise_interaction"));

    public static final StreamCodec<ByteBuf, PreciseContraptionInteractionPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, PreciseContraptionInteractionPacket::x,
            ByteBufCodecs.DOUBLE, PreciseContraptionInteractionPacket::y,
            ByteBufCodecs.DOUBLE, PreciseContraptionInteractionPacket::z,
            PreciseContraptionInteractionPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
