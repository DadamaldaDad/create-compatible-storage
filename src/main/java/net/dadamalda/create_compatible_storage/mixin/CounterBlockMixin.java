package net.dadamalda.create_compatible_storage.mixin;

import earth.terrarium.handcrafted.common.blocks.CounterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(CounterBlock.class)
public abstract class CounterBlockMixin extends HorizontalDirectionalBlock {
    public CounterBlockMixin(Properties pProperties) {
        super(pProperties);
    }

    @Shadow
    public abstract void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved);

    @Inject(method = "onRemove", at = @At("HEAD"), cancellable = true, remap = true)
    private void injectOnRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if(moved) {
            super.onRemove(state, level, pos, newState, moved);
            ci.cancel();
        }
    }
}
