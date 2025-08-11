package net.dadamalda.create_compatible_storage.foundation;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PreciseContraptionInteractionStore {
    private static Map<UUID, Vec3> LAST_HITS = new HashMap<>();

    public static void storeInteraction(ServerPlayer player, Vec3 hit) {
        LAST_HITS.put(player.getUUID(), hit);
    }

    public static Vec3 getLastInteraction(ServerPlayer player) {
        return LAST_HITS.get(player.getUUID());
    }

    public static void handle(PreciseContraptionInteractionPacket payload, IPayloadContext ctx) {
        if(ctx.player() instanceof ServerPlayer player) {
            storeInteraction(player, new Vec3(payload.x(), payload.y(), payload.z()));
        }
    }
}
