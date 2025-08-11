package net.dadamalda.create_compatible_storage.mixin;

import com.simibubi.create.impl.unpacking.DefaultUnpackingHandler;
import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.foundation.UncooperativeStationaryStorageHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DefaultUnpackingHandler.class)
public abstract class DefaultUnpackingHandlerMixin {
    @Redirect(method = "unpack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getCapability(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Ljava/lang/Object;)Ljava/lang/Object;"))
    public Object redirectUnpack(Level instance, BlockCapability blockCapability, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, Object o) {
        if(!blockState.is(CCSTags.UNCOOPERATIVE_STATIONARY_STORAGE)) return instance.getCapability(blockCapability, blockPos, blockState, blockEntity, o);
        return UncooperativeStationaryStorageHelper.wrap(blockEntity);
    }
}
