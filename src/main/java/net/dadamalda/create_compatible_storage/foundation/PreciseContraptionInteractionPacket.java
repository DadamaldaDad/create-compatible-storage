package net.dadamalda.create_compatible_storage.foundation;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

// Note: Parts of this code are AI generated
public class PreciseContraptionInteractionPacket {
    private final Vec3 hit;

    public PreciseContraptionInteractionPacket(Vec3 hit) {
        this.hit = hit;
    }

    public PreciseContraptionInteractionPacket(FriendlyByteBuf buf) {
        this.hit = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(hit.x);
        buf.writeDouble(hit.y);
        buf.writeDouble(hit.z);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            PreciseContraptionInteractionStore.storeInteraction(player, hit);
        });
        ctx.get().setPacketHandled(true);
    }

    public Vec3 getHit() { return hit; }
}
