package net.dadamalda.create_compatible_storage;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.api.contraption.BlockMovementChecks;
import com.simibubi.create.api.registry.SimpleRegistry;
import com.simibubi.create.content.contraptions.behaviour.CampfireMovementBehaviour;
import net.dadamalda.create_compatible_storage.contraption_behaviours.GingerbreadDoorMovingInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

import java.util.List;

public class CCSContraptionBehaviours {
    public static void register() {
        if(ModList.get().isLoaded("carved_wood")) registerCarvedWood();
        // if(ModList.get().isLoaded("alexscaves")) registerAlexsCaves();
    }

    public static void registerCarvedWood() {
        List<String> WOOD_TYPES = List.of(
                "spruce",
                "birch",
                "jungle",
                "acacia",
                "dark_oak",
                "crimson",
                "warped",
                "mangrove",
                "bamboo",
                "cherry"
        );

        WOOD_TYPES.forEach((wood_type) -> {
            MovementBehaviour.REGISTRY.register(
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("carved_wood", wood_type + "_campfire")),
                    new CampfireMovementBehaviour()
            );
            MovementBehaviour.REGISTRY.register(
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("carved_wood", "soul_"+wood_type+"_campfire")),
                    new CampfireMovementBehaviour()
            );
        });
    }

    public static void registerAlexsCaves() {
        MovingInteractionBehaviour.REGISTRY.registerProvider(SimpleRegistry.Provider.forBlockTag(CCSTags.AC_GINGERBREAD_DOORS, new GingerbreadDoorMovingInteraction()));
        BlockMovementChecks.registerAttachedCheck(new GingerbreadDoorMovingInteraction());
    }
}
