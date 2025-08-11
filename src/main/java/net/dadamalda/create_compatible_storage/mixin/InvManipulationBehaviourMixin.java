package net.dadamalda.create_compatible_storage.mixin;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.CapManipulationBehaviourBase;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.InvManipulationBehaviour;
import net.createmod.catnip.math.BlockFace;
import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.foundation.UncooperativeStationaryStorageHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapManipulationBehaviourBase.class)
public abstract class InvManipulationBehaviourMixin<T> extends BlockEntityBehaviour {
    @Shadow public abstract BlockFace getTarget();

    @Shadow protected T targetCapability;

    public InvManipulationBehaviourMixin(SmartBlockEntity be) {
        super(be);
    }

    @Inject(method = "findNewCapability", at = @At("HEAD"), remap = false, cancellable = true)
    public void findNewCapability(CallbackInfo ci) {
        // LogUtils.getLogger().info("findNewCapability");
        if(!((Object)this instanceof InvManipulationBehaviour)) return;
        // LogUtils.getLogger().info("instanceof InvManipulationBehaviour");
        Level world = getWorld();
        BlockFace targetBlockFace = getTarget().getOpposite();
        BlockPos pos = targetBlockFace.getPos();

        BlockState targetState = world.getBlockState(pos);
        if(!targetState.is(CCSTags.UNCOOPERATIVE_STATIONARY_STORAGE)) return;
        // LogUtils.getLogger().info("is uncooperative_stationary_storage");

        if(!(world.getBlockEntity(pos) instanceof BaseContainerBlockEntity container)) return;
        // LogUtils.getLogger().info("instanceof BaseContainerBlockEntity");

        targetCapability = (T) UncooperativeStationaryStorageHelper.wrap(container);
        ci.cancel();
    }
}
