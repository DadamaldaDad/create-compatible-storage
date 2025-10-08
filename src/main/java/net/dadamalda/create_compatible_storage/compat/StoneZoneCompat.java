package net.dadamalda.create_compatible_storage.compat;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class StoneZoneCompat {
    private static DynamicTagHelper helper;

    public static void generateTags(ResourceManager manager, ResourceSink sink) {
        helper = new DynamicTagHelper();

        for(StoneType stoneType : StoneTypeRegistry.INSTANCE.getValues()) {
            addTag(CCSTags.CHEST_MOUNTED_STORAGE, stoneType, "stonechest:chest");
            addTag(CCSTags.UNCOOPERATIVE_STATIONARY_CHESTS, stoneType, "stonechest:chest");
        }

        helper.registerAll(sink);
    }

    private static void addTag(TagKey<Block> tag, StoneType stoneType, String... ids) {
        for(String id : ids) {
            Block block = stoneType.getBlockOfThis(id);
            if (block != null) {
                helper.get(tag).addOptional(Utils.getID(block));
            }
        }
    }
}
