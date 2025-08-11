package net.dadamalda.create_compatible_storage.mixin;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.foundation.UncooperativeStationaryStorageHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChuteBlockEntity.class)
public abstract class ChuteBlockEntityMixin extends SmartBlockEntity implements IHaveGoggleInformation {
    public ChuteBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "grabCapability", at = @At("HEAD"), remap = false, cancellable = true)
    private void grabCapability(Direction side, CallbackInfoReturnable<IItemHandler> cir) {
        BlockPos pos = this.worldPosition.relative(side);
        if(level == null) return;
        BlockState state = level.getBlockState(pos);
        if(!state.is(CCSTags.UNCOOPERATIVE_STATIONARY_STORAGE)) return;
        BlockEntity be = level.getBlockEntity(pos);
        cir.setReturnValue(UncooperativeStationaryStorageHelper.wrap(be));
        cir.cancel();
    }
}
