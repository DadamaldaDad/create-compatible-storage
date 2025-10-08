package net.dadamalda.create_compatible_storage.foundation;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

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
}
