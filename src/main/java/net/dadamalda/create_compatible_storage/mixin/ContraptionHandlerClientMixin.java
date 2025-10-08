package net.dadamalda.create_compatible_storage.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.contraptions.ContraptionHandlerClient;
import net.dadamalda.create_compatible_storage.foundation.CCSNetwork;
import net.dadamalda.create_compatible_storage.foundation.PreciseContraptionInteractionPacket;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.InputEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContraptionHandlerClient.class)
public class ContraptionHandlerClientMixin {
    @Inject(method = "rightClickingOnContraptionsGetsHandledLocally",
            at = @At(value = "INVOKE",
                    target = "Lcom/simibubi/create/AllPackets;getChannel()Lnet/minecraftforge/network/simple/SimpleChannel;",
                    shift = At.Shift.BEFORE),
            remap = false
    )
    private static void rightClickingOnContraptionHandledLocally(InputEvent.InteractionKeyMappingTriggered event,
                                                                 CallbackInfo ci, @Local(ordinal = 0) BlockHitResult raycastHit)
    {
        CCSNetwork.CHANNEL.sendToServer(new PreciseContraptionInteractionPacket(raycastHit.getLocation()));
    }
}
