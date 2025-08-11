package net.dadamalda.create_compatible_storage.compat;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.CampfireMovementBehaviour;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

public class WoodGoodMovementBehavioursCompat {
    public static void register() {
        if(!ModList.get().isLoaded("everycomp")) return;
        for (WoodType woodType : WoodTypeRegistry.INSTANCE.getValues()) {
            if(HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

            add(woodType, "carved_wood:campfire", new CampfireMovementBehaviour());
            add(woodType, "carved_wood:soul_campfire", new CampfireMovementBehaviour());
        }
    }

    private static void add(WoodType woodType, String name, MovementBehaviour behaviour) {
        Block block = woodType.getBlockOfThis(name);
        if(block != null) {
            MovementBehaviour.REGISTRY.register(
                    block,
                    behaviour
            );
        }
    }
}
