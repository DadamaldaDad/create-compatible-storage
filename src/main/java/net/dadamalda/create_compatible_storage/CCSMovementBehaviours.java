package net.dadamalda.create_compatible_storage;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.CampfireMovementBehaviour;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

import java.util.List;

public class CCSMovementBehaviours {
    public static void register() {
        if(ModList.get().isLoaded("carved_wood")) registerCarvedWood();
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
}
