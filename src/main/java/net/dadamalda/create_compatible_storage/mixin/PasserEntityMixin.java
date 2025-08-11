package net.dadamalda.create_compatible_storage.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.dadamalda.create_compatible_storage.foundation.UncooperativeStationaryStorageHelper;
import net.fxnt.fxntstorage.passer.PasserEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(value = PasserEntity.class)
public class PasserEntityMixin {
    @Inject(method = "getStorage", at = @At(value = "RETURN"), cancellable = true)
    private void getStorageInject(Level level, Direction facing, boolean isSourceContainer, CallbackInfoReturnable<IItemHandler> cir, @Local BlockEntity blockEntity) {
        if(blockEntity == null) return;
        IItemHandler handler = UncooperativeStationaryStorageHelper.wrap(blockEntity);
        if(handler == null) return;
        cir.setReturnValue(handler);
    }
}
