package net.dadamalda.create_compatible_storage.foundation;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ContraptionUtils {
    public static @Nullable BlockEntity getBlockEntity(Contraption contraption, BlockPos localPos) {
        BlockEntity be;
        try {
            be = (BlockEntity) Contraption.class.getMethod("getBlockEntityClientSide", BlockPos.class).invoke(contraption, localPos);
        } catch(Exception ignored) {
            try {
                Map<BlockPos, BlockEntity> presentBlockEntities = (Map<BlockPos, BlockEntity>) Contraption.class.getField("presentBlockEntities").get(contraption);
                be = presentBlockEntities.get(localPos);
            } catch (Exception e) {
                LogUtils.getLogger().error("An unknown error occurred. Was there a Create update?", e);
                return null;
            }
        }
        return be;
    }
}
