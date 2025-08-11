package net.dadamalda.create_compatible_storage.mixin;

import com.simibubi.create.content.logistics.packager.PackagerBlock;
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

@Mixin(PackagerBlock.class)
public abstract class PackagerBlockMixin {
    @Redirect(method = "getStateForPlacement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getCapability(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Ljava/lang/Object;"))
    public Object redirectUnpack(Level instance, BlockCapability blockCapability, BlockPos blockPos, Object o) {
        BlockState blockState = instance.getBlockState(blockPos);
        BlockEntity blockEntity = instance.getBlockEntity(blockPos);
        if(!blockState.is(CCSTags.UNCOOPERATIVE_STATIONARY_STORAGE)) return instance.getCapability(blockCapability, blockPos, blockState, blockEntity, o);
        return UncooperativeStationaryStorageHelper.wrap(blockEntity);
    }
}
