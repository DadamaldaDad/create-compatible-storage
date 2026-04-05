package net.dadamalda.create_compatible_storage.contraption_behaviours;

import com.github.alexmodguy.alexscaves.server.block.GingerbreadDoorBlock;
import com.simibubi.create.api.contraption.BlockMovementChecks;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GingerbreadDoorMovingInteraction extends SimpleBlockMovingInteraction implements BlockMovementChecks.AttachedCheck {

    @Override
    protected BlockState handle(Player player, Contraption contraption, BlockPos pos, BlockState currentState) {
        if (!(currentState.getBlock() instanceof GingerbreadDoorBlock))
            return currentState;

        currentState = currentState.cycle(DoorBlock.OPEN);

        if (player != null) {
            SoundEvent sound = currentState.getValue(DoorBlock.OPEN)
                    ? SoundEvents.BAMBOO_WOOD_DOOR_CLOSE : SoundEvents.BAMBOO_WOOD_DOOR_OPEN;
            float pitch = player.level().random.nextFloat() * 0.1F + 0.9F;
            playSound(player, sound, pitch);
        }

        return currentState;
    }

    @Override
    protected boolean updateColliders() {
        return true;
    }

    @Override
    public BlockMovementChecks.CheckResult isBlockAttachedTowards(BlockState state, Level world, BlockPos pos, Direction direction) {
        return state.is(CCSTags.AC_GINGERBREAD_DOORS) && direction == Direction.DOWN ? BlockMovementChecks.CheckResult.SUCCESS : BlockMovementChecks.CheckResult.PASS;
    }
}
