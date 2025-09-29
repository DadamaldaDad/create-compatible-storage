package net.dadamalda.create_compatible_storage.compat;

import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class DynamicTagHelper {
    private final Map<TagKey<Block>, SimpleTagBuilder> builders = new HashMap<>();

    public DynamicTagHelper() {}

    public SimpleTagBuilder get(TagKey<Block> tag) {
        return builders.computeIfAbsent(tag, SimpleTagBuilder::of);
    }

    public void registerAll(ResourceSink sink) {
        for (Map.Entry<TagKey<Block>, SimpleTagBuilder> entry : builders.entrySet()) {
            SimpleTagBuilder builder = entry.getValue();
            sink.addTag(builder, Registries.BLOCK);
        }
    }
}
