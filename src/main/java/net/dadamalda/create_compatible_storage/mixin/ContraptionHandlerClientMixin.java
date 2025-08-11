package net.dadamalda.create_compatible_storage.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.contraptions.ContraptionHandlerClient;
import net.dadamalda.create_compatible_storage.foundation.PreciseContraptionInteractionPacket;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContraptionHandlerClient.class)
public class ContraptionHandlerClientMixin {
    @Inject(method = "rightClickingOnContraptionsGetsHandledLocally",
            at = @At(value = "INVOKE",
                    target = "Lnet/createmod/catnip/platform/services/NetworkHelper;sendToServer(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V",
                    shift = At.Shift.BEFORE),
            remap = false
    )
    private static void rightClickingOnContraptionHandledLocally(
            net.neoforged.neoforge.client.event.InputEvent.InteractionKeyMappingTriggered event, CallbackInfo ci, @Local(ordinal = 0) BlockHitResult raycastHit)
    {
        Vec3 location = raycastHit.getLocation();

        PacketDistributor.sendToServer(
                new PreciseContraptionInteractionPacket(
                        location.x, location.y, location.z
                )
        );
    }
}
